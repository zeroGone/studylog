package io.zerogone.blog.service;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.MemberRole;
import io.zerogone.common.service.CreateWithImageService;
import io.zerogone.user.model.User;
import io.zerogone.blog.BlogDao;
import io.zerogone.user.UserDao;
import io.zerogone.common.fileupload.ImageUploadService;
import io.zerogone.common.fileupload.ImageUrl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogCreateService implements CreateWithImageService<BlogDto> {
    private final BlogDao blogDao;
    private final UserDao userDao;
    private final BlogInvitationService blogInvitationService;
    private final ConversionService conversionService;
    private final ImageUploadService imageUploadService;

    public BlogCreateService(BlogDao blogDao,
                             UserDao userDao,
                             BlogInvitationService blogInvitationService,
                             ConversionService conversionService,
                             @Qualifier("blogImageUploadService") ImageUploadService imageUploadService) {
        this.blogDao = blogDao;
        this.userDao = userDao;
        this.blogInvitationService = blogInvitationService;
        this.conversionService = conversionService;
        this.imageUploadService = imageUploadService;
    }

    @Transactional
    @Override
    public BlogDto create(BlogDto dto, MultipartFile image) {
        ImageUrl imageUrl = imageUploadService.upload(image);
        dto.setImageUrl(imageUrl.getValue());
        return create(dto);
    }

    @Transactional
    @Override
    public BlogDto create(BlogDto dto) {
        createBlogInvitationKey(dto);
        Blog entity = conversionService.convert(dto, Blog.class);
        addBlogMemberEntities(entity, dto.getMembers());
        blogDao.save(entity);
        inviteMembers(entity);
        return conversionService.convert(entity, BlogDto.class);
    }

    private void createBlogInvitationKey(BlogDto dto) {
        StringBuilder keyBuilder = new StringBuilder();
        dto.getName().codePoints().forEach(keyBuilder::append);
        dto.setInvitationKey(keyBuilder.toString());
    }

    private void addBlogMemberEntities(Blog blog, List<BlogMemberDto> memberDtos) {
        for (BlogMemberDto member : memberDtos) {
            User user = userDao.findById(member.getId());
            if (user == null) {
                String message = "???????????? ?????? ????????? ???????????? ????????????";
                SQLException sqlException = new SQLException(message);
                throw new ConstraintViolationException(message, sqlException, "id=" + member.getId());
            }
            blog.addMember(new BlogMember(user, blog, member.getRole()));
        }
    }

    private void inviteMembers(Blog entity) {
        List<BlogMember> targetMembers = entity.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getRole(), MemberRole.INVITING))
                .collect(Collectors.toList());

        targetMembers.forEach(member -> blogInvitationService.inviteMemberToBlog(member.getEmail(), entity.getName(), entity.getInvitationKey()));
    }
}

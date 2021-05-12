package io.zerogone.service.create;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.repository.BlogDao;
import io.zerogone.service.BlogInvitationService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
public class BlogCreateService implements CreateService<BlogDto> {
    private final BlogDao blogDao;
    private final BlogInvitationService blogInvitationService;
    private final Converter<Blog, BlogDto> entityConverter;
    private final Converter<BlogDto, Blog> dtoConverter;

    public BlogCreateService(BlogDao blogDao,
                             BlogInvitationService blogInvitationService,
                             Converter<Blog, BlogDto> entityConverter,
                             Converter<BlogDto, Blog> dtoConverter) {
        this.blogDao = blogDao;
        this.blogInvitationService = blogInvitationService;
        this.entityConverter = entityConverter;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    @Override
    public BlogDto create(BlogDto dto) {
        if (isBlogNameOverlapped(dto.getName())) {
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }

        createBlogInvitationKey(dto);
        Blog entity = dtoConverter.convert(dto);
        addBlogMemberEntities(entity, dto.getMembers());
        blogDao.save(entity);
        inviteMembers(entity);
        return entityConverter.convert(entity);
    }

    private boolean isBlogNameOverlapped(String name) {
        try {
            blogDao.findByName(name);
            return true;
        } catch (NotExistedDataException notExistedDataException) {
            return false;
        }
    }

    private void createBlogInvitationKey(BlogDto dto) {
        StringBuilder keyBuilder = new StringBuilder();
        dto.getName().codePoints().forEach(keyBuilder::append);
        dto.setInvitationKey(keyBuilder.toString());
    }

    private void addBlogMemberEntities(Blog blog, List<BlogMemberDto> memberDtos) {
        for (BlogMemberDto memberDto : memberDtos) {
            BlogMember blogMember = new BlogMember.Builder(blog, memberDto.getId())
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .nickName(memberDto.getNickName())
                    .imageUrl(memberDto.getImageUrl())
                    .role(memberDto.getRole())
                    .build();
            blog.addMember(blogMember);
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

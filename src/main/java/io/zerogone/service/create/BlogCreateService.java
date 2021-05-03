package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.dto.*;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.service.fileupload.ImageUploadService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogCreateService extends CreateWithImageService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private static final String BLOG_DEFAULT_IMAGE_URL = "/img/blog-default.png";
    private final CreateTemplate<Blog> createTemplate;
    private final CreateService createService;

    public BlogCreateService(ImageUploadService imageUploadService,
                             CreateTemplate<Blog> createTemplate,
                             @Qualifier("blogMemberCreateService") CreateService createService) {
        super(imageUploadService);
        this.createTemplate = createTemplate;
        this.createService = createService;
    }

    @Transactional
    @Override
    public DataTransferObject create(DataTransferObject dto) {
        BlogCreateDto blogDto = (BlogCreateDto) dto;

        if (blogDto.getImageUrl() == null) {
            blogDto.setImageUrl(BLOG_DEFAULT_IMAGE_URL);
        }

        BlogDto createdBlogDto = (BlogDto) createTemplate.create(blogDto);
        blogDto.setId(createdBlogDto.getId());
        createBlogMembers(blogDto);
        return createdBlogDto;
    }

    private void createBlogMembers(BlogCreateDto blogDto) {
        List<BlogMemberDto> members = new ArrayList<>();
        members.add(createAdmin(blogDto, blogDto.getAdmin()));
        members.addAll(createMembers(blogDto, blogDto.getMembers()));
        validateMembers(members);
        members.forEach(createService::create);
    }

    private BlogMemberDto createAdmin(BlogDto blogDto, UserDto userDto) {
        BlogMemberDto blogMemberDto = new BlogMemberDto();
        blogMemberDto.setBlog(blogDto);
        blogMemberDto.setUser(userDto);
        blogMemberDto.setRole(MemberRole.ADMIN);
        return blogMemberDto;
    }

    private List<BlogMemberDto> createMembers(BlogDto blogDto, List<UserDto> users) {
        List<BlogMemberDto> blogMemberDtos = new ArrayList<>();
        for (UserDto user : users) {
            BlogMemberDto blogMemberDto = new BlogMemberDto();
            blogMemberDto.setBlog(blogDto);
            blogMemberDto.setUser(user);
            blogMemberDto.setRole(MemberRole.INVITING);
            blogMemberDtos.add(blogMemberDto);
        }
        return blogMemberDtos;
    }

    private void validateMembers(List<BlogMemberDto> blogMemberDtos) {
        logger.info("-----validate blog members-----");
        List<UserDto> members = blogMemberDtos.stream().map(BlogMemberDto::getUser).collect(Collectors.toList());
        if (members.stream().map(UserDto::getId).distinct().count() < members.size()) {
            throw new BlogMembersStateException("한 블로그에 같은 사람이 두 번 포함될 수 없습니다");
        }
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), 0))) {
            throw new BlogMembersStateException("유효하지 않은 아이디를 가진 유저가 존재합니다");
        }
        logger.info("-----Blog Members are validated!-----");
    }
}

package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.service.create.CreateWithImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class BlogCreateController {
    private final CreateWithImageService<BlogDto> createService;
    private final Converter<UserDto, BlogMemberDto> converter;

    public BlogCreateController(CreateWithImageService<BlogDto> createService,
                                @Qualifier("userDtoToAdminBlogMemberDtoConverter") Converter<UserDto, BlogMemberDto> converter) {
        this.converter = converter;
        this.createService = createService;
    }

    @PostMapping("blogs")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDto handleCreatingBlog(@SessionAttribute UserDto userInfo,
                                      @ModelAttribute @Valid BlogDto blogDto,
                                      @RequestPart(required = false) MultipartFile image) {

        setBlogMembersRole(blogDto);
        addAdminBlogMember(blogDto, userInfo);

        if (image == null) {
            return createService.create(blogDto);
        } else {
            return createService.create(blogDto, image);
        }
    }

    private void setBlogMembersRole(BlogDto blog) {
        if (blog.getMembers() == null) {
            return;
        }
        blog.getMembers().forEach(member -> member.setRole(MemberRole.INVITING));
    }

    private void addAdminBlogMember(BlogDto blog, UserDto admin) {
        if (blog.getMembers() == null) {
            blog.setMembers(new ArrayList<>());
        }
        blog.getMembers().add(converter.convert(admin));
    }
}

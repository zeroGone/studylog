package io.zerogone.controller.api;

import io.zerogone.converter.UserDtoToBlogMemberDtoConverter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.fileupload.ImageUploadService;
import io.zerogone.service.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class BlogCreateController {
    private final UserDtoToBlogMemberDtoConverter converter;
    private final ImageUploadService imageUploadService;
    private final CreateService<BlogDto> createService;

    public BlogCreateController(@Qualifier("userDtoToAdminBlogMemberDtoConverter") UserDtoToBlogMemberDtoConverter converter,
                                @Qualifier("blogImageUploadService") ImageUploadService imageUploadService,
                                CreateService<BlogDto> createService) {
        this.converter = converter;
        this.imageUploadService = imageUploadService;
        this.createService = createService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<Object> handleBlogCreateApi(@SessionAttribute UserDto userInfo,
                                                      @ModelAttribute @Valid BlogDto blogDto,
                                                      @RequestPart(required = false) MultipartFile image) {

        setBlogMembersRole(blogDto);
        addAdminBlogMember(blogDto, userInfo);
        setBlogImageUrl(blogDto, image);
        BlogDto createdBlogDto = createService.create(blogDto);

        return new ResponseEntity<>(createdBlogDto, HttpStatus.CREATED);
    }

    private void setBlogImageUrl(BlogDto blogDto, MultipartFile image) {
        ImageUrl imageUrl = imageUploadService.upload(image);
        blogDto.setImageUrl(imageUrl.getValue());
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

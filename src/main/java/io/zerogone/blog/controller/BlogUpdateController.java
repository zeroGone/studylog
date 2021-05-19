package io.zerogone.blog.controller;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.model.MemberRole;
import io.zerogone.blog.service.BlogUpdateService;
import io.zerogone.common.fileupload.ImageUploadService;
import io.zerogone.common.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class BlogUpdateController {
    private final ImageUploadService imageUploadService;
    private final BlogUpdateService updateService;

    public BlogUpdateController(@Qualifier("blogImageUploadService") ImageUploadService imageUploadService,
                                BlogUpdateService updateService) {
        this.imageUploadService = imageUploadService;
        this.updateService = updateService;
    }

    @PutMapping("api/blog/{id}")
    public ResponseEntity<BlogDto> handleUpdatingBlog(
            @SessionAttribute UserDto userInfo,
            @RequestBody BlogDto blog) {

        validateAuth(userInfo, blog);

        return ResponseEntity.ok(updateService.updateBlog(blog));
    }

    @PostMapping("api/blog/{id}")
    public ResponseEntity<BlogDto> handleUpdatingBlogWithImage(
            @SessionAttribute UserDto userInfo,
            @RequestBody BlogDto blog,
            MultipartFile image) {

        validateAuth(userInfo, blog);
        ImageUrl newImageUrl = imageUploadService.upload(image);
        blog.setImageUrl(newImageUrl.getValue());

        return ResponseEntity.ok(updateService.updateBlog(blog));
    }

    private void validateAuth(UserDto user, BlogDto blog) {
        BlogMemberDto admin = blog.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getId(), user.getId()))
                .findAny()
                .orElseThrow(IllegalAccessError::new);

        if (admin.getRole() != MemberRole.ADMIN) {
            throw new IllegalArgumentException("관리자가 아닙니다!");
        }
    }
}

package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.service.BlogUpdateService;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class BlogUpdateController {
    private final ImageUploadService<BlogDto> imageUploadService;
    private final BlogUpdateService updateService;

    public BlogUpdateController(ImageUploadService<BlogDto> imageUploadService, BlogUpdateService updateService) {
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
            MultipartFile imageFile) {

        validateAuth(userInfo, blog);
        blog = imageUploadService.upload(blog, imageFile);

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

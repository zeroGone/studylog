package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@RestController
public class BlogCreateController {
    private final ImageUploadService<BlogDto> imageUploadService;
    private final CreateService<BlogDto> createService;

    public BlogCreateController(ImageUploadService<BlogDto> imageUploadService, CreateService<BlogDto> createService) {
        this.imageUploadService = imageUploadService;
        this.createService = createService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<BlogDto> handleBlogCreateApi(@SessionAttribute UserDto userInfo,
                                                       @ModelAttribute BlogDto blogDto,
                                                       @RequestPart(required = false) MultipartFile image) {
        if (image != null) {
            blogDto = imageUploadService.upload(blogDto, image);
        }
        blogDto.setMembers(getMembersWithAdmin(blogDto, userInfo));
        return new ResponseEntity<>(createService.create(blogDto), HttpStatus.CREATED);
    }

    private Set<BlogMemberDto> getMembersWithAdmin(BlogDto dto, UserDto admin) {
        Set<BlogMemberDto> members = getMembersWithRole(dto);
        members.add(getAdminMember(admin));
        return members;
    }

    private Set<BlogMemberDto> getMembersWithRole(BlogDto dto) {
        if (dto.getMembers() != null) {
            dto.getMembers().forEach(member -> member.setRole(MemberRole.INVITING));
            return dto.getMembers();
        } else {
            return new HashSet<>();
        }
    }

    private BlogMemberDto getAdminMember(UserDto admin) {
        BlogMemberDto adminDto = new BlogMemberDto();
        adminDto.setId(admin.getId());
        adminDto.setImageUrl(admin.getImageUrl());
        adminDto.setEmail(admin.getEmail());
        adminDto.setName(admin.getName());
        adminDto.setNickName(admin.getNickName());
        adminDto.setRole(MemberRole.ADMIN);
        return adminDto;
    }
}

package io.zerogone.blog.service;

import io.zerogone.blog.BlogDao;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.common.fileupload.ImageUploadService;
import io.zerogone.common.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogUpdateService {
    private final BlogDao blogDao;
    private final BlogInvitationService blogInvitationService;
    private final ImageUploadService imageUploadService;
    private final ConversionService conversionService;

    public BlogUpdateService(BlogDao blogDao,
                             BlogInvitationService blogInvitationService,
                             @Qualifier("blogImageUploadService") ImageUploadService imageUploadService,
                             ConversionService conversionService) {
        this.blogDao = blogDao;
        this.blogInvitationService = blogInvitationService;
        this.imageUploadService = imageUploadService;
        this.conversionService = conversionService;
    }

    @Transactional
    public BlogDto updateBlog(BlogDto dto, MultipartFile image) {
        ImageUrl imageUrl = imageUploadService.upload(image);
        dto.setImageUrl(imageUrl.getValue());
        return updateBlog(dto);
    }

    @Transactional
    public BlogDto updateBlog(BlogDto dto) {
        Blog entity = blogDao.findById(dto.getId());
        entity.setIntroduce(dto.getIntroduce());
        entity.setImageUrl(dto.getImageUrl());

        List<BlogMember> newMembers = getNewMembers(entity, dto);
        newMembers.forEach(member -> {
            entity.addMember(member);
            blogInvitationService.inviteMemberToBlog(member.getEmail(), entity.getName(), entity.getInvitationKey());
        });
        return dto;
    }

    private List<BlogMember> getNewMembers(Blog entity, BlogDto dto) {
        return dto.getMembers()
                .stream()
                .filter(memberDto -> entity.getMembers().stream().noneMatch(member -> Objects.equals(member.getUserId(), memberDto.getId())))
                .map(memberDto -> conversionService.convert(memberDto, BlogMember.class))
                .collect(Collectors.toList());
    }
}

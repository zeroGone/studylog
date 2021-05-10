package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlogEntityToDtoConverter implements Converter<Blog, BlogDto> {
    private final Converter<BlogMember, BlogMemberDto> blogMemberConverter;

    public BlogEntityToDtoConverter(Converter<BlogMember, BlogMemberDto> blogMemberConverter) {
        this.blogMemberConverter = blogMemberConverter;
    }

    @Override
    public BlogDto convert(Blog entity) {
        BlogDto dto = new BlogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        dto.setInvitationKey(entity.getInvitationKey());
        dto.setMembers(entity.getMembers().stream().map(blogMemberConverter::convert).collect(Collectors.toSet()));
        return dto;
    }
}

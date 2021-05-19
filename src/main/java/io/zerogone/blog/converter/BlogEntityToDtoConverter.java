package io.zerogone.blog.converter;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.Blog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogEntityToDtoConverter implements Converter<Blog, BlogDto> {
    @Override
    public BlogDto convert(Blog entity) {
        BlogDto dto = new BlogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        dto.setInvitationKey(entity.getInvitationKey());
        return dto;
    }
}

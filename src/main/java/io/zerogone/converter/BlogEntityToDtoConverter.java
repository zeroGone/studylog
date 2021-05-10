package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogEntityToDtoConverter implements Converter<Blog, BlogDto> {
    @Override
    public BlogDto convert(Blog key) {
        BlogDto dto = new BlogDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setIntroduce(key.getIntroduce());
        dto.setImageUrl(key.getImageUrl());
        dto.setInvitationKey(key.getInvitationKey());
        return dto;
    }
}

package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogConverter implements Converter<Blog, BlogDto> {
    @Override
    public BlogDto convert(Blog key) {
        BlogDto dto = new BlogDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setIntroduce(key.getIntroduce());
        dto.setImageUrl(key.getImageUrl());
        return dto;
    }
}

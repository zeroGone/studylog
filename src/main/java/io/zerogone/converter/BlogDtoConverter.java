package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogDtoConverter implements Converter<BlogDto, Blog> {
    @Override
    public Blog convert(BlogDto key) {
        return new Blog(key.getId(), key.getName(), key.getIntroduce(), key.getImageUrl());
    }
}

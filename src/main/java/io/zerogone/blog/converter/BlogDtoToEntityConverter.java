package io.zerogone.blog.converter;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.Blog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogDtoToEntityConverter implements Converter<BlogDto, Blog> {
    @Override
    public Blog convert(BlogDto dto) {
        return new Blog(dto.getId(), dto.getName(), dto.getIntroduce(), dto.getImageUrl(), dto.getInvitationKey());
    }
}

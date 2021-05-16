package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogDtoToEntityConverter implements Converter<BlogDto, Blog> {
    @Override
    public Blog convert(BlogDto dto) {
        return new Blog(dto.getName(), dto.getIntroduce(), dto.getImageUrl(), dto.getInvitationKey());
    }
}

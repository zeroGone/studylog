package io.zerogone.converter;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.model.entity.Post;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostDtoToEntityConverter implements Converter<PostDto, Post> {
    private final Converter<BlogDto, Blog> blogConverter;
    private final Converter<UserDto, User> userConverter;

    public PostDtoToEntityConverter(Converter<BlogDto, Blog> blogConverter, Converter<UserDto, User> userConverter) {
        this.blogConverter = blogConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Post convert(PostDto dto) {
        return new Post(dto.getId(),
                dto.getTitle(),
                dto.getContents(),
                blogConverter.convert(dto.getBlog()),
                userConverter.convert(dto.getWriter()));
    }
}

package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserEntityToDtoConverter implements Converter<User, UserDto> {
    private final Converter<Blog, BlogDto> blogConverter;

    public UserEntityToDtoConverter(Converter<Blog, BlogDto> blogConverter) {
        this.blogConverter = blogConverter;
    }

    @Override
    public UserDto convert(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setNickName(entity.getNickName());
        dto.setImageUrl(entity.getImageUrl());
        dto.setBlogs(entity.getBlogs().stream().map(blogConverter::convert).collect(Collectors.toList()));
        return dto;
    }
}

package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserEntityConverter implements Converter<User, UserWithBlogsDto> {
    private final Converter<Blog, BlogDto> blogDtoConverter;

    public UserEntityConverter(Converter<Blog, BlogDto> blogDtoConverter) {
        this.blogDtoConverter = blogDtoConverter;
    }

    @Override
    public UserWithBlogsDto convert(User key) {
        UserWithBlogsDto dto = new UserWithBlogsDto();
        dto.setId(key.getId());
        dto.setEmail(key.getEmail());
        dto.setName(key.getName());
        dto.setNickName(key.getNickName());
        dto.setImageUrl(key.getImageUrl());
        try {
            dto.setBlogs(key.getBlogs().stream().map(blogDtoConverter::convert).collect(Collectors.toList()));
        } catch (LazyInitializationException | NullPointerException exception) {
            dto.setBlogs(new ArrayList<>());
        }
        return dto;
    }
}

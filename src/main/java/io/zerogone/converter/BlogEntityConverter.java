package io.zerogone.converter;

import io.zerogone.model.dto.BlogWithMembersDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class BlogEntityConverter implements Converter<Blog, BlogWithMembersDto> {
    private final Converter<User, UserDto> userDtoConverter;

    public BlogEntityConverter(Converter<User, UserDto> userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public BlogWithMembersDto convert(Blog key) {
        BlogWithMembersDto dto = new BlogWithMembersDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setIntroduce(key.getIntroduce());
        dto.setImageUrl(key.getImageUrl());
        try {
            dto.setMembers(key.getMembers().stream().map(userDtoConverter::convert).collect(Collectors.toList()));
        } catch (LazyInitializationException | NullPointerException exception) {
            dto.setMembers(new ArrayList<>());
        }
        return dto;
    }
}

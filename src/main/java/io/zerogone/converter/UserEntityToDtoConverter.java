package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setNickName(entity.getNickName());
        dto.setImageUrl(entity.getImageUrl());
        dto.setBlogs(convertBlogs(entity.getBlogs()));
        return dto;
    }

    private List<BlogDto> convertBlogs(List<Blog> blogs) {
        return blogs.stream()
                .map(blog -> {
                    BlogDto dto = new BlogDto();
                    dto.setId(blog.getId());
                    dto.setName(blog.getName());
                    dto.setIntroduce(blog.getIntroduce());
                    dto.setImageUrl(blog.getImageUrl());
                    return dto;
                })
                .collect(Collectors.toList());

    }
}

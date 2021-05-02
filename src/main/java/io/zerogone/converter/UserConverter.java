package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.model.entity.User;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<User> {
    @Override
    public User convert(DataTransferObject dto) {
        UserDto user = (UserDto) dto;
        return new User(user.getId(), user.getName(), user.getEmail(), user.getNickName(), user.getImageUrl());
    }

    @Override
    public DataTransferObject convert(User entity) {
        UserWithBlogsDto dto = new UserWithBlogsDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setNickName(entity.getNickName());
        dto.setImageUrl(entity.getImageUrl());
        try {
            dto.setBlogs(entity.getBlogs()
                    .stream()
                    .map(blog -> {
                        BlogDto blogDto = new BlogDto();
                        blogDto.setId(blog.getId());
                        blogDto.setIntroduce(blog.getIntroduce());
                        blogDto.setName(blog.getName());
                        blogDto.setImageUrl(blog.getImageUrl());
                        return blogDto;
                    })
                    .collect(Collectors.toList()));
        } catch (LazyInitializationException | NullPointerException exception) {
            dto.setBlogs(new ArrayList<>());
        }
        return dto;
    }
}

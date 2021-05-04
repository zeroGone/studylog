package io.zerogone.converter;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User key) {
        UserDto dto = new UserDto();
        dto.setId(key.getId());
        dto.setEmail(key.getEmail());
        dto.setName(key.getName());
        dto.setNickName(key.getNickName());
        dto.setImageUrl(key.getImageUrl());
        return dto;
    }
}

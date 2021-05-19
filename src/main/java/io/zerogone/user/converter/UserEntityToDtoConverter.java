package io.zerogone.user.converter;

import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
        return dto;
    }
}

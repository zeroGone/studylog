package io.zerogone.user.converter;

import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToEntityConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getNickName(), dto.getImageUrl());
    }
}

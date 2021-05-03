package io.zerogone.converter;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto key) {
        return new User(key.getId(), key.getName(), key.getEmail(), key.getNickName(), key.getImageUrl());
    }
}

package io.zerogone.service.search;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserEmailSearchService implements SearchService<String, UserDto> {
    private final UserDao userDao;
    private final Converter<User, UserDto> converter;

    public UserEmailSearchService(UserDao userDao, Converter<User, UserDto> converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserDto search(String key) {
        return converter.convert(userDao.findByEmail(key));
    }
}

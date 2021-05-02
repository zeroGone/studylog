package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserEmailSearchService implements SearchService<String, UserDto> {
    private final UserDao userDao;
    private final Converter<User> converter;

    public UserEmailSearchService(UserDao userDao, Converter<User> converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserDto search(String key) {
        User entity = userDao.findByEmail(key);
        return (UserDto) converter.convert(entity);
    }
}

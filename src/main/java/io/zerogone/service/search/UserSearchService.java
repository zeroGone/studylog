package io.zerogone.service.search;

import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserSearchService implements SearchService<Email, UserDto> {
    private final UserDao userDao;
    private final Converter<User, UserDto> converter;

    public UserSearchService(UserDao userDao, Converter<User, UserDto> converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserDto search(Email email) {
        User entity = userDao.findByEmail(email.getValue());
        return converter.convert(entity);
    }
}

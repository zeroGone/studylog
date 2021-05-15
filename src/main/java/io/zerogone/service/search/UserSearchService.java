package io.zerogone.service.search;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

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
        try {
            User entity = userDao.findByEmail(email.get());
            return converter.convert(entity);
        } catch (NoResultException noResultException) {
            throw new NotExistDataException("검색한 유저가 없습니다", email.get());
        }
    }
}

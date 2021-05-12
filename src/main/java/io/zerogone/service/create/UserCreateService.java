package io.zerogone.service.create;

import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
public class UserCreateService implements CreateService<UserDto> {
    private final UserDao userDao;
    private final Converter<User, UserDto> entityConverter;
    private final Converter<UserDto, User> dtoConverter;

    public UserCreateService(UserDao userDao, Converter<User, UserDto> entityConverter, Converter<UserDto, User> dtoConverter) {
        this.userDao = userDao;
        this.entityConverter = entityConverter;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        if (isNotUniqueEmail(dto.getEmail())) {
            throw new UniquePropertyException("이메일이 중복되었습니다", dto.getEmail());
        }
        if (isNotUniqueNickName(dto.getNickName())) {
            throw new UniquePropertyException("닉네임이 중복되었습니다", dto.getNickName());
        }
        User entity = dtoConverter.convert(dto);
        userDao.save(entity);
        return entityConverter.convert(entity);
    }

    private boolean isNotUniqueNickName(String nickName) {
        try {
            userDao.findByNickName(nickName);
            return true;
        } catch (NoResultException noResultException) {
            return false;
        }
    }

    private boolean isNotUniqueEmail(String email) {
        try {
            userDao.findByEmail(email);
            return true;
        } catch (NoResultException noResultException) {
            return false;
        }
    }
}

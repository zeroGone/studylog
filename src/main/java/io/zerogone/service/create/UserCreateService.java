package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class UserCreateService implements CreateService<UserDto> {
    private static final String USER_DEFAULT_IMAGE_URL = "/img/user-default/";
    private static final String USER_DEFAULT_IMAGE_TYPE = ".png";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;
    private final Converter<User, UserDto> converter;

    public UserCreateService(UserDao userDao, Converter<User, UserDto> converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        validate(dto);

        if (dto.getImageUrl() == null) {
            int randomNumber = (int) (Math.random() * 10);
            dto.setImageUrl(USER_DEFAULT_IMAGE_URL + randomNumber + USER_DEFAULT_IMAGE_TYPE);
        }

        User entity = new User(dto.getName(), dto.getEmail(), dto.getNickName(), dto.getImageUrl());

        try {
            userDao.save(entity);
        } catch (PersistenceException persistenceException) {
            logger.error("user property is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("유저의 이메일이나 닉네임이 중복되었습니다");
        }

        return converter.convert(entity);
    }

    private void validate(UserDto userDto) {
        logger.info("-----validate created User data-----");
        if (userDto.getId() != 0) {
            throw new IllegalArgumentException("생성 시 id를 부여하면 안됩니다");
        }
        if (userDto.getName() == null) {
            throw new NotNullPropertyException(User.class, "name");
        }
        if (userDto.getEmail() == null) {
            throw new NotNullPropertyException(User.class, "email");
        }
        if (userDto.getNickName() == null) {
            throw new NotNullPropertyException(User.class, "nick name");
        }
        logger.info("-----validated Blog data!-----");
    }
}

package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class UserCreateTemplate extends CreateTemplate<User> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;

    public UserCreateTemplate(Converter<User> converter, UserDao userDao) {
        super(converter);
        this.userDao = userDao;
    }

    @Override
    User saveEntity(User entity) {
        try {
            userDao.save(entity);
        } catch (PersistenceException persistenceException) {
            logger.error("user property is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("유저의 이메일이나 닉네임이 중복되었습니다");
        }
        return entity;
    }

    @Override
    void validate(DataTransferObject dto) {
        logger.info("-----validate created User data-----");
        UserDto userDto = (UserDto) dto;
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

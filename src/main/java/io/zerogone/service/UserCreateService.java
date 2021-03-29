package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.UserCreateDto;
import io.zerogone.model.UserVo;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class UserCreateService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;

    public UserCreateService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserVo createUser(UserCreateDto userCreateDto) {
        validate(userCreateDto);

        User user = new User(userCreateDto);

        try {
            userDao.save(user);
        } catch (PersistenceException persistenceException) {
            logger.error("user property is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("유저의 이메일이나 닉네임이 중복되었습니다");
        }

        return new UserVo(user);
    }

    private void validate(UserCreateDto createDto) {
        logger.info("-----validate created dto-----");
        if (createDto.getName() == null) {
            throw new NotNullPropertyException(User.class, "name");
        }
        if (createDto.getEmail() == null) {
            throw new NotNullPropertyException(User.class, "email");
        }
        if (createDto.getNickName() == null) {
            throw new NotNullPropertyException(User.class, "nickname");
        }
        logger.info("-----validated dto!-----");
    }
}

package io.zerogone.service;

import io.zerogone.model.entity.User;
import io.zerogone.model.UserCreateDto;
import io.zerogone.model.UserVo;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserCreateService {
    private final UserDao userDao;

    public UserCreateService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserVo createUser(UserCreateDto userCreateDto) {
        User user = new User(userCreateDto);
        userDao.save(user);
        return new UserVo(user);
    }
}

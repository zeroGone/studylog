package io.zerogone.user.service;

import io.zerogone.user.model.User;
import io.zerogone.user.model.UserCreateDto;
import io.zerogone.user.model.UserVo;
import io.zerogone.user.repository.UserDao;
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

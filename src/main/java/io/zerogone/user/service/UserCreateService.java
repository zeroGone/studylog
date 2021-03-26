package io.zerogone.user.service;

import io.zerogone.user.model.User;
import io.zerogone.user.model.UserDto;
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
    public UserVo createUser(UserDto userDto) {
        User user = new User(userDto);
        userDao.save(user);
        return new UserVo(user);
    }
}

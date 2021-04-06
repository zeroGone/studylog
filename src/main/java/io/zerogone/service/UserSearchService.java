package io.zerogone.service;

import io.zerogone.model.UserVo;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserSearchService {
    private final UserDao userDao;

    public UserSearchService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserVo getUserByEmail(String email) {
        return new UserVo(userDao.findByEmail(email));
    }
}

package io.zerogone.service;

import io.zerogone.model.UserVo;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSearchService {
    private final UserDao userDao;

    public UserSearchService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserVo getUserHasEmail(String email) throws NoResultException {
        return new UserVo(userDao.findUserByEmail(email));
    }

    public List<UserVo> getUsersByBlogId(int blogId) {
        return userDao.findAllByBlogId(blogId)
                .stream()
                .map(UserVo::new)
                .collect(Collectors.toList());
    }
}

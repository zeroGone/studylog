package io.zerogone.user.service;

import io.zerogone.user.model.User;
import io.zerogone.user.repository.UserSearchDao;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class UserSearchService {
    private final UserSearchDao userSearchDao;

    public UserSearchService(UserSearchDao userSearchDao) {
        this.userSearchDao = userSearchDao;
    }

    public User getUserHasEmail(String email) throws NoResultException {
        return userSearchDao.findUserByEmail(email);
    }
}

package io.zerogone.service;

import io.zerogone.model.BlogVo;
import io.zerogone.model.UserVo;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSearchService {
    private final UserDao userDao;

    public UserSearchService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserVo getUserVoByEmail(String email) {
        User user = userDao.findByEmail(email);
        return new UserVo(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getNickName(),
                user.getImageUrl(),
                user.getCreateDateTime(),
                user.getUpdateDateTime());
    }

    public List<UserVo> getUserVosByBlogVo(BlogVo blogVo) {
        Blog blog = new Blog(blogVo.getId(),
                blogVo.getName(),
                blogVo.getIntroduce(),
                blogVo.getImageUrl());

        List<User> users = userDao.findAllByBlogAndBlogMemberRoleIsAdminOrMember(blog);

        return users.stream()
                .map(user -> new UserVo(user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getNickName(),
                        user.getImageUrl(),
                        user.getCreateDateTime(),
                        user.getUpdateDateTime()))
                .collect(Collectors.toList());
    }
}

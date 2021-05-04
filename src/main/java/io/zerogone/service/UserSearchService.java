package io.zerogone.service;

import io.zerogone.model.vo.BlogVo;
import io.zerogone.model.vo.UserVo;
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
                user.getBlogs()
                        .stream()
                        .map(blog -> new BlogVo(blog.getId(), blog.getName(), blog.getIntroduce(), blog.getImageUrl()))
                        .collect(Collectors.toList()));
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
                        user.getImageUrl()))
                .collect(Collectors.toList());
    }
}

package io.zerogone.service;

import io.zerogone.model.vo.BlogVo;
import io.zerogone.model.vo.UserVo;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogSearchService {
    private final BlogDao blogDao;

    public BlogSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public BlogVo getBlogVoByName(String name) {
        Blog blog = blogDao.findByName(name);
        return new BlogVo(blog.getId(),
                blog.getName(),
                blog.getIntroduce(),
                blog.getImageUrl());
    }

    public List<BlogVo> getBlogVosByUserVo(UserVo userVo) {
        User user = new User(userVo.getId(),
                userVo.getName(),
                userVo.getEmail(),
                userVo.getNickName(),
                userVo.getImageUrl());

        List<Blog> blogs = blogDao.findAllByUserAndBlogMemberRoleIsAdminOrMember(user);

        return blogs.stream().map(blog -> new BlogVo(
                blog.getId(),
                blog.getName(),
                blog.getIntroduce(),
                blog.getImageUrl()))
                .collect(Collectors.toList());
    }
}

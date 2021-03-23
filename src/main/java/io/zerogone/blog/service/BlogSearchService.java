package io.zerogone.blog.service;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogVo;
import io.zerogone.blog.repository.BlogDao;
import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogSearchService {
    private final BlogDao blogDao;

    public BlogSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getBlogsThatUserBelongTo(CurrentUserInfo userInfo) {
        return blogDao.findAllByUser(new User(userInfo));
    }

    public BlogVo getBlog(String name) {
        return new BlogVo(blogDao.findByName(name));
    }
}

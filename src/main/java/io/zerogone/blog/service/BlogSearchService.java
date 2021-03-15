package io.zerogone.blog.service;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.repository.BlogSearchDao;
import io.zerogone.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogSearchService {
    private final BlogSearchDao blogSearchDao;

    public BlogSearchService(BlogSearchDao blogSearchDao) {
        this.blogSearchDao = blogSearchDao;
    }

    public List<Blog> getBlogsThatUserBelongTo(User user) {
        return blogSearchDao.findAllByUser(user);
    }
}

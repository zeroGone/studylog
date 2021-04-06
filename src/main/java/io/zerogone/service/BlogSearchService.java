package io.zerogone.service;

import io.zerogone.model.BlogVo;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchService {
    private final BlogDao blogDao;

    public BlogSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public BlogVo getBlogByName(String name) {
        return new BlogVo(blogDao.findByName(name));
    }
}

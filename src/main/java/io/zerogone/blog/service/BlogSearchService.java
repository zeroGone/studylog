package io.zerogone.blog.service;

import io.zerogone.blog.model.BlogVo;
import io.zerogone.blog.repository.BlogDao;
import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogSearchService {
    private final BlogDao blogDao;

    public BlogSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<BlogVo> getBlogsThatUserBelongTo(CurrentUserInfo userInfo) {
        return blogDao.findAllByUserId(userInfo.getId()).stream().map(BlogVo::new).collect(Collectors.toList());
    }

    public BlogVo getBlog(String name) {
        return new BlogVo(blogDao.findByName(name));
    }
}

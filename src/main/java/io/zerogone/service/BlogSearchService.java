package io.zerogone.service;

import io.zerogone.model.BlogVo;
import io.zerogone.model.CurrentUserInfo;
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

    public List<BlogVo> getBlogsThatUserBelongTo(CurrentUserInfo userInfo) {
        return blogDao.findAllByUserId(userInfo.getId()).stream().map(BlogVo::new).collect(Collectors.toList());
    }

    public BlogVo getBlogByName(String name) {
        return new BlogVo(blogDao.findByName(name));
    }
}

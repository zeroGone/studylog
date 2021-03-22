package io.zerogone.blog.service;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogVo;
import io.zerogone.blog.repository.BlogDao;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.blogmember.service.BlogMemberCreateService;
import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogCreateService {
    private final BlogDao BlogDao;
    private final BlogMemberCreateService blogMemberCreateService;

    public BlogCreateService(BlogDao BlogDao, BlogMemberCreateService blogMemberCreateService) {
        this.BlogDao = BlogDao;
        this.blogMemberCreateService = blogMemberCreateService;
    }

    @Transactional
    public BlogVo createBlog(CurrentUserInfo creator, BlogDto blogDto) throws BlogMembersStateException {
        Blog blog = new Blog(blogDto.getName(), blogDto.getIntroduce(), blogDto.getImageUrl());
        BlogDao.save(blog);

        blogMemberCreateService.createBlogMembers(blog.getId(), creator, blogDto.getMembers());
        return new BlogVo(blog);
    }
}

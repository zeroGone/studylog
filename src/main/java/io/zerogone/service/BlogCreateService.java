package io.zerogone.service;

import io.zerogone.model.entity.Blog;
import io.zerogone.model.BlogDto;
import io.zerogone.model.BlogVo;
import io.zerogone.repository.BlogDao;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.CurrentUserInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogCreateService {
    private final BlogDao blogDao;
    private final BlogMemberCreateService blogMemberCreateService;

    public BlogCreateService(BlogDao blogDao, BlogMemberCreateService blogMemberCreateService) {
        this.blogDao = blogDao;
        this.blogMemberCreateService = blogMemberCreateService;
    }

    @Transactional
    public BlogVo createBlog(CurrentUserInfo creator, BlogDto blogDto, String savedImgUrl) throws BlogMembersStateException {
        Blog blog = new Blog(blogDto.getName(), blogDto.getIntroduce(), savedImgUrl);
        blogDao.save(blog);

        blogMemberCreateService.createBlogMembers(blog.getId(), creator, blogDto.getMembers());
        return new BlogVo(blog);
    }
}

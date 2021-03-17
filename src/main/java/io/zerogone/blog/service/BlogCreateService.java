package io.zerogone.blog.service;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.*;
import io.zerogone.blog.repository.BlogSaveDao;
import io.zerogone.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogCreateService {
    private final BlogSaveDao blogSaveDao;

    public BlogCreateService(BlogSaveDao blogSaveDao) {
        this.blogSaveDao = blogSaveDao;
    }

    @Transactional
    public BlogVo createBlog(User creator, BlogDto blogDto) throws InvalidBlogMemberException {
        Blog blog = convertToBlogEntity(blogDto);
        List<BlogMember> blogMembers = convertToBlogMemberEntities(creator, blogDto.getMembers());
        return convertToVo(blogSaveDao.save(blog, blogMembers));
    }

    private Blog convertToBlogEntity(BlogDto blogDto) {
        return new Blog(blogDto.getName(), blogDto.getIntroduce(), blogDto.getImageUrl());
    }

    private List<BlogMember> convertToBlogMemberEntities(User creator, List<User> members) {
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(new BlogMember(creator.getId(), MemberRole.ADMIN));

        if (members != null) {
            blogMembers.addAll(members
                    .stream()
                    .map(user -> new BlogMember(user.getId(), MemberRole.INVITING))
                    .collect(Collectors.toList()));
        }
        return blogMembers;
    }

    private BlogVo convertToVo(Blog blog) {
        return new BlogVo(blog.getId(), blog.getName(), blog.getIntroduce(), blog.getImgUrl());
    }
}

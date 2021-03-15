package io.zerogone.blog.service;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.MemberRole;
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
    public Blog createBlog(User creator, BlogDto blogDto) throws InvalidBlogMemberException {
        Blog blog = convertToBlogEntity(blogDto);

        List<BlogMember> members = new ArrayList<>();
        members.add(convertToBlogAdmin(creator));

        if (blogDto.getMembers() != null) {
            members.addAll(convertToBlogMembers(blogDto.getMembers()));
        }

        return blogSaveDao.save(blog, members);
    }

    private Blog convertToBlogEntity(BlogDto blogDto) {
        return new Blog(blogDto.getName(), blogDto.getIntroduce(), blogDto.getImageUrl());
    }

    private BlogMember convertToBlogAdmin(User user) {
        return new BlogMember(user.getId(), MemberRole.ADMIN);
    }

    private List<BlogMember> convertToBlogMembers(List<User> users) {
        return users
                .stream()
                .map(user -> new BlogMember(user.getId(), MemberRole.INVITING))
                .collect(Collectors.toList());
    }
}

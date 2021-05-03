package io.zerogone.converter;

import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.User;

public class BlogMemberEntityConverter implements Converter<BlogMember, BlogMemberDto> {
    private final BlogEntityConverter blogEntityConverter;
    private final UserEntityConverter userEntityConverter;

    public BlogMemberEntityConverter(BlogEntityConverter blogEntityConverter, UserEntityConverter userEntityConverter) {
        this.blogEntityConverter = blogEntityConverter;
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public BlogMemberDto convert(BlogMember key) {
        BlogMemberDto dto = new BlogMemberDto();
        Blog blog = new Blog(key.getBlogId(), key.getBlogName(), key.getBlogIntroduce(), key.getBlogImageUrl());
        User user = new User(key.getUserId(), key.getName(), key.getEmail(), key.getNickName(), key.getImageUrl());

        dto.setId(key.getId());
        dto.setBlog(blogEntityConverter.convert(blog));
        dto.setUser(userEntityConverter.convert(user));
        dto.setRole(key.getRole());
        return dto;
    }
}

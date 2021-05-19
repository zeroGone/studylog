package io.zerogone.converter;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogMemberDtoToEntityConverter implements Converter<BlogMemberDto, BlogMember> {
    @Override
    public BlogMember convert(BlogMemberDto memberDto) {
        User user = new User(memberDto.getId(), memberDto.getName(), memberDto.getEmail(), memberDto.getNickName(), memberDto.getImageUrl());
        Blog blog = new Blog(memberDto.getBlogId(), null, null, null, null);
        return new BlogMember(user, blog, memberDto.getRole());
    }
}

package io.zerogone.blog.converter;

import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.blog.model.BlogMember;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogMemberEntityToDtoConverter implements Converter<BlogMember, BlogMemberDto> {
    @Override
    public BlogMemberDto convert(BlogMember member) {
        BlogMemberDto memberDto = new BlogMemberDto();
        memberDto.setId(member.getUserId());
        memberDto.setBlogId(member.getBlogId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setNickName(member.getNickName());
        memberDto.setImageUrl(member.getImageUrl());
        memberDto.setRole(member.getRole());
        return memberDto;
    }
}

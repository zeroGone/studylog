package io.zerogone.converter;

import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.BlogMember;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogMemberEntityToDtoConverter implements Converter<BlogMember, BlogMemberDto> {
    @Override
    public BlogMemberDto convert(BlogMember member) {
        BlogMemberDto memberDto = new BlogMemberDto();
        memberDto.setId(member.getUserId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setNickName(member.getNickName());
        memberDto.setImageUrl(member.getImageUrl());
        memberDto.setRole(member.getRole());
        return memberDto;
    }
}

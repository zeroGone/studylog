package io.zerogone.converter;

import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public abstract class UserDtoToBlogMemberDtoConverter implements Converter<UserDto, BlogMemberDto> {
    @Override
    public BlogMemberDto convert(UserDto userDto) {
        BlogMemberDto memberDto = new BlogMemberDto();
        memberDto.setId(userDto.getId());
        memberDto.setImageUrl(userDto.getImageUrl());
        memberDto.setEmail(userDto.getEmail());
        memberDto.setName(userDto.getName());
        memberDto.setNickName(userDto.getNickName());
        setRole(memberDto);
        return memberDto;
    }

    abstract void setRole(BlogMemberDto memberDto);
}

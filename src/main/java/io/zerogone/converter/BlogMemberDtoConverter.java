package io.zerogone.converter;

import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.BlogMember;

public class BlogMemberDtoConverter implements Converter<BlogMemberDto, BlogMember> {
    private final UserDtoConverter userDtoConverter;
    private final BlogDtoConverter blogDtoConverter;

    public BlogMemberDtoConverter(UserDtoConverter userDtoConverter, BlogDtoConverter blogDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.blogDtoConverter = blogDtoConverter;
    }

    @Override
    public BlogMember convert(BlogMemberDto key) {
        return new BlogMember(
                userDtoConverter.convert(key.getUser()),
                blogDtoConverter.convert(key.getBlog()),
                key.getRole());
    }
}

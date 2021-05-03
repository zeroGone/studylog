package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BlogMemberConverter implements Converter<BlogMember> {
    private final UserConverter userConverter;
    private final BlogConverter blogConverter;

    public BlogMemberConverter(UserConverter userConverter, BlogConverter blogConverter) {
        this.userConverter = userConverter;
        this.blogConverter = blogConverter;
    }

    @Override
    public BlogMember convert(DataTransferObject dto) {
        BlogMemberDto blogMemberDto = (BlogMemberDto) dto;
        return new BlogMember(
                userConverter.convert(blogMemberDto.getUser()),
                blogConverter.convert(blogMemberDto.getBlog()),
                blogMemberDto.getRole());
    }

    @Override
    public DataTransferObject convert(BlogMember entity) {
        BlogMemberDto dto = new BlogMemberDto();
        Blog blog = new Blog(entity.getBlogId(), entity.getBlogName(), entity.getBlogIntroduce(), entity.getBlogImageUrl());
        User user = new User(entity.getUserId(), entity.getName(), entity.getEmail(), entity.getNickName(), entity.getImageUrl());

        dto.setId(entity.getId());
        dto.setBlog((BlogDto) blogConverter.convert(blog));
        dto.setUser((UserDto) userConverter.convert(user));
        dto.setRole(entity.getRole());
        return dto;
    }
}

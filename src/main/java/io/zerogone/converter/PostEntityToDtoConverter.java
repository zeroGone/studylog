package io.zerogone.converter;

import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostEntityToDtoConverter implements Converter<Post, PostDto> {
    @Override
    public PostDto convert(Post entity) {
        PostDto postDto = new PostDto();
        postDto.setId(entity.getId());
        postDto.setTitle(entity.getTitle());
        postDto.setContents(entity.getContents());
        postDto.setHits(entity.getHits());
        postDto.setCreateDate(entity.getCreateDateTime().toLocalDate());
        postDto.setCategories(entity.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        postDto.setWriter(convertWriter(entity.getWriter()));
        return postDto;
    }

    private UserDto convertWriter(User writer) {
        UserDto dto = new UserDto();
        dto.setId(writer.getId());
        dto.setEmail(writer.getEmail());
        dto.setName(writer.getName());
        dto.setNickName(writer.getNickName());
        dto.setImageUrl(writer.getImageUrl());
        return dto;
    }
}

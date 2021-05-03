package io.zerogone.converter;

import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostEntityConverter implements Converter<Post, PostDto> {
    private final UserEntityConverter userEntityConverter;
    private final BlogEntityConverter blogEntityConverter;

    public PostEntityConverter(UserEntityConverter userEntityConverter, BlogEntityConverter blogEntityConverter) {
        this.userEntityConverter = userEntityConverter;
        this.blogEntityConverter = blogEntityConverter;
    }

    @Override
    public PostDto convert(Post key) {
        PostDto postDto = new PostDto();
        postDto.setId(key.getId());
        postDto.setTitle(key.getTitle());
        postDto.setContents(key.getContents());
        postDto.setWriter(userEntityConverter.convert(key.getWriter()));
        postDto.setBlog(blogEntityConverter.convert(key.getBlog()));
        postDto.setCategories(key.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        return postDto;
    }
}

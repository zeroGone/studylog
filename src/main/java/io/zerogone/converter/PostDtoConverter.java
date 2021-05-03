package io.zerogone.converter;

import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoConverter implements Converter<PostDto, Post> {
    private final UserDtoConverter userDtoConverter;
    private final BlogDtoConverter blogDtoConverter;

    public PostDtoConverter(UserDtoConverter userDtoConverter, BlogDtoConverter blogDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.blogDtoConverter = blogDtoConverter;
    }

    @Override
    public Post convert(PostDto key) {
        User writer = userDtoConverter.convert(key.getWriter());
        Blog blog = blogDtoConverter.convert(key.getBlog());
        List<Category> categorieEntities = key.getCategories().stream().map(Category::new).collect(Collectors.toList());
        return new Post(key.getTitle(), key.getContents(), writer, blog, categorieEntities);
    }
}

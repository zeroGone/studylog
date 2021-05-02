package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostConverter implements Converter<Post> {
    private final UserConverter userConverter;
    private final BlogConverter blogConverter;

    public PostConverter(UserConverter userConverter, BlogConverter blogConverter) {
        this.userConverter = userConverter;
        this.blogConverter = blogConverter;
    }

    @Override
    public Post convert(DataTransferObject dto) {
        PostDto postDto = (PostDto) dto;

        String title = postDto.getTitle();
        String contents = postDto.getContents();

        User writer = userConverter.convert(postDto.getWriter());
        Blog blog = blogConverter.convert(postDto.getBlog());

        List<Category> categorieEntities = postDto.getCategories()
                .stream()
                .map(Category::new)
                .collect(Collectors.toList());

        return new Post(postDto.getTitle(), postDto.getContents(), writer, blog, categorieEntities);
    }

    @Override
    public DataTransferObject convert(Post entity) {
        PostDto postDto = new PostDto();
        postDto.setId(entity.getId());
        postDto.setTitle(entity.getTitle());
        postDto.setContents(entity.getContents());

        postDto.setWriter((UserDto) userConverter.convert(entity.getWriter()));
        postDto.setBlog((BlogDto) blogConverter.convert(entity.getBlog()));
        postDto.setCategories(entity.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        return postDto;
    }
}

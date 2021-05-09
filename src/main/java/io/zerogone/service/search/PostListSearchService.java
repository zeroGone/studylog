package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import io.zerogone.repository.PostDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListSearchService implements SearchService<String, List<PostDto>> {
    private final PostDao postDao;
    private final Converter<User, UserDto> userDtoConvert;

    public PostListSearchService(PostDao postDao, Converter<User, UserDto> userDtoConvert) {
        this.postDao = postDao;
        this.userDtoConvert = userDtoConvert;
    }

    @Override
    public List<PostDto> search(String blogName) {
        List<Post> entities = postDao.findAllByBlogName(blogName);

        List<PostDto> dtos = new ArrayList<>();
        for (Post entity : entities) {
            PostDto postDto = new PostDto();
            postDto.setId(entity.getId());
            postDto.setTitle(entity.getTitle());
            String contents = entity.getContents();
            if (contents.length() > 200) {
                contents = contents.substring(0, 200);
            }
            postDto.setContents(contents);
            postDto.setHits(entity.getHits());
            postDto.setCreateDateTime(entity.getCreateDateTime());
            postDto.setCategories(entity.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
            postDto.setWriter(userDtoConvert.convert(entity.getWriter()));
            dtos.add(postDto);
        }
        return dtos;
    }
}

package io.zerogone.service.search;

import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.PostDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListSearchService implements SearchService<String, List<PostDto>> {
    private final PostDao postDao;
    private final Converter<Post, PostDto> converter;

    public PostListSearchService(PostDao postDao, Converter<Post, PostDto> converter) {
        this.postDao = postDao;
        this.converter = converter;
    }

    @Override
    public List<PostDto> search(String blogName) {
        List<Post> entities = postDao.findAllByBlogName(blogName);
        return entities.stream().map(converter::convert).collect(Collectors.toList());
    }
}

package io.zerogone.service.search;

import io.zerogone.model.Name;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.PostDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListSearchService implements SearchService<Name, List<PostDto>> {
    private final PostDao postDao;
    private final Converter<Post, PostDto> converter;

    public PostListSearchService(PostDao postDao, Converter<Post, PostDto> converter) {
        this.postDao = postDao;
        this.converter = converter;
    }

    @Override
    public List<PostDto> search(Name blogName) {
        List<Post> entities = postDao.findAllByBlogName(blogName.getValue());
        return entities.stream().map(converter::convert).collect(Collectors.toList());
    }
}

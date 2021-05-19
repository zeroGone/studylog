package io.zerogone.blog.post.service;

import io.zerogone.blog.model.BlogName;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.blog.post.model.Post;
import io.zerogone.blog.post.PostDao;
import io.zerogone.common.service.SearchService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListSearchService implements SearchService<BlogName, List<PostDto>> {
    private final PostDao postDao;
    private final ConversionService conversionService;

    public PostListSearchService(PostDao postDao, ConversionService conversionService) {
        this.postDao = postDao;
        this.conversionService = conversionService;
    }

    @Override
    public List<PostDto> search(BlogName blogName) {
        List<Post> entities = postDao.findAllByBlogName(blogName.get());
        return entities.stream()
                .map(post -> {
                    PostDto dto = conversionService.convert(post, PostDto.class);
                    dto.setCategories(post.getCategories()
                            .stream()
                            .map(category -> conversionService.convert(category, String.class))
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

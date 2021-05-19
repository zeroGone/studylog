package io.zerogone.service.search;

import io.zerogone.blog.model.BlogName;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.PostDao;
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

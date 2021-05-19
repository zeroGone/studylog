package io.zerogone.blog.post.service;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.post.model.Post;
import io.zerogone.blog.post.PostDao;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class PostSearchService implements SearchService<Integer, PostDto> {
    private final PostDao postDao;
    private final ConversionService conversionService;

    public PostSearchService(PostDao postDao, ConversionService conversionService) {
        this.postDao = postDao;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public PostDto search(Integer id) {
        Post entity = postDao.findById(id);
        if (entity == null) {
            throw new NotExistDataException("해당 아이디를 가진 게시글이 없습니다", id);
        }
        entity.hit();

        PostDto dto = conversionService.convert(entity, PostDto.class);
        dto.setCategories(entity.getCategories()
                .stream()
                .map(category -> conversionService.convert(category, String.class))
                .collect(Collectors.toList()));
        dto.setWriter(conversionService.convert(entity.getWriter(), UserDto.class));
        return dto;
    }
}

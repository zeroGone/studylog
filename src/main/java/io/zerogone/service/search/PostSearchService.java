package io.zerogone.service.search;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.PostDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostSearchService implements SearchService<Integer, PostDto> {
    private final PostDao postDao;
    private final Converter<Post, PostDto> converter;

    public PostSearchService(PostDao postDao, Converter<Post, PostDto> converter) {
        this.postDao = postDao;
        this.converter = converter;
    }

    @Transactional
    @Override
    public PostDto search(Integer id) {
        Post entity = postDao.findById(id);
        if (entity == null) {
            throw new NotExistedDataException(Post.class, "id로 게시글 검색", id.toString());
        }
        entity.hit();
        return converter.convert(entity);
    }
}

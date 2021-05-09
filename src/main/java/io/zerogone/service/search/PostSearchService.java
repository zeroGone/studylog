package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import io.zerogone.repository.PostDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class PostSearchService implements SearchService<Integer, PostDto> {
    private final PostDao postDao;
    private final Converter<User, UserDto> userDtoConvert;

    public PostSearchService(PostDao postDao, Converter<User, UserDto> userDtoConvert) {
        this.postDao = postDao;
        this.userDtoConvert = userDtoConvert;
    }

    @Transactional
    @Override
    public PostDto search(Integer id) {
        Post entity = postDao.findById(id);
        if (entity == null) {
            throw new NotExistedDataException(Post.class, "id로 게시글 검색", Integer.toString(id));
        }
        entity.hit();
        PostDto postDto = new PostDto();
        postDto.setId(entity.getId());
        postDto.setTitle(entity.getTitle());
        postDto.setContents(entity.getContents());
        postDto.setHits(entity.getHits());
        postDto.setCreateDateTime(entity.getCreateDateTime());
        postDto.setCategories(entity.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        postDto.setWriter(userDtoConvert.convert(entity.getWriter()));
        return postDto;
    }
}

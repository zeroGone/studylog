package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import io.zerogone.repository.CategoryDao;
import io.zerogone.repository.PostDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostCreateService implements CreateService<PostDto> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final PostDao postDao;
    private final CategoryDao categoryDao;

    public PostCreateService(PostDao postDao, CategoryDao categoryDao) {
        this.postDao = postDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public PostDto create(PostDto dto) {
        validate(dto);
        Post entity = convert(dto);
        postDao.save(entity);
        return dto;
    }

    private void validate(PostDto dto) {
        logger.info("-----Validating post data start-----");
        if (dto.getTitle() == null) {
            throw new NotNullPropertyException(Post.class, "title");
        }
        if (dto.getContents() == null) {
            throw new NotNullPropertyException(Post.class, "contents");
        }
        logger.info("-----Validating post data is ended-----");
    }

    private Post convert(PostDto dto) {
        UserDto writer = dto.getWriter();
        User user = new User(writer.getId(), writer.getName(), writer.getEmail(), writer.getNickName(), writer.getImageUrl());

        BlogDto blogDto = dto.getBlog();
        Blog blog = new Blog(blogDto.getId(), blogDto.getName(), blogDto.getIntroduce(), blogDto.getImageUrl());

        List<Category> categories = saveCategories(dto.getCategories());
        return new Post(dto.getTitle(), dto.getContents(), user, blog, categories);
    }

    private List<Category> saveCategories(List<String> categories) {
        List<Category> persistedCategories = new ArrayList<>();
        for (String category : categories) {
            Category entity = new Category(category);
            try {
                entity = categoryDao.findByName(category);
            } catch (NotExistedDataException notExistedDataException) {
                categoryDao.save(entity);
            }
            persistedCategories.add(entity);
        }
        return persistedCategories;
    }
}

package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.CategoryDao;
import io.zerogone.repository.PostDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostCreateTemplate extends CreateTemplate<Post> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final PostDao postDao;
    private final CategoryDao categoryDao;

    public PostCreateTemplate(Converter<Post> converter, PostDao postDao, CategoryDao categoryDao) {
        super(converter);
        this.postDao = postDao;
        this.categoryDao = categoryDao;
    }

    @Override
    Post saveEntity(Post entity) {
        entity.setCategories(persistCategories(entity.getCategories()));
        postDao.save(entity);
        return entity;
    }

    @Override
    void validate(DataTransferObject dto) {
        logger.info("-----Validating post data start-----");
        PostDto postDto = (PostDto) dto;

        if (postDto.getTitle() == null) {
            throw new NotNullPropertyException(Post.class, "title");
        }
        if (postDto.getContents() == null) {
            throw new NotNullPropertyException(Post.class, "contents");
        }
        logger.info("-----Validating post data is ended-----");
    }

    private List<Category> persistCategories(List<Category> categories) {
        List<Category> persistedCategories = new ArrayList<>();
        for (Category category : categories) {
            try {
                category = categoryDao.findByName(category.getName());
            } catch (NotExistedDataException notExistedDataException) {
                categoryDao.save(category);
            }
            persistedCategories.add(category);
        }
        return persistedCategories;
    }
}

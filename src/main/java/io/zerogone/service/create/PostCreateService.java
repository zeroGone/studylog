package io.zerogone.service.create;

import io.zerogone.model.dto.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.repository.CategoryDao;
import io.zerogone.repository.PostDao;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCreateService implements CreateService<PostDto> {
    private final PostDao postDao;
    private final CategoryDao categoryDao;
    private final ConversionService conversionService;

    public PostCreateService(PostDao postDao, CategoryDao categoryDao, ConversionService conversionService) {
        this.postDao = postDao;
        this.categoryDao = categoryDao;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public PostDto create(PostDto dto) {
        Post entity = conversionService.convert(dto, Post.class);
        postDao.save(entity);

        setCategories(entity, dto.getCategories());

        PostDto createdPostDto = conversionService.convert(entity, PostDto.class);
        createdPostDto.setWriter(conversionService.convert(entity.getWriter(), UserDto.class));
        createdPostDto.setCategories(entity.getCategories()
                .stream()
                .map(category -> conversionService.convert(category, String.class))
                .collect(Collectors.toList()));
        return createdPostDto;
    }

    private void setCategories(Post post, List<String> categories) {
        for (String categoryName : categories) {
            Category category;
            try {
                category = categoryDao.findByName(categoryName);
            } catch (NoResultException noResultException) {
                category = conversionService.convert(categoryName, Category.class);
                categoryDao.save(category);
            }
            post.addCategory(category);
        }
    }
}

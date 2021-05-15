package io.zerogone.service.search;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.model.BlogName;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class BlogSearchService implements SearchService<BlogName, BlogDto> {
    private final BlogDao blogDao;
    private final Converter<Blog, BlogDto> converter;

    public BlogSearchService(BlogDao blogDao, Converter<Blog, BlogDto> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Override
    public BlogDto search(BlogName blogName) {
        try {
            Blog entity = blogDao.findByName(blogName.get());
            return converter.convert(entity);
        } catch (NoResultException noResultException) {
            throw new NotExistDataException("검색한 블로그가 없습니다", blogName.get());
        }
    }
}

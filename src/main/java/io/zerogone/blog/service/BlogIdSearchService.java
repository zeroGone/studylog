package io.zerogone.blog.service;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.BlogDao;
import io.zerogone.service.search.SearchService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class BlogIdSearchService implements SearchService<Integer, BlogDto> {
    private final BlogDao blogDao;
    private final ConversionService conversionService;

    public BlogIdSearchService(BlogDao blogDao, ConversionService conversionService) {
        this.blogDao = blogDao;
        this.conversionService = conversionService;
    }

    @Override
    public BlogDto search(Integer id) {
        Blog entity = blogDao.findById(id);
        if (entity == null) {
            throw new NotExistDataException("검색한 블로그가 없습니다", id);
        }
        return conversionService.convert(entity, BlogDto.class);
    }
}

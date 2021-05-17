package io.zerogone.service.search;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
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

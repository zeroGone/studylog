package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;


@Service
public class BlogNameSearchService implements SearchService<String, BlogDto> {
    private final BlogDao blogDao;
    private final Converter<Blog> converter;

    public BlogNameSearchService(BlogDao blogDao, Converter<Blog> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Override
    public BlogDto search(String key) {
        Blog entity = blogDao.findByName(key);
        return (BlogDto) converter.convert(entity);
    }
}

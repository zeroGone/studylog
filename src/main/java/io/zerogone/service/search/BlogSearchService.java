package io.zerogone.service.search;

import io.zerogone.model.Name;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchService implements SearchService<Name, BlogDto> {
    private final BlogDao blogDao;
    private final Converter<Blog, BlogDto> converter;

    public BlogSearchService(BlogDao blogDao, Converter<Blog, BlogDto> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Override
    public BlogDto search(Name name) {
        return converter.convert(blogDao.findByName(name.getValue()));
    }
}

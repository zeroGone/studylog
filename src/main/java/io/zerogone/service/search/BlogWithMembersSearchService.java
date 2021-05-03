package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogWithMembersDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

@Service
public class BlogWithMembersSearchService implements SearchService<String, BlogWithMembersDto> {
    private final BlogDao blogDao;
    private final Converter<Blog, BlogDto> converter;

    public BlogWithMembersSearchService(BlogDao blogDao, Converter<Blog, BlogDto> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Override
    public BlogWithMembersDto search(String key) {
        return (BlogWithMembersDto) converter.convert(blogDao.findWithBlogMembersByName(key));
    }
}

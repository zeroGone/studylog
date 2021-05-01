package io.zerogone.service.search;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

@Service
public class BlogNameSearchService implements SearchService<String, BlogDto> {
    private final BlogDao blogDao;

    public BlogNameSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public BlogDto search(String key) {
        Blog entity = blogDao.findByName(key);
        BlogDto dto = new BlogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }
}

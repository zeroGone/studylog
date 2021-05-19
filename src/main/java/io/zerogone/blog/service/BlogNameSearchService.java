package io.zerogone.blog.service;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.blog.model.BlogName;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.BlogDao;
import io.zerogone.service.search.SearchService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@Service
public class BlogNameSearchService implements SearchService<BlogName, BlogDto> {
    private final BlogDao blogDao;
    private final ConversionService conversionService;

    public BlogNameSearchService(BlogDao blogDao, ConversionService conversionService) {
        this.blogDao = blogDao;
        this.conversionService = conversionService;
    }

    @Override
    public BlogDto search(BlogName blogName) {
        try {
            Blog entity = blogDao.findByName(blogName.get());
            BlogDto blogDto = conversionService.convert(entity, BlogDto.class);
            blogDto.setMembers(entity.getMembers()
                    .stream()
                    .map(member -> conversionService.convert(member, BlogMemberDto.class))
                    .collect(Collectors.toList()));
            return blogDto;
        } catch (NoResultException noResultException) {
            throw new NotExistDataException("검색한 블로그가 없습니다", blogName.get());
        }
    }
}

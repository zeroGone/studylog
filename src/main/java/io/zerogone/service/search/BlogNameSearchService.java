package io.zerogone.service.search;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.model.BlogName;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
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

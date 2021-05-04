package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogCreateService implements CreateService<BlogDto> {
    private static final String BLOG_DEFAULT_IMAGE_URL = "/img/blog-default.png";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogDao blogDao;
    private final CreateService<List<BlogMemberDto>> membersCreateService;
    private final Converter<Blog, BlogDto> entityConverter;

    public BlogCreateService(BlogDao blogDao,
                             CreateService<List<BlogMemberDto>> membersCreateService,
                             Converter<Blog, BlogDto> entityConverter) {
        this.blogDao = blogDao;
        this.membersCreateService = membersCreateService;
        this.entityConverter = entityConverter;
    }

    @Transactional
    @Override
    public BlogDto create(BlogDto dto) {
        if (dto.getName() == null) {
            throw new NotNullPropertyException(Blog.class, "name");
        }
        if (dto.getImageUrl() == null) {
            dto.setImageUrl(BLOG_DEFAULT_IMAGE_URL);
        }
        Blog entity = new Blog(dto.getName(), dto.getIntroduce(), dto.getImageUrl());
        try {
            blogDao.save(entity);
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            logger.error("Blog name is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }

        setMembersBlog(entity, dto.getMembers());
        membersCreateService.create(dto.getMembers());
        return entityConverter.convert(entity);
    }

    private void setMembersBlog(Blog blog, List<BlogMemberDto> blogMemberDtos) {
        blogMemberDtos.forEach(blogMemberDto -> {
            blogMemberDto.setBlogId(blog.getId());
            blogMemberDto.setBlogName(blog.getName());
        });
    }
}

package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class BlogCreateTemplate extends CreateTemplate<Blog> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogDao blogDao;

    public BlogCreateTemplate(Converter<Blog> converter, BlogDao blogDao) {
        super(converter);
        this.blogDao = blogDao;
    }

    @Override
    Blog saveEntity(Blog entity) {
        try {
            blogDao.save(entity);
        } catch (PersistenceException persistenceException) {
            logger.error("Blog name is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }
        return entity;
    }

    @Override
    void validate(DataTransferObject dto) {
        BlogDto blogDto = (BlogDto) dto;
        logger.info("-----validate created Blog data-----");
        if (blogDto.getName() == null) {
            logger.error(blogDto.getName());
            throw new NotNullPropertyException(Blog.class, "name");
        }
        logger.info("-----validated Blog data!-----");
    }
}

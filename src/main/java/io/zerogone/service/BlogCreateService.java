package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.BlogCreateDto;
import io.zerogone.model.BlogVo;
import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class BlogCreateService {
    private static final String imageFilePath = "img/blog";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final FileUploadService fileUploadService;
    private final BlogDao blogDao;
    private final BlogMemberCreateService blogMemberCreateService;

    public BlogCreateService(FileUploadService fileUploadService, BlogDao blogDao, BlogMemberCreateService blogMemberCreateService) {
        this.fileUploadService = fileUploadService;
        this.blogDao = blogDao;
        this.blogMemberCreateService = blogMemberCreateService;
    }

    @Transactional
    public BlogVo createBlog(CurrentUserInfo creator, BlogCreateDto blogCreateDto) {
        validate(blogCreateDto);

        String uploadedImgUrl = fileUploadService.uploadFile(imageFilePath, blogCreateDto.getImage());

        Blog blog = new Blog(blogCreateDto.getName(), blogCreateDto.getIntroduce(), uploadedImgUrl);

        try {
            blogDao.save(blog);
        } catch (PersistenceException persistenceException) {
            logger.error("Blog name is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }

        blogMemberCreateService.createBlogMembers(blog, creator, blogCreateDto.getMembers());
        return new BlogVo(blog);
    }

    private void validate(BlogCreateDto blogCreateDto) {
        logger.info("-----validate created Blog data-----");
        if (blogCreateDto.getName() == null) {
            logger.error(blogCreateDto.getName());
            throw new NotNullPropertyException(Blog.class, "name");
        }
        logger.info("-----validated Blog data!-----");
    }
}

package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.BlogDto;
import io.zerogone.model.BlogVo;
import io.zerogone.model.UserVo;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class BlogCreateService {
    private static final String BLOG_IMAGE_FILE_PATH = "img/blog";
    private static final String BLOG_DEFAULT_IMAGE_URL = "/img/blog-default.png";

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
    public BlogVo createBlog(UserVo creator, BlogDto blogDto, MultipartFile imageFile) {
        validate(blogDto);
        String blogImageUrl = uploadBlogImage(imageFile);
        Blog blog = createBlog(blogDto, blogImageUrl);

        blogMemberCreateService.createBlogMembers(blog, creator, blogDto.getMembers());
        return new BlogVo(blog.getId(), blog.getName(), blog.getIntroduce(), blog.getImageUrl(), blog.getCreateDateTime(), blog.getUpdateDateTime());
    }

    private void validate(BlogDto blogCreateDto) {
        logger.info("-----validate created Blog data-----");
        if (blogCreateDto.getName() == null) {
            logger.error(blogCreateDto.getName());
            throw new NotNullPropertyException(Blog.class, "name");
        }
        logger.info("-----validated Blog data!-----");
    }

    private String uploadBlogImage(MultipartFile imageFile) {
        String uploadedImageUrl = fileUploadService.uploadFile(BLOG_IMAGE_FILE_PATH, imageFile);

        if (uploadedImageUrl == null) {
            return BLOG_DEFAULT_IMAGE_URL;
        } else {
            return uploadedImageUrl;
        }
    }

    private Blog createBlog(BlogDto blogDto, String imageUrl) {
        Blog entity = new Blog(0, blogDto.getName(), blogDto.getIntroduce(), imageUrl);

        try {
            blogDao.save(entity);
        } catch (PersistenceException persistenceException) {
            logger.error("Blog name is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }

        return entity;
    }
}

package io.zerogone.blog.controller;

import ch.qos.logback.classic.Logger;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.ErrorResponse;
import io.zerogone.service.FileUploadService;
import io.zerogone.user.model.CurrentUserInfo;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BlogCreateController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogCreateService blogCreateService;
    private final FileUploadService fileUploadService;

    public BlogCreateController(BlogCreateService blogCreateService, FileUploadService fileUploadService) {
        this.blogCreateService = blogCreateService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<Object> handleBlogCreateApi(@SessionAttribute(name = "userInfo") CurrentUserInfo userInfo,
                                                      @ModelAttribute BlogDto blog,
                                                      HttpServletRequest httpServletRequest) {
        logger.info("-----create blog start-----");
        logger.debug(String.format("received blog data { name : %s , introduce : %s, image : %s }"
                , blog.getName(), blog.getIntroduce(), blog.getName()));

        String savedImageUrl = fileUploadService.uploadFile(httpServletRequest.getServletContext().getRealPath("/"), blog.getImage());
        try {
            return new ResponseEntity<>(blogCreateService.createBlog(userInfo, blog, savedImageUrl), HttpStatus.CREATED);
        } catch (BlogMembersStateException | UniquePropertyException exception) {
            return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

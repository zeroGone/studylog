package io.zerogone.controller;

import ch.qos.logback.classic.Logger;
import io.zerogone.service.BlogCreateService;
import io.zerogone.model.BlogDto;
import io.zerogone.model.BlogVo;
import io.zerogone.service.FileUploadService;
import io.zerogone.model.CurrentUserInfo;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ResponseEntity<BlogVo> handleBlogCreateApi(@SessionAttribute(name = "userInfo") CurrentUserInfo userInfo,
                                                      @ModelAttribute BlogDto blog,
                                                      HttpServletRequest httpServletRequest) throws IOException {
        logger.info("-----create blog start-----");
        logger.debug(String.format("received blog data { name : %s , introduce : %s, image : %s }"
                , blog.getName(), blog.getIntroduce(), blog.getName()));

        String savedImageUrl = fileUploadService.uploadFile(httpServletRequest.getServletContext().getRealPath("/"), blog.getImage());
        return new ResponseEntity<>(blogCreateService.createBlog(userInfo, blog, savedImageUrl), HttpStatus.CREATED);
    }
}

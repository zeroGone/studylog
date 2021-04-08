package io.zerogone.controller.api;

import io.zerogone.model.BlogDto;
import io.zerogone.model.BlogVo;
import io.zerogone.model.UserVo;
import io.zerogone.service.BlogCreateService;
import io.zerogone.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BlogCreateController {
    private final BlogCreateService blogCreateService;
    private final FileUploadService fileUploadService;

    public BlogCreateController(BlogCreateService blogCreateService, FileUploadService fileUploadService) {
        this.blogCreateService = blogCreateService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<BlogVo> handleBlogCreateApi(@SessionAttribute UserVo userInfo,
                                                      @ModelAttribute BlogDto blogDto,
                                                      @RequestPart(required = false) MultipartFile image) {

        return new ResponseEntity<>(blogCreateService.createBlog(userInfo, blogDto, image), HttpStatus.CREATED);
    }
}

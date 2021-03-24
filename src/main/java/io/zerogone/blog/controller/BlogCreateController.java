package io.zerogone.blog.controller;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.ErrorResponse;
import io.zerogone.service.FileUploadService;
import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BlogCreateController {
    private final BlogCreateService blogCreateService;
    private final FileUploadService fileUploadService;

    public BlogCreateController(BlogCreateService blogCreateService, FileUploadService fileUploadService) {
        this.blogCreateService = blogCreateService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<Object> handleBlogCreateApi(@SessionAttribute(name = "userInfo") CurrentUserInfo userInfo,
                                                      @ModelAttribute BlogDto blogDto,
                                                      HttpServletRequest httpServletRequest) {
        String savedImageUrl = fileUploadService.uploadFile(httpServletRequest.getServletContext().getRealPath("/"), blogDto.getImage());
        try {
            return new ResponseEntity<>(blogCreateService.createBlog(userInfo, blogDto, savedImageUrl), HttpStatus.CREATED);
        } catch (BlogMembersStateException | UniquePropertyException exception) {
            return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

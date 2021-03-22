package io.zerogone.blog.controller;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.Error;
import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class BlogCreateController {
    private final BlogCreateService blogCreateService;

    public BlogCreateController(BlogCreateService blogCreateService) {
        this.blogCreateService = blogCreateService;
    }

    @PostMapping(value = "api/blog")
    public ResponseEntity<Object> handleBlogCreateApi(@SessionAttribute(name = "userInfo") CurrentUserInfo userInfo,
                                                      @RequestBody BlogDto blog) {
        try {
            return new ResponseEntity<>(blogCreateService.createBlog(userInfo, blog), HttpStatus.CREATED);
        } catch (BlogMembersStateException | UniquePropertyException exception) {
            return new ResponseEntity<>(new Error(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

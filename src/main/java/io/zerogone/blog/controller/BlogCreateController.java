package io.zerogone.blog.controller;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.model.User;
import io.zerogone.model.Error;
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
    public ResponseEntity<Object> handleBlogCreateApi(@SessionAttribute(name = "userInfo") User user,
                                                      @RequestBody BlogDto blog) {
        try {
            return new ResponseEntity<>(blogCreateService.createBlog(user, blog), HttpStatus.CREATED);
        } catch (InvalidBlogMemberException invalidBlogMemberException) {
            return new ResponseEntity<>(new Error(invalidBlogMemberException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

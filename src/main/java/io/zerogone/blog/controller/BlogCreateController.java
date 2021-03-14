package io.zerogone.blog.controller;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.model.User;
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
    public ResponseEntity<Blog> handleBlogCreateApi(@SessionAttribute(name = "userInfo") User user,
                                                    @RequestBody BlogDto blog) {
        return new ResponseEntity<>(blogCreateService.createBlog(user, blog), HttpStatus.CREATED);
    }
}

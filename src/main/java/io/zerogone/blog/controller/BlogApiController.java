package io.zerogone.blog.controller;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.service.BlogCreateService;
import io.zerogone.blog.service.BlogSearchService;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.Error;
import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
public class BlogApiController {
    private final BlogCreateService blogCreateService;
    private final BlogSearchService blogSearchService;

    public BlogApiController(BlogCreateService blogCreateService, BlogSearchService blogSearchService) {
        this.blogCreateService = blogCreateService;
        this.blogSearchService = blogSearchService;
    }

    @GetMapping("api/blog")
    public ResponseEntity<Object> handleBlogSearchApi(@RequestParam String name) {
        try {
            return new ResponseEntity<>(blogSearchService.getBlog(name), HttpStatus.OK);
        } catch (NoResultException noResultException) {
            return new ResponseEntity<>(new Error("검색 결과 없음"), HttpStatus.NOT_FOUND);
        }
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

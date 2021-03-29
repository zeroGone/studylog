package io.zerogone.controller;

import io.zerogone.service.BlogSearchService;
import io.zerogone.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@RestController
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    public BlogSearchController(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }

    @GetMapping("api/blog")
    public ResponseEntity<Object> handleBlogSearchApi(@RequestParam String name) {
        try {
            return new ResponseEntity<>(blogSearchService.getBlogByName(name), HttpStatus.OK);
        } catch (NoResultException noResultException) {
            return new ResponseEntity<>(new ErrorResponse("검색 결과 없음"), HttpStatus.NOT_FOUND);
        }
    }
}

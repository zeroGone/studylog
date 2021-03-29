package io.zerogone.controller;

import io.zerogone.service.BlogSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    public BlogSearchController(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }

    @GetMapping("api/blog")
    public ResponseEntity<Object> handleBlogSearchApi(@RequestParam String name) {
        return new ResponseEntity<>(blogSearchService.getBlogByName(name), HttpStatus.OK);
    }
}

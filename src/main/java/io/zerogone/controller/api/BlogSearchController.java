package io.zerogone.controller.api;

import io.zerogone.model.BlogVo;
import io.zerogone.service.BlogSearchService;
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
    public ResponseEntity<BlogVo> handleBlogSearchApi(@RequestParam String name) {
        return ResponseEntity.ok(blogSearchService.getBlogVoByName(name));
    }
}

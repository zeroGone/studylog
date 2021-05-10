package io.zerogone.controller.api;

import io.zerogone.model.Name;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.service.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogSearchController {
    private final SearchService<Name, BlogDto> searchService;

    public BlogSearchController(SearchService<Name, BlogDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/blog")
    public ResponseEntity<BlogDto> handleBlogSearchApi(@RequestParam Name name) {
        return ResponseEntity.ok(searchService.search(name));
    }
}

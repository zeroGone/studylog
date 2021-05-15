package io.zerogone.controller.api;

import io.zerogone.model.BlogName;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.service.search.SearchService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class BlogSearchController {
    private final SearchService<BlogName, BlogDto> searchService;

    public BlogSearchController(SearchService<BlogName, BlogDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/blog")
    public BlogDto handleSearchingBlogByName(@RequestParam @Valid BlogName name) {
        return searchService.search(name);
    }
}

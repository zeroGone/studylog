package io.zerogone.controller;

import io.zerogone.model.dto.PostDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {
    private final SearchService<Integer, PostDto> searchService;

    public PostController(SearchService<Integer, PostDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}/posts/new")
    public String getCreatePostViewName() {
        return "post_write";
    }

    @GetMapping("{name}/posts/{id}")
    public String getPostViewNameWithPostDto(@PathVariable("id") int postId, Model model) {
        model.addAttribute("post", searchService.search(postId));
        return "post";
    }
}

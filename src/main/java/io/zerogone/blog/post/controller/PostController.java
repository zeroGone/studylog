package io.zerogone.blog.post.controller;

import io.zerogone.blog.post.model.PostDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Positive;

@Controller
@Validated
public class PostController {
    private final SearchService<Integer, PostDto> searchService;

    public PostController(SearchService<Integer, PostDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}/posts/{id}")
    public String getPostViewNameWithPostDto(@PathVariable @Positive Integer id, Model model) {
        model.addAttribute("post", searchService.search(id));
        return "post";
    }
}

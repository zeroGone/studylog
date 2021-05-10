package io.zerogone.controller;

import io.zerogone.model.Name;
import io.zerogone.model.dto.PostDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostListController {
    private final SearchService<Name, List<PostDto>> searchService;

    public PostListController(SearchService<Name, List<PostDto>> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{blogName}/posts")
    public String getPostListViewName(@PathVariable Name blogName, Model model) {
        List<PostDto> postDtos = searchService.search(blogName);
        model.addAttribute("posts", postDtos);
        return "post_list";
    }
}

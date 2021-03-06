package io.zerogone.blog.post.controller;

import io.zerogone.blog.model.BlogName;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.common.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
public class PostListController {
    private final SearchService<BlogName, List<PostDto>> searchService;

    public PostListController(SearchService<BlogName, List<PostDto>> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}/posts")
    public String getPostListViewName(@PathVariable @Valid BlogName name, Model model) {
        List<PostDto> postDtos = searchService.search(name);
        model.addAttribute("posts", postDtos);
        return "post_list";
    }
}

package io.zerogone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("{name}/posts")
    public String getPostListViewName() {
        return "post_list";
    }

    @GetMapping("{name}/posts/new")
    public String getCreatePostViewName() {
        return "post_write";
    }
}

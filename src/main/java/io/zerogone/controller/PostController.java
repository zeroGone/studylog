package io.zerogone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {
    @GetMapping("{name}/posts")
    public String getPostListViewName(@PathVariable String name) {
        return "post_list";
    }
}

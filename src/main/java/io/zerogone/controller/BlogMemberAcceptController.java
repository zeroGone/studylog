package io.zerogone.controller;

import io.zerogone.service.BlogMemberAcceptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogMemberAcceptController {
    private final BlogMemberAcceptService blogMemberAcceptService;

    public BlogMemberAcceptController(BlogMemberAcceptService blogMemberAcceptService) {
        this.blogMemberAcceptService = blogMemberAcceptService;
    }

    @GetMapping("blog/accept")
    public String getBlogAcceptViewName(@RequestParam String key, Model model) {
        model.addAttribute("blog", blogMemberAcceptService.acceptBlogInvitation(key));
        return "blog_accept";
    }
}

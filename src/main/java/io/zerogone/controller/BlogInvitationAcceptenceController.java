package io.zerogone.controller;

import io.zerogone.service.BlogInvitationAcceptenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogInvitationAcceptenceController {
    private final BlogInvitationAcceptenceService blogInvitationAcceptenceService;

    public BlogInvitationAcceptenceController(BlogInvitationAcceptenceService blogInvitationAcceptenceService) {
        this.blogInvitationAcceptenceService = blogInvitationAcceptenceService;
    }

    @GetMapping("blog/accept")
    public String getBlogAcceptViewName(@RequestParam String key, Model model) {
        model.addAttribute("blog", blogInvitationAcceptenceService.acceptBlogInvitation(key));
        return "blog_accept";
    }
}

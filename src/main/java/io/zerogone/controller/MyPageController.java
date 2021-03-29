package io.zerogone.controller;

import io.zerogone.service.BlogSearchService;
import io.zerogone.model.CurrentUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MyPageController {
    private final BlogSearchService blogSearchService;

    public MyPageController(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithUserInfo(@SessionAttribute("userInfo") CurrentUserInfo userInfo, Model model) {
        model.addAttribute("blogs", blogSearchService.getBlogsThatUserBelongTo(userInfo));
        return "mypage";
    }
}

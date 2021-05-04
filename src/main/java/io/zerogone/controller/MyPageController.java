package io.zerogone.controller;

import io.zerogone.model.vo.UserVo;
import io.zerogone.service.BlogSearchService;
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
    public String getMypageViewNameWithBlogVos(@SessionAttribute("userInfo") UserVo userInfo, Model model) {
        model.addAttribute("blogs", blogSearchService.getBlogVosByUserVo(userInfo));
        return "mypage";
    }
}

package io.zerogone.controller;

import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MyPageController {
    private final SearchService<String, UserWithBlogsDto> searchService;

    public MyPageController(SearchService<String, UserWithBlogsDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithBlogVos(@SessionAttribute("userInfo") UserWithBlogsDto userInfo, Model model) {
        model.addAttribute("user", searchService.search(userInfo.getEmail()));
        return "mypage";
    }
}

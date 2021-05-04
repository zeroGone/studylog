package io.zerogone.controller;

import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MyPageController {
    private final SearchService<String, UserDto> searchService;

    public MyPageController(@Qualifier("userWithBlogsSearchService") SearchService<String, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithBlogVos(@SessionAttribute("userInfo") UserDto userInfo, Model model) {
        model.addAttribute("user", searchService.search(userInfo.getEmail()));
        return "mypage";
    }
}

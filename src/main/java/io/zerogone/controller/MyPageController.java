package io.zerogone.controller;

import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MyPageController {
    private final SearchService<Email, UserDto> searchService;

    public MyPageController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithBlogVos(@SessionAttribute("userInfo") UserDto userInfo, Model model) {
        model.addAttribute("user", searchService.search(new Email(userInfo.getEmail())));
        return "mypage";
    }
}

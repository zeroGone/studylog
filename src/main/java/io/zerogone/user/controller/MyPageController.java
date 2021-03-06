package io.zerogone.user.controller;

import io.zerogone.user.model.Email;
import io.zerogone.user.model.UserDto;
import io.zerogone.common.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@Validated
public class MyPageController {
    private final SearchService<Email, UserDto> searchService;

    public MyPageController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithBlogVos(@SessionAttribute("userInfo") @Valid UserDto userInfo, Model model) {
        model.addAttribute("user", searchService.search(new Email(userInfo.getEmail())));
        return "mypage";
    }
}

package io.zerogone.user.controller;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.LoginRequestForm;
import io.zerogone.user.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    private final SearchService<LoginRequestForm, UserDto> searchService;

    public LoginController(SearchService<LoginRequestForm, UserDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("login")
    public String doLogin(@ModelAttribute @Valid LoginRequestForm loginRequestForm, HttpSession httpSession) {
        try {
            UserDto dto = searchService.search(loginRequestForm);
            httpSession.setAttribute("userInfo", dto);
            return "redirect:/mypage";
        } catch (NotExistDataException notExistDataException) {
            httpSession.setAttribute("visitor", loginRequestForm);
            return "redirect:/signup";
        }
    }
}

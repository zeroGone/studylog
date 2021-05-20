package io.zerogone.user.controller;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.LoginRequest;
import io.zerogone.user.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    private final SearchService<LoginRequest, UserDto> searchService;

    public LoginController(SearchService<LoginRequest, UserDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("login")
    public String doLogin(@RequestBody @Valid LoginRequest loginRequest, HttpSession httpSession) {
        try {
            UserDto dto = searchService.search(loginRequest);
            httpSession.setAttribute("userInfo", dto);
            return "redirect:/mypage";
        } catch (NotExistDataException notExistDataException) {
            httpSession.setAttribute("visitor", loginRequest);
            return "redirect:/signup";
        }
    }
}

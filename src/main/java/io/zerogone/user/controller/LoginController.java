package io.zerogone.user.controller;

import io.zerogone.blog.exception.NotAuthorizedException;
import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.LoginRequest;
import io.zerogone.user.model.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {
    private final SearchService<LoginRequest, UserDto> searchService;

    public LoginController(SearchService<LoginRequest, UserDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("login")
    public UserDto doLogin(@RequestBody @Valid LoginRequest loginRequest, HttpSession httpSession) {
        try {
            UserDto dto = searchService.search(loginRequest);
            httpSession.setAttribute("userInfo", dto);
            return dto;
        } catch (NotExistDataException notExistDataException) {
            httpSession.setAttribute("visitor", loginRequest);
            throw new NotAuthorizedException("회원가입 대상입니다");
        }
    }
}

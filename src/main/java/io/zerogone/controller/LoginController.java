package io.zerogone.controller;

import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.UserVo;
import io.zerogone.user.service.UserSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private final UserSearchService userSearchService;

    public LoginController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @PostMapping("login")
    public ResponseEntity<Object> doLogin(@RequestBody UserDto userDto, HttpSession httpSession) {
        try {
            UserVo userVo = userSearchService.getUserHasEmail(userDto.getEmail());
            httpSession.setAttribute("userInfo", new CurrentUserInfo(userVo));
            return ResponseEntity.ok(userVo);
        } catch (NoResultException noResultException) {
            httpSession.setAttribute("visitor", userDto);
            throw noResultException;
        }
    }
}

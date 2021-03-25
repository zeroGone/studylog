package io.zerogone.controller;

import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.UserVo;
import io.zerogone.user.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private final UserSearchService userSearchService;

    public LoginController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @PostMapping("login")
    public ResponseEntity<UserVo> handleLoginApi(@RequestBody UserDto userDto, HttpSession httpSession) {
        UserVo userVo = userSearchService.getUserHasEmail(userDto.getEmail());

        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setId(userVo.getId());
        currentUserInfo.setName(userVo.getName());
        currentUserInfo.setEmail(userVo.getEmail());
        currentUserInfo.setNickName(userVo.getNickName());
        currentUserInfo.setImgUrl(userVo.getImgUrl());

        httpSession.setAttribute("userInfo", currentUserInfo);

        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }
}

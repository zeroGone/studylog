package io.zerogone.controller;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.UserDto;
import io.zerogone.model.UserVo;
import io.zerogone.service.UserSearchService;
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
    public ResponseEntity<UserVo> doLogin(@RequestBody UserDto userDto, HttpSession httpSession) {
        try {
            UserVo userVo = userSearchService.getUserByEmail(userDto.getEmail());
            httpSession.setAttribute("userInfo", new CurrentUserInfo(userVo));
            return ResponseEntity.ok(userVo);
        } catch (NotExistedDataException notExistedDataException) {
            httpSession.setAttribute("visitor", userDto);
            throw notExistedDataException;
        }
    }
}

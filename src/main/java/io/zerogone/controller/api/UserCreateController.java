package io.zerogone.controller.api;

import io.zerogone.model.UserDto;
import io.zerogone.model.vo.UserVo;
import io.zerogone.service.UserCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class UserCreateController {
    private final UserCreateService userCreateService;

    public UserCreateController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @PostMapping("api/user")
    public ResponseEntity<UserVo> handleCreateUserApi(@ModelAttribute UserDto userDto,
                                                      @RequestPart(required = false) MultipartFile image,
                                                      HttpSession httpSession) {
        UserVo userVo = userCreateService.createUser(userDto, image);
        httpSession.setAttribute("userInfo", userVo);
        httpSession.removeAttribute("visitor");
        return new ResponseEntity<>(userVo, HttpStatus.CREATED);
    }
}

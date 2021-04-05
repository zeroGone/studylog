package io.zerogone.controller;

import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.UserDto;
import io.zerogone.service.UserUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserUpdateController {
    private final UserUpdateService userUpdateService;

    public UserUpdateController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping(value = "api/user")
    public ResponseEntity<UserDto> handleUpdatingUserImage(
            @SessionAttribute CurrentUserInfo userInfo,
            @RequestPart MultipartFile image) {

        userUpdateService.updateUserImage(userInfo, image);

        return ResponseEntity.ok(userInfo);
    }
}
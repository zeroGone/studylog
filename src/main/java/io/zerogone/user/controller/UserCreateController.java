package io.zerogone.user.controller;

import io.zerogone.user.model.UserDto;
import io.zerogone.service.create.CreateWithImageService;
import io.zerogone.validator.NewEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class UserCreateController {
    private final CreateWithImageService<UserDto> createService;

    public UserCreateController(CreateWithImageService<UserDto> createService) {
        this.createService = createService;
    }

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto handleCreatingUser(@ModelAttribute @Validated(NewEntity.class) UserDto user,
                                      @RequestPart(required = false) MultipartFile image,
                                      HttpSession httpSession) {
        UserDto userInfo;

        if (image == null) {
            userInfo = createService.create(user);
        } else {
            userInfo = createService.create(user, image);
        }

        httpSession.setAttribute("userInfo", userInfo);
        httpSession.removeAttribute("visitor");
        return userInfo;
    }
}


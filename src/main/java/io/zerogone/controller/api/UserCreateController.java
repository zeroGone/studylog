package io.zerogone.controller.api;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.service.create.CreateService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final CreateService createService;

    public UserCreateController(@Qualifier("userCreateService") CreateService createService) {
        this.createService = createService;
    }

    @PostMapping("api/user")
    public ResponseEntity<UserDto> handleCreateUserApi(@ModelAttribute UserDto userDto,
                                                       @RequestPart(required = false) MultipartFile image,
                                                       HttpSession httpSession) {
        UserWithBlogsDto userInfo;
        if (image == null) {
            userInfo = (UserWithBlogsDto) createService.create(userDto);
        } else {
            userInfo = (UserWithBlogsDto) createService.create(userDto, image);
        }
        httpSession.setAttribute("userInfo", userInfo);
        httpSession.removeAttribute("visitor");
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
}

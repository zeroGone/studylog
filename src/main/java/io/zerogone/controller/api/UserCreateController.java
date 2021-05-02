package io.zerogone.controller.api;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.service.create.CreateWithImageService;
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
    private final CreateWithImageService createWithImageService;

    public UserCreateController(CreateWithImageService createWithImageService) {
        this.createWithImageService = createWithImageService;
    }

    @PostMapping("api/user")
    public ResponseEntity<UserDto> handleCreateUserApi(@ModelAttribute UserDto userDto,
                                                       @RequestPart(required = false) MultipartFile image,
                                                       HttpSession httpSession) {
        if (image == null) {
            userDto = (UserWithBlogsDto) createWithImageService.create(userDto);
        } else {
            userDto = (UserWithBlogsDto) createWithImageService.create(userDto, image);
        }
        httpSession.setAttribute("userInfo", userDto);
        httpSession.removeAttribute("visitor");
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}

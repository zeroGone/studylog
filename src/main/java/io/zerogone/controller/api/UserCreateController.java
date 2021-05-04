package io.zerogone.controller.api;

import io.zerogone.model.dto.UserDto;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.fileupload.ImageUploadService;
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
    private final CreateService<UserDto> createService;
    private final ImageUploadService<UserDto> imageUploadService;

    public UserCreateController(CreateService<UserDto> createService, ImageUploadService<UserDto> imageUploadService) {
        this.createService = createService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("api/user")
    public ResponseEntity<UserDto> handleCreateUserApi(@ModelAttribute UserDto userDto,
                                                       @RequestPart(required = false) MultipartFile image,
                                                       HttpSession httpSession) {
        if (image != null) {
            userDto = imageUploadService.upload(userDto, image);
        }
        UserDto userInfo = createService.create(userDto);
        httpSession.setAttribute("userInfo", userInfo);
        httpSession.removeAttribute("visitor");
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
}

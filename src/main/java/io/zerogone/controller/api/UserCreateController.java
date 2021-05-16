package io.zerogone.controller.api;

import io.zerogone.model.dto.UserDto;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.fileupload.ImageUploadService;
import io.zerogone.service.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserCreateController {
    private final CreateService<UserDto> createService;
    private final ImageUploadService imageUploadService;

    public UserCreateController(CreateService<UserDto> createService,
                                @Qualifier("userImageUploadService") ImageUploadService imageUploadService) {
        this.createService = createService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("api/user")
    public ResponseEntity<UserDto> handleCreateUserApi(@ModelAttribute @Valid UserDto userDto,
                                                       @RequestPart(required = false) MultipartFile image,
                                                       HttpSession httpSession) {

        setImageUrl(userDto, image);
        UserDto userInfo = createService.create(userDto);
        httpSession.setAttribute("userInfo", userInfo);
        httpSession.removeAttribute("visitor");
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

    private void setImageUrl(UserDto userDto, MultipartFile image) {
        if (userDto.getImageUrl() != null) {
            return;
        }
        ImageUrl imageUrl = imageUploadService.upload(image);
        userDto.setImageUrl(imageUrl.getValue());
    }
}

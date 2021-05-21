package io.zerogone.user.controller;

import io.zerogone.blog.exception.NotAuthorizedException;
import io.zerogone.common.fileupload.ImageUrl;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.service.UserImageUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;

@RestController
public class UserImageUpdateController {
    private final UserImageUpdateService userImageUpdateService;

    public UserImageUpdateController(UserImageUpdateService userImageUpdateService) {
        this.userImageUpdateService = userImageUpdateService;
    }

    @PostMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void handleUpdatingUserImage(
            @PathVariable @Positive int id,
            @SessionAttribute UserDto userInfo,
            @RequestPart MultipartFile image) {

        if (id != userInfo.getId()) {
            throw new NotAuthorizedException("다른 회원의 정보에 접근할 수 없습니다");
        }

        ImageUrl updatedImageUrl = userImageUpdateService.updateUserImage(id, image);
        userInfo.setImageUrl(updatedImageUrl.getValue());
    }
}
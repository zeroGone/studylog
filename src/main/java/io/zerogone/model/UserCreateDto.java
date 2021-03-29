package io.zerogone.model;

import org.springframework.web.multipart.MultipartFile;

public class UserCreateDto extends UserDto {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}

package io.zerogone.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequestForm {
    @NotBlank(message = "이메일이 있어야 합니다")
    @Email(message = "이메일 형식이어야 합니다")
    private String email;
    @NotBlank(message = "이름이 있어야 합니다")
    private String name;
    private String imageUrl;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

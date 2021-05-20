package io.zerogone.user.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Validated
public class SignupForm {
    @NotBlank(message = "회원가입 시 이메일이 있어야 합니다")
    @Email(message = "이메일 형식이어야 합니다")
    private String email;
    @NotBlank(message = "회원가입 시 이름이 있어야 합니다")
    private String name;
    private String imageUrl;
    @NotBlank(message = "회원가입 시 닉네임이 있어야 합니다")
    private String nickName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

package io.zerogone.user.model;

import javax.validation.constraints.NotBlank;

public class Email {
    @NotBlank(message = "공백이면 안됩니다")
    @javax.validation.constraints.Email(message = "이메일 형식이어야 합니다")
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    public String get() {
        return email;
    }
}

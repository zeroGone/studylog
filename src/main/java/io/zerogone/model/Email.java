package io.zerogone.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class Email {
    @NotBlank(message = "이메일 형식이어야 합니다")
    @javax.validation.constraints.Email(message = "이메일 형식이어야 합니다")
    private final String value;

    public Email(String email) {
        value = email;
    }

    public String getValue() {
        return value;
    }
}

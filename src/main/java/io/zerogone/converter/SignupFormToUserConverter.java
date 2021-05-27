package io.zerogone.converter;

import io.zerogone.domain.SignupForm;
import io.zerogone.domain.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SignupFormToUserConverter implements Converter<SignupForm, User> {
    @Override
    public User convert(SignupForm signupForm) {
        return new User(signupForm.getName(), signupForm.getNickName(), signupForm.getEmail(), signupForm.getImageUrl());
    }
}

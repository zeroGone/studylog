package io.zerogone.user.converter;

import io.zerogone.user.model.SignupForm;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SignupFormToUserConverter implements Converter<SignupForm, User> {
    @Override
    public User convert(SignupForm signupForm) {
        return new User(signupForm.getName(), signupForm.getEmail(), signupForm.getNickName(), signupForm.getImageUrl());
    }
}

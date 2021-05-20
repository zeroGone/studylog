package io.zerogone.user.service;

import io.zerogone.user.UserDao;
import io.zerogone.user.model.SignupForm;
import io.zerogone.user.model.User;
import io.zerogone.user.model.UserDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class SignupService {
    private final UserDao userDao;
    private final ConversionService conversionService;

    public SignupService(UserDao userDao, ConversionService conversionService) {
        this.userDao = userDao;
        this.conversionService = conversionService;
    }

    @Transactional
    public UserDto createUser(@NotNull @Valid SignupForm signupForm) {
        User user = conversionService.convert(signupForm, User.class);
        userDao.save(user);
        return conversionService.convert(user, UserDto.class);
    }
}

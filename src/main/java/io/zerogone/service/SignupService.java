package io.zerogone.service;

import io.zerogone.domain.SignupForm;
import io.zerogone.domain.entity.User;
import io.zerogone.repository.UserDao;
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
    public User createUser(@NotNull @Valid SignupForm signupForm) {
        User user = conversionService.convert(signupForm, User.class);
        userDao.save(user);
        return user;
    }
}

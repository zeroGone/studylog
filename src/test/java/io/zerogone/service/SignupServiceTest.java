package io.zerogone.service;

import io.zerogone.config.ApplicationConfiguration;
import io.zerogone.domain.SignupForm;
import io.zerogone.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class SignupServiceTest {
    @Autowired
    private SignupService signupService;

    @Test
    @Transactional
    public void createUser() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setName("김영곤");
        signupForm.setNickName("zeroGone");
        User createdUser = signupService.createUser(signupForm);
        Assert.assertNotEquals(0, (long) createdUser.getId());
    }

    @Test
    public void WhenCreateUser_GivenNull_ThenThrowConstraintException() {
        try {
            signupService.createUser(null);
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    public void WhenCreateUser_GivenEmptySignupForm_ThenThrowConstraintException() {
        SignupForm signupForm = new SignupForm();
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    public void WhenCreateUser_GivenSignupFormIsNameBlank_ThenThrowConstraintException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setName(" ");
        signupForm.setNickName("zeroGone");
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    public void WhenCreateUser_GivenSingupFormIsNicknameBlank_ThenThrowConstraintException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail(" ");
        signupForm.setName("name");
        signupForm.setNickName("zeroGone");
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    public void WhenCreateUser_GivenSignupFormEmailIsBlank_ThenThrowConstraintException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setName("name");
        signupForm.setNickName(" ");
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void WhenCreateUser_GivenEmailIsDuplicated_ThenDataIntegrityViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setName("김영곤");
        signupForm.setNickName("zeroGone");

        signupService.createUser(signupForm);
        signupForm.setNickName("emailTest");
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void WhenCreateUser_GivenNicknameIsDuplicated_ThenDataIntegrityViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setName("김영곤");
        signupForm.setNickName("zeroGone");

        signupService.createUser(signupForm);
        signupForm.setEmail("nickTest@test.com");
        try {
            signupService.createUser(signupForm);
            assert false;
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            assert true;
        }
    }
}
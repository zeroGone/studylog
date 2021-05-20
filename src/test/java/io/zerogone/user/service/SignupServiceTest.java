package io.zerogone.user.service;

import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.SignupForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class SignupServiceTest {
    @Autowired
    private SignupService signupService;

    @Test
    @Transactional
    public void createUser() {
        SignupForm signupForm = new SignupForm();
        signupForm.setName("김영곤");
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setNickName("zeroGone");
        Assert.assertNotNull(signupService.createUser(signupForm));
    }

    @Test
    @Transactional
    public void createUser_IsNull_ConstraintViolationException() {
        try {
            Assert.assertNotNull(signupService.createUser(null));
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createUser_NameIsBlank_ConstraintViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setEmail("dudrhs571@gmail.com");
        signupForm.setNickName("zeroGone");
        try {
            Assert.assertNotNull(signupService.createUser(signupForm));
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createUser_EmailIsBlank_ConstraintViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setName("김영곤");
        signupForm.setNickName("zeroGone");
        try {
            Assert.assertNotNull(signupService.createUser(signupForm));
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createUser_EmailIsNotEmail_ConstraintViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setName("김영곤");
        signupForm.setEmail("dudrhs571");
        signupForm.setNickName("zeroGone");
        try {
            Assert.assertNotNull(signupService.createUser(signupForm));
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createUser_NickNameIsBlank_ConstraintViolationException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setName("김영곤");
        signupForm.setEmail("dudrhs571");
        try {
            Assert.assertNotNull(signupService.createUser(signupForm));
            assert false;
        } catch (ConstraintViolationException constraintViolationException) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createUser_EmailIsDuplicated_ThrowPersistenceException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setName("김영곤");
        signupForm.setEmail("dudrhs571@naver.com");
        signupForm.setNickName("zeroGone7247");
        try {
            Assert.assertNotNull(signupService.createUser(signupForm));
            assert false;
        } catch (PersistenceException persistenceException) {
            assert true;
        }
    }
}
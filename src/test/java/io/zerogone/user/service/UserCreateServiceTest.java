package io.zerogone.user.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.PersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserCreateServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserCreateService userCreateService;

    @Before
    public void setUp() {
        userCreateService = webApplicationContext.getBean(UserCreateService.class);
    }

    @Test
    public void createUser() {
        UserDto userDto = new UserDto();
        userDto.setName("03/26 08:17");
        userDto.setEmail("03/26 08:17");
        userDto.setNickName("03/26 08:17");

        Assert.assertNotNull(userCreateService.createUser(userDto));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createUser_DuplicatedEmail_ThrowPersistenException() {
        expectedException.expect(PersistenceException.class);

        UserDto userDto = new UserDto();
        userDto.setName("test 0326");
        userDto.setEmail("dudrhs571@gmail.com");
        userDto.setNickName("03/26 08:17qwewqeqw");

        Assert.assertNotNull(userCreateService.createUser(userDto));
    }
}
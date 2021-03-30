package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.UserCreateDto;
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
        UserCreateDto userDto = new UserCreateDto();
        userDto.setName("03/26 08:17");
        userDto.setEmail("03/26 08:17");
        userDto.setNickName("03/26 08:17");

        Assert.assertNotNull(userCreateService.createUser(userDto));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createUser_DuplicatedEmail_ThrowUniquePropertyException() {
        expectedException.expect(UniquePropertyException.class);

        UserCreateDto userDto = new UserCreateDto();
        userDto.setName("test 0326");
        userDto.setEmail("dundung@gmail.com");
        userDto.setNickName("03/26 08:17qwewqeqw");

        Assert.assertNotNull(userCreateService.createUser(userDto));
    }

    @Test
    public void createUser_NameIsNull_ThrowNotNullPropertyException() {
        expectedException.expect(NotNullPropertyException.class);

        UserCreateDto userDto = new UserCreateDto();
        userDto.setEmail("test@gmail.com");
        userDto.setNickName("03/26 08:17qwewqeqw");

        Assert.assertNotNull(userCreateService.createUser(userDto));
    }
}
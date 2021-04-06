package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.UserDto;
import io.zerogone.model.entity.User;
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

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = webApplicationContext.getBean(UserDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findUserByEmail() {
        User user = userDao.findByEmail("dudrhs571@gmail.com");
        Assert.assertNotNull(user);
        Assert.assertNotEquals(0, user.getId());
        Assert.assertEquals("dudrhs571@gmail.com", user.getEmail());
    }

    @Test
    public void fidnUserByEmail_GivenNull_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userDao.findByEmail(null).getId());
    }

    @Test
    public void findUserByEmail_GivenEmptyString_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userDao.findByEmail("").getId());
    }

    @Test
    @Transactional
    public void save() {
        UserDto dto = new UserDto();
        dto.setName("test0325 1513");
        dto.setEmail("test0325 1509");
        dto.setNickName("test0325 1509");
        User user = new User(dto);

        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void update() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("dudrhs571@gmail.com");
        userDto.setName("김영곤");
        userDto.setNickName("zeroGone7247");
        User user = new User(userDto);
        userDao.updateImageUrl(user);
        Assert.assertNull(user.getImageUrl());

        userDto.setImageUrl("url");
        user = new User(userDto);
        userDao.updateImageUrl(user);
        Assert.assertEquals("url", user.getImageUrl());
    }
}
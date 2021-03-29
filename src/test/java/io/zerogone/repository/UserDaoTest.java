package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.entity.User;
import io.zerogone.model.UserCreateDto;
import io.zerogone.repository.UserDao;
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

import javax.persistence.NoResultException;
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
        expectedException.expect(NoResultException.class);
        Assert.assertNull(userDao.findUserByEmail("dudrhs571"));
        Assert.assertNotNull(userDao.findUserByEmail("dudrhs571@gmail.com"));
    }

    @Test
    public void findAllByBlogId() {
        Assert.assertNotNull(userDao.findAllByBlogId(1));
        Assert.assertEquals(0, userDao.findAllByBlogId(2).size());
    }

    @Test
    @Transactional
    public void save() {
        UserCreateDto dto = new UserCreateDto();
        dto.setName("test0325 1513");
        dto.setEmail("test0325 1509");
        dto.setNickName("test0325 1509");
        User user = new User(dto);

        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }
}
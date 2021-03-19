package io.zerogone.user.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserSearchDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserSearchDao userSearchDao;

    @Before
    public void setUp() throws Exception {
        userSearchDao = webApplicationContext.getBean(UserSearchDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindUserByEmail() {
        expectedException.expect(NoResultException.class);
        Assert.assertNull(userSearchDao.findUserByEmail("dudrhs571"));
        Assert.assertNotNull(userSearchDao.findUserByEmail("dudrhs571@gmail.com"));
    }
}
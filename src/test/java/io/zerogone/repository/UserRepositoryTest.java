package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import org.junit.Assert;
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
public class UserRepositoryTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testRepositoryLoaded() {
        Assert.assertNotNull(webApplicationContext.getBean(UserRepository.class));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindUserByEmail() {
        UserRepository userRepository = webApplicationContext.getBean(UserRepository.class);
        Assert.assertNotNull(userRepository.findUserByEmail("dudrhs571@gmail.com"));

        expectedException.expect(NoResultException.class);
        Assert.assertNotNull(userRepository.findUserByEmail("dud123"));
    }
}
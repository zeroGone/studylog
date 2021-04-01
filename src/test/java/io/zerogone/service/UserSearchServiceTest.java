package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotExistedDataException;
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
public class UserSearchServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserSearchService userSearchService;

    @Before
    public void setUp() throws Exception {
        userSearchService = webApplicationContext.getBean(UserSearchService.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getUserByEmail() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNull(userSearchService.getUserByEmail("dudrhs571"));
        Assert.assertNotEquals(0, userSearchService.getUserByEmail("dudrhs571@gmail.com").getId());
    }

    @Test
    public void fidnUserByEmail_GivenNull_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userSearchService.getUserByEmail(null).getId());
    }

    @Test
    public void getUserByEmail_GivenEmptyString_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userSearchService.getUserByEmail("").getId());
    }
}
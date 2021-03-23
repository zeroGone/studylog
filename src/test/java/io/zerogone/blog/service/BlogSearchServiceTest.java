package io.zerogone.blog.service;

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
public class BlogSearchServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogSearchService blogSearchService;

    @Before
    public void setUp() {
        blogSearchService = webApplicationContext.getBean(BlogSearchService.class);
    }

    @Test
    public void getBlog() {
        Assert.assertNotNull(blogSearchService.getBlog("studylog"));
        Assert.assertEquals(blogSearchService.getBlog("studylog").getName(), "studylog");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getBlog_NotExistedName_ThrowNoResultException() {
        expectedException.expect(NoResultException.class);
        Assert.assertNotNull(blogSearchService.getBlog("jinmin is genius"));
    }
}
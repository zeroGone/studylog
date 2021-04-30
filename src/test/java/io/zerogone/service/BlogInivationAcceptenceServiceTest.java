package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.vo.BlogVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class BlogInivationAcceptenceServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogInvitationAcceptenceService blogInvitationAcceptenceService;

    @Before
    public void setUp() {
        blogInvitationAcceptenceService = webApplicationContext.getBean(BlogInvitationAcceptenceService.class);
    }

    @Test
    public void acceptBlogInvitation() {
        BlogVo blog = blogInvitationAcceptenceService.acceptBlogInvitation("hXfHxxa0nqLHNsk");
        Assert.assertNotNull(blog);
    }
}
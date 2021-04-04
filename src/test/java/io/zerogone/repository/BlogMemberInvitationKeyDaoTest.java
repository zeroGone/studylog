package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.entity.BlogMemberInvitationKey;
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
public class BlogMemberInvitationKeyDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogMemberInvitationKeyDao blogMemberInvitationKeyDao;

    @Before
    public void setUp() {
        blogMemberInvitationKeyDao = webApplicationContext.getBean(BlogMemberInvitationKeyDao.class);
    }

    @Test
    public void findBlogMemberInvitationKeyByValue() {
        BlogMemberInvitationKey invitationKey = blogMemberInvitationKeyDao.findBlogMemberInvitationKeyByValue("I\\BIs.!\"?lU~R$U%>lsG");
        Assert.assertNotNull(invitationKey);
        Assert.assertEquals("dudrhs571@naver.com", invitationKey.getOwner().getUser().getEmail());
    }
}
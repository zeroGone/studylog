package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.entity.*;
import io.zerogone.service.InvitationKeyGenerator;
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

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogInvitationKeyDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogMemberDao blogMemberDao;
    private BlogInvitationKeyDao blogInvitationKeyDao;
    private InvitationKeyGenerator invitationKeyGenerator = new InvitationKeyGenerator();

    @Before
    public void setUp() throws Exception {
        blogMemberDao = webApplicationContext.getBean(BlogMemberDao.class);
        blogInvitationKeyDao = webApplicationContext.getBean(BlogInvitationKeyDao.class);
    }

    @Test
    @Transactional
    public void save() {
        BlogMember blogMember = new BlogMember(
                new User(1, null, null, null, null),
                new Blog(1, null, null, null),
                MemberRole.ADMIN);
        blogMemberDao.save(blogMember);

        BlogInvitationKey blogInvitationKey = new BlogInvitationKey(invitationKeyGenerator.generateKey(), blogMember);
        blogInvitationKeyDao.save(Collections.singletonList(blogInvitationKey));

        Assert.assertNotEquals(0, blogInvitationKey.getId());
    }

    @Test
    @Transactional
    public void save_OwnerIsNull_ThrowPersistenceException() {
        BlogInvitationKey blogInvitationKey = new BlogInvitationKey(invitationKeyGenerator.generateKey(), null);
        try {
            blogInvitationKeyDao.save(Collections.singletonList(blogInvitationKey));
            assert false;
        } catch (Exception exception) {
            Assert.assertEquals(PersistenceException.class, exception.getClass());
        }
    }

    @Test
    @Transactional
    public void save_OwnerIsTrasientInstance_ThrowIllegalStatementException() {
        BlogMember blogMember = new BlogMember(
                new User(1, null, null, null, null),
                new Blog(1, null, null, null),
                MemberRole.ADMIN);

        BlogInvitationKey blogInvitationKey = new BlogInvitationKey(invitationKeyGenerator.generateKey(), blogMember);
        try {
            blogInvitationKeyDao.save(Collections.singletonList(blogInvitationKey));
            assert false;
        } catch (Exception exception) {
            Assert.assertEquals(IllegalStateException.class, exception.getClass());
        }
    }

    @Test
    @Transactional
    public void save_ValueIsNull_ThrowPersistenceException() {
        BlogMember blogMember = new BlogMember(
                new User(1, null, null, null, null),
                new Blog(1, null, null, null),
                MemberRole.ADMIN);
        blogMemberDao.save(blogMember);

        BlogInvitationKey blogInvitationKey = new BlogInvitationKey(null, blogMember);
        try {
            blogInvitationKeyDao.save(Collections.singletonList(blogInvitationKey));
            assert false;
        } catch (Exception exception) {
            Assert.assertEquals(PersistenceException.class, exception.getClass());
        }
    }
}
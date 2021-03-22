package io.zerogone.blogmember.repository;

import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.blogmember.model.BlogMember;
import io.zerogone.blogmember.model.MemberRole;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogMemberDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogMemberDao blogMemberDao;

    @Before
    public void setUp() {
        blogMemberDao = webApplicationContext.getBean(BlogMemberDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void save() {
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(new BlogMember(1, 1, MemberRole.ADMIN));
        blogMembers.add(new BlogMember(2, 1, MemberRole.INVITING));
        blogMemberDao.save(blogMembers);

        for (BlogMember blogMember : blogMembers) {
            Assert.assertNotEquals(0, blogMember.getId());
        }
    }

    @Test
    @Transactional
    public void save_GivenOneEntity_Success() {
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(new BlogMember(1, 1, MemberRole.ADMIN));
        blogMemberDao.save(blogMembers);

        for (BlogMember blogMember : blogMembers) {
            Assert.assertNotEquals(0, blogMember.getId());
        }
    }

    @Test
    @Transactional
    public void save_GivenInvalidUserId_ThrowBlogMembersStateException() {
        expectedException.expect(BlogMembersStateException.class);
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(new BlogMember(Integer.MAX_VALUE, 1, MemberRole.ADMIN));
        blogMemberDao.save(blogMembers);

        for (BlogMember blogMember : blogMembers) {
            Assert.assertNotEquals(0, blogMember.getId());
        }
    }

    @Test
    @Transactional
    public void save_GivenInvalidBlogId_ThrowBlogMembersStateException() {
        expectedException.expect(BlogMembersStateException.class);
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(new BlogMember(1, Integer.MAX_VALUE, MemberRole.ADMIN));
        blogMemberDao.save(blogMembers);

        for (BlogMember blogMember : blogMembers) {
            Assert.assertNotEquals(0, blogMember.getId());
        }
    }
}
package io.zerogone.repository;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.MemberRole;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveBlog() {
        Blog blog = createBlogInstance();
        entityManager.persist(blog);
        entityManager.flush();

        List<BlogMember> blogMembers = createBlogMemberInstances();

        for (BlogMember blogMember : blogMembers) {
            blogMember.setBlogId(blog.getId());
            entityManager.persist(blogMember);
        }
        entityManager.flush();

        Assert.assertNotEquals(0, blog.getId());
        for (BlogMember blogMember : blogMembers) {
            Assert.assertNotEquals(0, blogMember.getId());
        }
        entityManager.close();
    }

    private Blog createBlogInstance() {
        return new Blog("studylog", "Web platform for team blog", null);
    }

    private List<BlogMember> createBlogMemberInstances() {
        BlogMember blogMember1 = new BlogMember(1, MemberRole.ADMIN);
        BlogMember blogMember2 = new BlogMember(2, MemberRole.INVITING);
        return new ArrayList<>(Arrays.asList(blogMember1, blogMember2));
    }
}
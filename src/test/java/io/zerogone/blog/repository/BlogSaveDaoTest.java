package io.zerogone.blog.repository;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.MemberRole;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogSaveDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogSaveDao blogSaveDao;

    @Before
    public void setUp() throws Exception {
        blogSaveDao = webApplicationContext.getBean(BlogSaveDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void save_InValidBlogMemeber_ThrowException() {
        expectedException.expect(InvalidBlogMemberException.class);
        Blog blog = new Blog("testBlog", "This is temporary instance fot testing", null);
        BlogMember blogMember = new BlogMember(Integer.MAX_VALUE, MemberRole.ADMIN);

        Assert.assertNotNull(blogSaveDao.save(blog, new ArrayList<>(Collections.singletonList(blogMember))));
        Assert.assertEquals(blog,blogSaveDao.save(blog, new ArrayList<>(Collections.singletonList(blogMember))));
    }

    @Test
    @Transactional
    public void save_AppropriateParameters_ReturnSameBlogInstance(){
        Blog blog = new Blog("testBlog", "This is temporary instance fot testing", null);
        BlogMember blogMember1 = new BlogMember(1, MemberRole.ADMIN);
        BlogMember blogMember2 = new BlogMember(2, MemberRole.INVITING);
        BlogMember blogMember3 = new BlogMember(3, MemberRole.INVITING);
        Assert.assertEquals(blog, blogSaveDao.save(blog, new ArrayList<>(Arrays.asList(blogMember1, blogMember2, blogMember3))));
    }
}
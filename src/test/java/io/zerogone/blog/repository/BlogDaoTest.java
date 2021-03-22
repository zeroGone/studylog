package io.zerogone.blog.repository;

import io.zerogone.blog.model.Blog;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogDao blogDao;

    @Before
    public void setUp() {
        blogDao = webApplicationContext.getBean(BlogDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void save() {
        Blog blog = new Blog("testBlog", "This is temporary instance fot testing", null);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    @Transactional
    public void save_BlogNameIsNull_ThrowException() {
        Blog blog = new Blog(null, null, null);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }
}
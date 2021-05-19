package io.zerogone.repository;

import io.zerogone.blog.post.PostDao;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
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

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PostDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private PostDao postDao;

    @Before
    public void setUp() {
        postDao = webApplicationContext.getBean(PostDao.class);
    }

    @Test
    @Transactional
    public void findById() {
        Assert.assertNotNull(postDao.findById(1));
    }
}
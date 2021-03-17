package io.zerogone.blog.service;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.User;
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
import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogCreateServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogCreateService blogCreateService;

    @Before
    public void setUp() {
        blogCreateService = webApplicationContext.getBean(BlogCreateService.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void createBlog() {
        User creator = new User();
        creator.setId(1);
        BlogDto blogDto = new BlogDto();
        blogDto.setName("test dto");

        Assert.assertNotNull(blogCreateService.createBlog(creator, blogDto));
    }

    @Test
    @Transactional
    public void createBlog_InValidBlogMemeber_ThrowException() {
        User creator = new User();
        creator.setId(1);
        User member = new User();
        member.setId(Integer.MAX_VALUE);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test dto");
        blogDto.setMembers(new ArrayList<>(Collections.singletonList(member)));

        expectedException.expect(InvalidBlogMemberException.class);
        Assert.assertNotNull(blogCreateService.createBlog(creator, blogDto));
    }
}
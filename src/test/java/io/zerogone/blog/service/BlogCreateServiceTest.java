package io.zerogone.blog.service;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.model.UserDto;
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
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
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
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);
        BlogDto blogDto = new BlogDto();
        blogDto.setName("test");

        Assert.assertNotNull(blogCreateService.createBlog(creator, blogDto));
    }

    @Test
    @Transactional
    public void createBlog_SameBlogName_ThrowDuplicatedPropertyException() {
        expectedException.expect(UniquePropertyException.class);
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);
        BlogDto blogDto = new BlogDto();
        blogDto.setName("test dto");
        Assert.assertNotNull(blogCreateService.createBlog(creator, blogDto));
    }

    @Test
    @Transactional
    public void createBlog_IncludeInvalidBlogMember_ThrowBlogMembersStateException() {
        expectedException.expect(BlogMembersStateException.class);
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);

        UserDto member = new UserDto();
        member.setId(-1);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test");
        blogDto.setMembers(new ArrayList<>(Collections.singletonList(member)));

        Assert.assertNotNull(blogCreateService.createBlog(creator, blogDto));
    }
}
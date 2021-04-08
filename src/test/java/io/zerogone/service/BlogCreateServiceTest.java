package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.BlogDto;
import io.zerogone.model.UserDto;
import io.zerogone.model.UserVo;
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
        UserVo creator = new UserVo(1, null, null, null, null, null, null);
        BlogDto BlogDto = new BlogDto();
        BlogDto.setName("test");

        Assert.assertNotNull(blogCreateService.createBlog(creator, BlogDto, null));
    }

    @Test
    @Transactional
    public void createBlog_SameBlogName_ThrowDuplicatedPropertyException() {
        expectedException.expect(UniquePropertyException.class);
        UserVo creator = new UserVo(1, null, null, null, null, null, null);

        BlogDto BlogDto = new BlogDto();
        BlogDto.setName("test dto");
        Assert.assertNotNull(blogCreateService.createBlog(creator, BlogDto, null));
    }

    @Test
    @Transactional
    public void createBlog_IncludeInvalidBlogMember_ThrowBlogMembersStateException() {
        expectedException.expect(BlogMembersStateException.class);
        UserVo creator = new UserVo(1, null, null, null, null, null, null);

        UserDto member = new UserDto();
        member.setId(-1);

        BlogDto BlogDto = new BlogDto();
        BlogDto.setName("test");
        BlogDto.setMembers(new ArrayList<>(Collections.singletonList(member)));

        Assert.assertNotNull(blogCreateService.createBlog(creator, BlogDto, null));
    }
}
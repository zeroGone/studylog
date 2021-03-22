package io.zerogone.blogmember.service;

import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogMemberCreateServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogMemberCreateService blogMemberCreateService;

    private CurrentUserInfo currentUserInfo;

    @Before
    public void setUp() {
        blogMemberCreateService = webApplicationContext.getBean(BlogMemberCreateService.class);
        currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setId(1);
        currentUserInfo.setName("김영곤");
        currentUserInfo.setNickName("zeroGone");
        currentUserInfo.setEmail("dudrhs571@gmail.com");
    }

    @Test
    public void testInstanceLoaded() {
        Assert.assertNotNull(blogMemberCreateService);
    }

    @Test
    public void createBlogMembers() {
        blogMemberCreateService.createBlogMembers(2, currentUserInfo, new ArrayList<>());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void createBlogMembers_IncludeSameBlogMember_ThrowBlogMembersStateException() {
        expectedException.expect(BlogMembersStateException.class);

        List<UserDto> members = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setId(1);
        members.add(userDto);
        blogMemberCreateService.createBlogMembers(1, currentUserInfo, members);

        members.clear();
        userDto = new UserDto();
        userDto.setId(2);
        members.add(userDto);
        userDto = new UserDto();
        userDto.setId(2);
        members.add(userDto);
        blogMemberCreateService.createBlogMembers(1, currentUserInfo, members);
    }
}
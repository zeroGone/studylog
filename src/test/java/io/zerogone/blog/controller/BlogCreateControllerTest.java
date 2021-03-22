package io.zerogone.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.model.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleBlogCreateApi_InValidBlogMemeber_ReturnBadRequest() throws Exception {
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);
        UserDto member = new UserDto();
        member.setId(Integer.MAX_VALUE);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test dto2");
        blogDto.setMembers(new ArrayList<>(Collections.singletonList(member)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", creator)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(blogDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void handleBlogCreateApi_IncludeSameBlogName_ReturnBadRequest() throws Exception {
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test dto");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", creator)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(blogDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void handleBlogCreateApi_BlogMemberIsNone_ReturnCreated() throws Exception {
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", creator)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(blogDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleBlogCreateApi_IncludeInvalidBlogMember_ReturnBadRequest() throws Exception {
        CurrentUserInfo creator = new CurrentUserInfo();
        creator.setId(1);

        UserDto userDto = new UserDto();
        userDto.setId(Integer.MAX_VALUE);

        BlogDto blogDto = new BlogDto();
        blogDto.setName("test create blog : 2021-03-22 16:52");
        blogDto.setMembers(Collections.singletonList(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", creator)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(blogDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
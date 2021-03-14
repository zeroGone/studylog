package io.zerogone.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.User;
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
import java.util.List;

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
    public void testRequestBodyDataIsProperlyMapped() throws Exception {
        User user = new User();
        user.setName("김영곤");
        user.setId(1);
        user.setEmail("dudrhs571@gmail.com");
        user.setNickName("zeroGone");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", user)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(makeJsonData()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private String makeJsonData() throws JsonProcessingException {
        BlogDto blogDto = makeBlogDto();
        blogDto.setMembers(makeUsers());
        System.out.println(new ObjectMapper().writeValueAsString(blogDto));
        return new ObjectMapper().writeValueAsString(blogDto);
    }

    private BlogDto makeBlogDto() {
        BlogDto blogDto = new BlogDto();
        blogDto.setName("신현청라 스터디팟");
        return blogDto;
    }

    private List<User> makeUsers() {
        User user2 = new User();
        user2.setId(1);
        user2.setName("모세현");
        user2.setNickName("모세");
        user2.setEmail("ahtpgus@naver.com");
        return new ArrayList<>(Collections.singletonList(user2));
    }
}
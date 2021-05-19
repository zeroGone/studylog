package io.zerogone.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.Email;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.service.UserSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PostCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserDto userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = webApplicationContext.getBean(UserSearchService.class).search(new Email("dudrhs571@gmail.com"));
    }

    @Test
    public void handlePostCreateApi() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setTitle("0517 1059");
        postDto.setContents("0517 1059");
        postDto.setCategories(Arrays.asList("project", "last3"));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/blogs/1/posts")
                .sessionAttr("userInfo", userInfo)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andDo(print());
    }
}
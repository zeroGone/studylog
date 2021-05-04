package io.zerogone.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.BlogDto;
import io.zerogone.model.dto.PostCreateDto;
import io.zerogone.model.vo.UserVo;
import io.zerogone.repository.UserDao;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PostCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserVo userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = new UserVo(webApplicationContext.getBean(UserDao.class).findByEmail("dudrhs571@gmail.com"));
    }

    @Test
    public void handlePostCreateApi() throws Exception {
        BlogDto blogDto = new BlogDto();
        blogDto.setName("studylog");

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setTitle("test0429");
        postCreateDto.setContents("4월 29일 테스트");
        postCreateDto.setBlog(blogDto);
        postCreateDto.setCategories(Collections.singletonList("study"));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/post")
                .sessionAttr("userInfo", userInfo)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(postCreateDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
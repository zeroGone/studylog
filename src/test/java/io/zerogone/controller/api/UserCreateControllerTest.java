package io.zerogone.controller.api;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    public void handleCreateUserApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .param("email", "5/4")
                .param("name", "5/4")
                .param("nickName", "5/4"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void handleCreateUserApi_WithImage_ReturnCreated() throws Exception {
        MockMultipartFile image = new MockMultipartFile("data", "test0405.txt", "text/plain", "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/api/user")
                .file("image", image.getBytes())
                .param("email", "5/4")
                .param("name", "5/4")
                .param("nickName", "5/4"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleCreateUserApi_WithId_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .param("id", "1")
                .param("email", "5/4")
                .param("name", "5/4")
                .param("nickName", "5/4"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void handleCreateUserApi_NickNameIsNull_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .param("email", "04/16 15:41")
                .param("name", "04/16 15:41"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
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
    public void handleCreateUserApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .param("email", "04/06 18:29")
                .param("name", "04/06 18:29")
                .param("nickName", "04/06 18:29"))
                .andDo(print())
                .andExpect(status().isCreated());

        MockMultipartFile image = new MockMultipartFile("data", "test0405.txt", "text/plain", "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/api/user")
                .file("image", image.getBytes())
                .param("email", "04/06 18:292")
                .param("name", "04/06 18:292")
                .param("nickName", "04/06 18:292"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleCreateUserApi_WithId_ReturnCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .param("id", "1")
                .param("email", "04/16 15:41")
                .param("name", "04/16 15:41")
                .param("nickName", "04/16 15:41"))
                .andDo(print())
                .andExpect(status().isCreated());
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
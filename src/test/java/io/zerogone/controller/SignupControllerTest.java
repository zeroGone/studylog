package io.zerogone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.UserDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class SignupControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getSignupViewName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup")
                .sessionAttr("visitor", new UserDto()))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    public void getSignupViewName_GivenNullSession_ReturnRedirectIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void signUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .param("name", "test 03/29 20:17")
                .param("email", "test 03/29 20:17")
                .param("nickName", "test 03/29 20:17"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void signUp_DuplicatedNickName_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .param("name", "test 03/29 20:03")
                .param("email", "test 03/29 20:03")
                .param("nickName", "zeroGone"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
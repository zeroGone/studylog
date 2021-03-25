package io.zerogone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.UserDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleLoginApi() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("김영곤");
        userDto.setEmail("dudrhs571@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
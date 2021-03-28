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
public class SignupControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleSignupApi() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("test 03/26");
        userDto.setNickName("test 03/26");
        userDto.setEmail("test 03/26");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleSignupApi_DuplicatedNickName_ReturnBadRequest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("test 03/26");
        userDto.setNickName("test 03/26");
        userDto.setEmail("test 03/26213131");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
package io.zerogone.controller.api;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserSearchControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleSearchingUserByEmail() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("email", "dudrhs571@naver.com"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void handleSearchingUserByEmail_ParamIsEmptystring_ReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("email", " "))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void handleSearchingUserByEmail_EmailIsNull_ReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void handleSearchingUserByEmail_EmailIsNotEmailString_ReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("email", "dudrhs571"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void handleSearchingUserByEmail_ParamIsEmptystring_ReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("email", " "))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
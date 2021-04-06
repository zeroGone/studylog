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
    public void handleUserSearchApi() throws Exception {
        mockMvc.perform(get("/api/user").param("email", "ahtpgus@naver.com"))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/user").param("email", "%"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void handleUserSearchApi_ParamIsEmptystring_ReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("email", ""))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
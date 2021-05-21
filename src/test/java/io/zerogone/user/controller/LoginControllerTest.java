package io.zerogone.user.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void doLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .param("email", "dudrhs571@gmail.com")
                .param("name", "김영곤"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/mypage"));
    }

    @Test
    public void doLogin_NotExistedUser_RedirectSignupPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .param("email", "dudrhs571@gmail.com")
                .param("name", "누구게"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/signup"));
    }

    @Test
    public void doLogin_EmailIsBlank_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .param("name", "누구게"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
package io.zerogone.web;

import io.zerogone.config.WebConfiguration;
import io.zerogone.domain.entity.User;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserDao userDao;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void WhenLogin_GivenUser_ThenRedirectMyPage() throws Exception {
        User user = new User("name", "nick", "dudrhs571@gmail.com", "imageUrl");
        userDao.save(user);

        mockMvc.perform(post("/login")
                .param("email", "dudrhs571@gmail.com")
                .param("name", "name"))
                .andDo(print());
    }

    @Test
    public void WhenLogin_GivenNull_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void WhenLogin_GivenEmailIsBlank_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", " ")
                .param("name", "name"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void WhenLogin_GivenNameIsBlank_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", "dudrhs571@gmail.com")
                .param("name", " "))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void WhenLogin_GivenEmailIsNotEmailForm_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/login")
                .param("email", "email")
                .param("name", "name"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}

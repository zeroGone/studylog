package io.zerogone.web;

import io.zerogone.config.WebConfiguration;
import io.zerogone.domain.entity.User;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class LogoutControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLogout() throws Exception {
        User user = new User(null, null, null, null);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout")
                .sessionAttr("user", user))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void WhenLogout_GivenNotThing_ThenRedriectIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }
}
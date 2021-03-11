package io.zerogone.filter;

import io.zerogone.config.WebConfiguration;
import io.zerogone.model.User;
import org.junit.Assert;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class LoginCheckFilterTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(webApplicationContext.getBean(LoginCheckFilter.class)).build();
    }

    @Test
    public void testFilterLoaded() {
        Assert.assertNotNull(webApplicationContext.getBean(LoginCheckFilter.class));
    }

    @Test
    public void testFilterIsWorking() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
        mockMvc.perform(get("/mypage")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/mypage").sessionAttr("userInfo", new User())).andExpect(status().isOk()).andExpect(view().name("mypage"));
        mockMvc.perform(get("/issue/1")).andExpect(status().is3xxRedirection());
    }
}
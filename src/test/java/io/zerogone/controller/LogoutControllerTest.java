package io.zerogone.controller;

import io.zerogone.config.WebConfiguration;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.UserWithBlogsSearchService;
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
public class LogoutControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserDto userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = webApplicationContext.getBean(UserWithBlogsSearchService.class).search("dudrhs571@naver.com");
    }

    @Test
    public void doLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/logout").sessionAttr("userInfo", userInfo))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void doLogout_NotExistedUserInfoInSession_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/logout"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
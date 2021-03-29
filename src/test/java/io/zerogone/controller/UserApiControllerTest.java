package io.zerogone.controller;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.CurrentUserInfo;
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
public class UserApiControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private CurrentUserInfo userInfo;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = new CurrentUserInfo();
        userInfo.setId(1);
        userInfo.setEmail("dudrhs571@gmail.com");
        userInfo.setName("김영곤");
        userInfo.setNickName("zeroGone");
        userInfo.setImgUrl("/img/user-default/1.png");
    }

    @Test
    public void handleUserSearchApi() throws Exception {
        mockMvc.perform(get("/api/user").sessionAttr("userInfo", userInfo).param("email", "ahtpgus@naver.com"))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/user").sessionAttr("userInfo", userInfo).param("email", "%"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void handleUserSearchApi_IncludeMemeberisSameSessionUser_ReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/user")
                .sessionAttr("userInfo", userInfo)
                .param("email", "dudrhs571@gmail.com"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
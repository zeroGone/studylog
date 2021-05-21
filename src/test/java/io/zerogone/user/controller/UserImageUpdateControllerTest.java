package io.zerogone.controller.api;

import com.google.common.io.Files;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.LoginCheckFilter;
import io.zerogone.user.model.Email;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.service.UserSearchService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserImageUpdateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserDto userInfo;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(new LoginCheckFilter()).build();
        userInfo = webApplicationContext.getBean(UserSearchService.class).search(new Email("dudrhs571@gmail.com"));
    }

    @Test
    public void handleUpdatingUserImage() throws Exception {
        UserDto afterUserInfo = (UserDto) mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/user/1")
                .file("image", Files.toByteArray(new File("C:\\Users\\younggon\\Desktop\\제목 없음.png")))
                .sessionAttr("userInfo", userInfo))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getRequest()
                .getSession()
                .getAttribute("userInfo");

        System.out.println(afterUserInfo.getImageUrl() + " " + userInfo.getImageUrl());
        Assert.assertNotEquals(afterUserInfo.getImageUrl(), userInfo.getImageUrl());
    }
}
package io.zerogone.user.controller;

import io.zerogone.config.WebConfiguration;
import io.zerogone.user.LoginCheckFilter;
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(new LoginCheckFilter()).build();
    }

    @Test
    public void doLogout() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("dudrhs571@gmail.com");
        userDto.setName("김영곤");
        userDto.setNickName("zeroGone");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout").sessionAttr("userInfo", userDto))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void doLogout_NotExistedUserInfoInSession_ReturnUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLogout_UserInfoIdIsNull_ReturnUnAuthorized() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("김영곤");
        userDto.setEmail("dudrhs571@gmail.com");
        userDto.setNickName("zeroGone");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLogout_UserInfoNameIsBlank_ReturnUnAuthorized() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("dudrhs571@gmail.com");
        userDto.setNickName("zeroGone");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLogout_UserInfoEmailIsBlank_ReturnUnAuthorized() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("김영곤");
        userDto.setNickName("zeroGone");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLogout_UserInfoEmailIsntEmail_ReturnUnAuthorized() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("김영곤");
        userDto.setEmail("dudrhs571");
        userDto.setNickName("zeroGone");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doLogout_NickNameIsBlank_ReturnUnAuthorized() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("김영곤");
        userDto.setEmail("dudrhs571");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/logout"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
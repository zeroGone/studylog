package io.zerogone.web;

import io.zerogone.config.WebConfiguration;
import io.zerogone.domain.LoginRequestForm;
import io.zerogone.web.filter.SignupPageFilter;
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

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class SignupControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private LoginRequestForm myForm;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(new SignupPageFilter()).build();
        myForm = new LoginRequestForm();
        myForm.setName("김영곤");
        myForm.setEmail("dudrhs571@gmail.com");
    }

    @Test
    public void getSignupViewName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup")
                .sessionAttr("visitor", myForm))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andDo(print());
    }

    @Test
    public void WhenGetSignupView_GivenNotthing_ThenReturnUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void WhenGetSignupView_GivenVisitorNameIsBlank_ThenReturnUnAuthorized() throws Exception {
        LoginRequestForm loginRequestForm = new LoginRequestForm();
        loginRequestForm.setEmail("dudrhs571@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup").sessionAttr("visitor", loginRequestForm))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void WhenGetSignupView_GivenVisitorEmailIsBlank_ThenReturnUnAuthorized() throws Exception {
        LoginRequestForm loginRequestForm = new LoginRequestForm();
        loginRequestForm.setName("Hi");
        loginRequestForm.setEmail(" ");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup").sessionAttr("visitor", loginRequestForm))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void WhenGetSignupView_GivenVisitorEmailIsNotEmail_ThenReturnUnAuthorized() throws Exception {
        LoginRequestForm loginRequestForm = new LoginRequestForm();
        loginRequestForm.setName("김영곤");
        loginRequestForm.setEmail("dudrhs571");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/signup").sessionAttr("visitor", loginRequestForm))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    public void signup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("email", myForm.getEmail())
                .param("nickName", "zeroGone"))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("/mypage"))
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenVisitorIsNull_ThenReturnUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .param("name", myForm.getName())
                .param("email", myForm.getEmail())
                .param("nickName", "zeroGone"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenNameIsBlank_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("email", myForm.getEmail())
                .param("nickName", "zeroGone"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenEmailIsBlank_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("nickName", "zeroGone"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenEmailIsNotEmail_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("email", "dudrhs571")
                .param("nickName", "zeroGone"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenNickNameIsBlank_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("email", "dudrhs571"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    public void WhenSignup_GivenNickNameIsDuplicated_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("nickName", "zeroGone")
                .param("email", myForm.getEmail()));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .sessionAttr("visitor", myForm)
                .param("name", myForm.getName())
                .param("nickName", "zeroGone")
                .param("email", "testNickName@test.com"))
                .andDo(print());
    }
}
package io.zerogone.controller.api;

import io.zerogone.config.WebConfiguration;
import io.zerogone.model.UserVo;
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
public class BlogCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserVo userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = new UserVo(1, "dudrhs571@gmail.com", "김영곤", "zeroGone", null, null, null);
    }

    @Test
    public void handleBlogCreateApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "4월 8일 테스트 17:42")
                .param("members[0].id", "4")
                .param("members[0].name", "김영곤")
                .param("members[0].email", "dudrhs571@naver.com")
                .param("members[0].nickName", "zeroGone7247"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleBlogCreateApi_InValidBlogMemeber_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "test dto")
                .param("members[0].id", Integer.toString(Integer.MAX_VALUE)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void handleBlogCreateApi_IncludeSameBlogName_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "studylog"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void handleBlogCreateApi_BlogMemberIsNone_ReturnCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "test03/30 16:23"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleBlogCreateApi_IncludeInvalidBlogMember_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "test create blog : 2021-03-22 16:52")
                .param("members[0].id", Integer.toString(Integer.MAX_VALUE)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

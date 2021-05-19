package io.zerogone.controller.api;

import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.Email;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.service.UserSearchService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserDto userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = webApplicationContext.getBean("userSearchService", UserSearchService.class).search(new Email("dudrhs571@naver.com"));
    }

    @Test
    @Transactional
    public void handleBlogCreateApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/blogs").sessionAttr("userInfo", userInfo)
                .param("name", "studylog")
                .param("members[0].id", "123213")
                .param("members[0].name", "김영곤")
                .param("members[0].email", "dudrhs571@gmail.com")
                .param("members[0].nickName", "zeroGone"))
                .andDo(print());
    }

    @Test
    public void handleBlogCreateApi_ModelTypeIsNotEquals_ReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("test", "hi"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleBlogCreateApi_BlogNameIsBlank_ReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", ""))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void handleBlogCreateApi_InValidBlogMemeber_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/blog").sessionAttr("userInfo", userInfo)
                .param("name", "test dto")
                .param("members[0].id", "1")
                .param("members[1].id", "1"))
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
    @Transactional
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

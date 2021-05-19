package io.zerogone.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.Email;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.model.MemberRole;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogUpdateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private UserDto userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = webApplicationContext.getBean(UserSearchService.class).search(new Email("dudrhs571@gmail.com"));
    }

    @Test
    public void handleUpdatingBlog() throws Exception {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(1);
        blogDto.setName("studylog");
        blogDto.setIntroduce("test123");

        BlogMemberDto blogMemberDto1 = new BlogMemberDto();
        blogMemberDto1.setId(1);
        blogMemberDto1.setEmail("dudrhs571@gmail.com");
        blogMemberDto1.setRole(MemberRole.ADMIN);

        BlogMemberDto blogMemberDto2 = new BlogMemberDto();
        blogMemberDto2.setId(4);
        blogMemberDto2.setEmail("dudrhs571@gmail.com");

        blogDto.setMembers(Arrays.asList(blogMemberDto1, blogMemberDto2));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/blogs/1")
                .sessionAttr("userInfo", userInfo)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(blogDto)))
                .andDo(print());
    }

    @Test
    public void handleUpdatingBlogWithImage() {
    }
}
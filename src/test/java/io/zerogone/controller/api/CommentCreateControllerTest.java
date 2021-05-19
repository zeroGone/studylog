package io.zerogone.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.user.model.Email;
import io.zerogone.blog.post.comment.model.CommentDto;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CommentCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    public void handleCreatingCommentApi() throws Exception {
        UserDto userInfo = webApplicationContext.getBean(UserSearchService.class).search(new Email("dudrhs571@gmail.com"));
        CommentDto commentDto = new CommentDto();
        commentDto.setContents("test");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/blogs/1/posts/1/comments")
                .sessionAttr("userInfo", userInfo)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(commentDto)))
                .andDo(print());
    }
}
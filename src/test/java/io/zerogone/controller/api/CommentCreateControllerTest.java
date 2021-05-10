package io.zerogone.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.dto.CommentDto;
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
public class CommentCreateControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private UserDto userInfo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userInfo = webApplicationContext.getBean(UserWithBlogsSearchService.class).search("dudrhs571@gmail.com");
    }

    @Test
    public void handleCreatingCommentApi() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setPostId(1);
        commentDto.setContents("test");
        commentDto.setWriter(new UserDto());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/comment")
                .sessionAttr("userInfo", userInfo)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(commentDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
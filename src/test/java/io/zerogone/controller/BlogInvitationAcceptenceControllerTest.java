//package io.zerogone.controller;
//
//import io.zerogone.config.WebConfiguration;
//import io.zerogone.user.model.Email;
//import io.zerogone.user.model.UserDto;
//import io.zerogone.service.search.UserWithBlogsSearchService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.transaction.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = WebConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
//@WebAppConfiguration
//public class BlogInvitationAcceptenceControllerTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    private UserDto userInfo;
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        userInfo = webApplicationContext.getBean(UserWithBlogsSearchService.class).search(new Email("dudrhs571@gmail.com"));
//    }
//
//    @Test
//    @Transactional
//    public void getBlogAcceptViewName() throws Exception {
//        mockMvc.perform(get("/blog/accept").param("key", "535090032535106832535804982853944")
//                .sessionAttr("userInfo", userInfo))
//                .andExpect(status().isOk())
//                .andExpect(view().name("blog_accept"))
//                .andDo(print());
//    }
//}
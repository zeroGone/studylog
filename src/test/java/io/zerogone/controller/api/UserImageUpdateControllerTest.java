//package io.zerogone.controller.api;
//
//import io.zerogone.config.DatabaseConfiguration;
//import io.zerogone.config.WebConfiguration;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.transaction.Transactional;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
//@WebAppConfiguration
//public class UserImageUpdateControllerTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    @Transactional
//    public void handleUpdatingUserImage() throws Exception {
//        MockMultipartFile image = new MockMultipartFile("data", "test0405.txt", "text/plain", "some xml".getBytes());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .fileUpload("/api/user/1")
//                .file("image", image.getBytes())
//                .sessionAttr("userInfo", new UserVo(1, null, null, null, null)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//}
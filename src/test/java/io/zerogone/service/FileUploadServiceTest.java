package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class FileUploadServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private FileUploadService fileUploadService;

    @Before
    public void setUp() {
        fileUploadService = webApplicationContext.getBean(FileUploadService.class);
    }

    @Test
    public void uploadFile() throws IOException {
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        Assert.assertEquals("C:\\tmp\\filename.txt", fileUploadService.uploadFile(firstFile));
    }

    @Test
    public void uploadFile_FileIsNull_ReturnNull() throws IOException {
        Assert.assertNull(fileUploadService.uploadFile(null));
    }
}
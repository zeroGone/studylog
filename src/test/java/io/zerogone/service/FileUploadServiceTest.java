package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.FileUploadException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

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
    public void uploadFile() {
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        Assert.assertNotNull(fileUploadService.uploadFile("img/user", firstFile));
    }

    @Test
    public void uploadFile_FileIsNull_ReturnNull() {
        Assert.assertNull(fileUploadService.uploadFile("img/user", null));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void uploadFile_InvalidFile_ThrowFileUploadException() {
        expectedException.expect(FileUploadException.class);
        MockMultipartFile file = new MockMultipartFile("data", "", "?", "some xml".getBytes());
        Assert.assertNotNull(fileUploadService.uploadFile("img/user/", file));
    }
}
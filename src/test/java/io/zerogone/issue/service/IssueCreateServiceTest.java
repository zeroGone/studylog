package io.zerogone.issue.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.issue.model.IssueCategory;
import io.zerogone.issue.model.IssueDto;
import io.zerogone.issue.model.IssueVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class IssueCreateServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private IssueCreateService issueCreateService;

    @Before
    public void setUp() {
        issueCreateService = webApplicationContext.getBean(IssueCreateService.class);
    }

    @Test
    @Transactional
    public void createIssueAfterConverting() {
        IssueDto testInstance = new IssueDto();
        testInstance.setTitle("test");
        testInstance.setContents("instance");
        testInstance.setCategory(IssueCategory.REQUEST);
        testInstance.setBlogMemeberId(1);

        IssueVo vo = issueCreateService.createIssueAfterConverting(testInstance);

        Assert.assertNotNull(vo);
        Assert.assertNotEquals(0, vo.getId());
    }
}
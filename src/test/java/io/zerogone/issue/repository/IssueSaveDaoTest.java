package io.zerogone.issue.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.issue.model.Issue;
import io.zerogone.issue.model.IssueCategory;
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
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class IssueSaveDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private IssueSaveDao issueSaveDao;

    @Before
    public void setUp() {
        issueSaveDao = webApplicationContext.getBean(IssueSaveDao.class);
    }

    @Test
    @Transactional
    public void save() {
        Issue testInstance = new Issue("test", "instance", IssueCategory.REQUEST, 1);
        issueSaveDao.save(testInstance);

        Assert.assertNotEquals(0, testInstance.getId());
        Assert.assertEquals(LocalDate.now(), testInstance.getCreateDate());
    }
}
package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.BlogVo;
import io.zerogone.model.UserVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogSearchServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogSearchService blogSearchService;

    @Before
    public void setUp() {
        blogSearchService = webApplicationContext.getBean(BlogSearchService.class);
    }

    @Test
    public void getBlogByName() {
        BlogVo blogVo = blogSearchService.getBlogVoByName("studylog");
        Assert.assertNotNull(blogVo);
        Assert.assertEquals(blogVo.getName(), "studylog");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getBlog_NotExistedName_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotNull(blogSearchService.getBlogVoByName("jinmin is genius"));
    }

    @Test
    public void getBlogVosByUserVos() {
        UserVo userVo = new UserVo(1, null, null, null, null, null, null);
        List<BlogVo> blogVos = blogSearchService.getBlogVosByUserVo(userVo);

        Assert.assertNotNull(blogVos);
        Assert.assertNotEquals(0, blogVos.size());
    }
}
package io.zerogone.service.search;

import io.zerogone.common.service.SearchService;
import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.blog.post.model.PostDto;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class PostSearchServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private SearchService<Integer, PostDto> searchService;

    @Before
    public void setUp() {
        searchService = (SearchService<Integer, PostDto>) webApplicationContext.getBean("postSearchService");
    }

    @Test
    public void search() {
        Assert.assertNotNull(searchService.search(1));
    }
}
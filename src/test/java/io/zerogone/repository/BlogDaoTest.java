package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.BlogDto;
import io.zerogone.model.entity.Blog;
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

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class BlogDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private BlogDao blogDao;

    @Before
    public void setUp() {
        blogDao = webApplicationContext.getBean(BlogDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void save() {
        BlogDto blogDto = new BlogDto();
        blogDto.setName("testBlog");
        blogDto.setIntroduce("This is temporary instance fot testing");
        Blog blog = new Blog(blogDto);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    @Transactional
    public void save_BlogNameIsNull_ThrowNotPropertyException() {
        expectedException.expect(NotNullPropertyException.class);
        Blog blog = new Blog(new BlogDto());
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    @Transactional
    public void save_BlogNameIsDuplicated_ThrowPersistenceException() {
        expectedException.expect(PersistenceException.class);
        BlogDto blogDto = new BlogDto();
        blogDto.setName("studylog");
        Blog blog = new Blog(blogDto);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    public void findByName() {
        Assert.assertNotNull(blogDao.findByName("studylog"));
    }

    @Test
    public void findByName_invalideName_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotNull(blogDao.findByName("zinmin is genius"));
    }
}
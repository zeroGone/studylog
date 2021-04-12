package io.zerogone.repository;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
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
import java.util.List;

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
        Blog blog = new Blog(0, "testBlog", "This is temporary instance fot testing", null);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    @Transactional
    public void save_BlogNameIsNull_ThrowPersistenceException() {
        expectedException.expect(PersistenceException.class);
        Blog blog = new Blog(0, null, null, null);
        blogDao.save(blog);
        Assert.assertNotEquals(0, blog.getId());
    }

    @Test
    @Transactional
    public void save_BlogNameIsDuplicated_ThrowPersistenceException() {
        expectedException.expect(PersistenceException.class);
        Blog blog = new Blog(0, "studylog", null, null);
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

    @Test
    public void findAllByUserAndBlogMemberRoleIsAdminOrMember() {
        User user = new User(1, null, null, null, null);
        List<Blog> blogs = blogDao.findAllByUserAndBlogMemberRoleIsAdminOrMember(user);
        Assert.assertNotEquals(0, blogs.size());
    }
}
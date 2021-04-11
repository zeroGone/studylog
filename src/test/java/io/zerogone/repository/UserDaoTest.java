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
public class UserDaoTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = webApplicationContext.getBean(UserDao.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @Transactional
    public void save() {
        User user = new User(0, "4/10 test", "4/10 test", "4/10 test", null);
        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void save_GivenNullName_throwPersistException() {
        expectedException.expect(PersistenceException.class);
        User user = new User(0, null, "4/10 test", "4/10 test", null);
        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void save_GivenNullEmail_throwPersistException() {
        expectedException.expect(PersistenceException.class);
        User user = new User(0, "4/10 test", null, "4/10 test", null);
        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void save_GivenNullNickName_throwPersistException() {
        expectedException.expect(PersistenceException.class);
        User user = new User(0, "4/10 test", "4/10 test", null, null);
        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void save_GivenId_throwPersistException() {
        expectedException.expect(PersistenceException.class);
        User user = new User(10, "4/10 test", "4/10 test", null, null);
        userDao.save(user);
        Assert.assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void updateImageUrl() {
        User user = new User(1, "dudrhs571@gmail.com", "김영곤", "zeroGone7247", "/img/user-default/3.png");
        userDao.updateImageUrl(user);
        Assert.assertNotNull(user.getImageUrl());
        Assert.assertEquals("/img/user-default/3.png", user.getImageUrl());
    }

    @Test
    @Transactional
    public void updateImageUrl_NotSameName_Success() {
        User user = new User(1, "dudrhs571@gmail.com", "test", "zeroGone7247", "https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/myimg.jpg");
        userDao.updateImageUrl(user);
        Assert.assertNotNull(user.getImageUrl());
        Assert.assertEquals("https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/myimg.jpg", user.getImageUrl());
    }

    @Test
    @Transactional
    public void updateImageUrl_NullProperties_Success() {
        User user = new User(1, null, null, null, "https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/myimg.jpg");
        userDao.updateImageUrl(user);
        Assert.assertNotNull(user.getImageUrl());
        Assert.assertEquals("https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/myimg.jpg", user.getImageUrl());
    }

    @Test
    public void findUserByEmail() {
        User user = userDao.findByEmail("dudrhs571@gmail.com");
        Assert.assertNotNull(user);
        Assert.assertNotEquals(0, user.getId());
        Assert.assertEquals("dudrhs571@gmail.com", user.getEmail());
    }

    @Test
    public void fidnUserByEmail_GivenNull_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userDao.findByEmail(null).getId());
    }

    @Test
    public void findUserByEmail_GivenEmptyString_ThrowNotExistedDataException() {
        expectedException.expect(NotExistedDataException.class);
        Assert.assertNotEquals(0, userDao.findByEmail("").getId());
    }

    @Test
    public void findAllByBlogAndBlogMemberRoleIsAdminOrMember() {
        Blog blog = new Blog(1, null, null, null);
        List<User> users = userDao.findAllByBlogAndBlogMemberRoleIsAdminOrMember(blog);
        Assert.assertNotNull(users);
        Assert.assertNotEquals(0, users.size());
    }

    @Test
    public void findAllByBlogAndBlogMemberRoleIsAdminOrMember_GivenIdIsZeroBlog_IllegalStateException() {
        expectedException.expect(IllegalStateException.class);
        Blog blog = new Blog(0, null, null, null);
        List<User> users = userDao.findAllByBlogAndBlogMemberRoleIsAdminOrMember(blog);
        Assert.assertNotNull(users);
        Assert.assertNotEquals(0, users.size());
    }
}
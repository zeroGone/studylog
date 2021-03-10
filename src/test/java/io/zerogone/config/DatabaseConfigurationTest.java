package io.zerogone.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class DatabaseConfigurationTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void testDataSourceLoaded() {
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void testEntityManagerRegistered() {
        Assert.assertNotNull(entityManagerFactory);
        Assert.assertEquals("true", entityManagerFactory.getProperties().get("hibernate.show_sql"));
        Assert.assertEquals("org.hibernate.dialect.MySQL5InnoDBDialect", entityManagerFactory.getProperties().get("hibernate.dialect"));
    }
}
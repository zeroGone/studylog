package io.zerogone.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DatabaseConfiguration {
    private static final String[] MODEL_PACKAGE_LOCATION = new String[]{
            "io.zerogone.user.model",
            "io.zerogone.blog.model",
            "io.zerogone.blogmember.model"
    };

    private final Environment environment;

    public DatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(Objects.requireNonNull(environment.getProperty("spring.datasource.url")));
        dataSource.setUsername(Objects.requireNonNull(environment.getProperty("spring.datasource.username")));
        dataSource.setPassword(Objects.requireNonNull(environment.getProperty("spring.datasource.password")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(MODEL_PACKAGE_LOCATION);

        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getJpaProperties());
        return entityManagerFactory;
    }

    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", Objects.requireNonNull(environment.getProperty("hibernate.dialect")));
        properties.setProperty("hibernate.show_sql", Objects.requireNonNull(environment.getProperty("hibernate.show_sql")));
        properties.setProperty("hibernate.format_sql", Objects.requireNonNull(environment.getProperty("hibernate.format_sql")));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }
}

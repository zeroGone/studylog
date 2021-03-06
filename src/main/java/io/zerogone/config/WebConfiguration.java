package io.zerogone.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;
import java.util.Set;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "io.zerogone")
public class WebConfiguration extends WebMvcConfigurerAdapter {
    private static final int TEN_MEGA_BYTE = 10 * 1024 * 1024;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(TEN_MEGA_BYTE);
        multipartResolver.setMaxUploadSizePerFile(TEN_MEGA_BYTE);
        return multipartResolver;
    }

    @Bean
    public AmazonS3 AwsS3Client(@Value("${aws.s3.accesskey}") String accesskey,
                                @Value("${aws.s3.secretkey}") String secretkey) {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey, secretkey)))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    @Bean
    public JavaMailSender getJavaMailSender(@Value("${mail.username}") String username,
                                            @Value("${mail.password}") String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public ConversionServiceFactoryBean conversionServiceFactory(Set<Converter<?, ?>> converters) {
        ConversionServiceFactoryBean conversionServiceFactory = new ConversionServiceFactoryBean();
        conversionServiceFactory.setConverters(converters);
        return conversionServiceFactory;
    }

    @Bean
    public ConversionService conversionService(ConversionServiceFactoryBean factory) {
        return factory.getObject();
    }

    @Bean
    public ConfigurableWebBindingInitializer webBindingInitializer(ConversionService conversionService) {
        ConfigurableWebBindingInitializer configurableWebBindingInitializer = new ConfigurableWebBindingInitializer();
        configurableWebBindingInitializer.setConversionService(conversionService);
        return configurableWebBindingInitializer;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setProxyTargetClass(true);
        return postProcessor;
    }
}

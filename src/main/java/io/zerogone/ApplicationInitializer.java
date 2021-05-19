package io.zerogone;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    private static final String ROOT_PACKAGE = "io.zerogone";

    private static final String DISPATCHER_NAME = "dispatcher";
    private static final int DISPATCHER_LOAD_NUMBER = 1;
    private static final String DISPATCHER_MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan(ROOT_PACKAGE);

        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic appServlet = servletContext.addServlet(DISPATCHER_NAME, new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(DISPATCHER_LOAD_NUMBER);
        appServlet.addMapping(DISPATCHER_MAPPING_URL);
    }
}

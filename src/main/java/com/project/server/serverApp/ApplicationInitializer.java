package com.project.server.serverApp;


import com.project.server.serverApp.config.*;
import com.project.server.serverApp.config.persistence.SystemPersistenceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SystemPersistenceConfig.class, SecurityConfig.class, AuthorizationServerConfig.class, ResourceServerConfig.class, MethodSecurityConfig.class};

    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

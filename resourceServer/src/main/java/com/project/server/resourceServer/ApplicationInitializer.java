package com.project.server.resourceServer;



import com.project.server.resourceServer.config.MethodSecurityConfig;
import com.project.server.resourceServer.config.ResourceServerConfig;
import com.project.server.resourceServer.config.RootConfig;
import com.project.server.resourceServer.config.WebConfig;
import com.project.server.resourceServer.config.persistence.DataPersistenceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;




public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class, ResourceServerConfig.class, MethodSecurityConfig.class, DataPersistenceConfig.class};

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

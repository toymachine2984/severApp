package com.project.server.authServer.config;


import com.project.server.authServer.config.persistence.SystemPersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SystemPersistenceConfig.class, SecurityConfig.class, AuthorizationServerConfig.class})
@ComponentScan({"com.project.server.authServer.service", "com.project.server.authServer.util"})
public class RootConfig{
}

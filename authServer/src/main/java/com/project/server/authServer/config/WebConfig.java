package com.project.server.authServer.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan({"com.project.server.authServer.service", "com.project.server.authServer.repository", "com.project.server.authServer.controller"})
public class WebConfig implements WebMvcConfigurer {
}

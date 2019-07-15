package com.project.server.resourceServer.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
@ComponentScan({"com.project.server.resourceServer.controller"})
public class WebConfig implements WebMvcConfigurer {

}

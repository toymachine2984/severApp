package com.project.server.authServer.config.persistence;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "systemEntityManagerFactory",
        transactionManagerRef = "systemTransactionManager",
        basePackages = {"com.project.server.authServer.repository"})
public class SystemPersistenceConfig extends BasePersistenceConfig {


    @Bean(name = "systemEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        return getEntityManagerFactory(super.dataSource(), "com.project.server.authServer.entity");
    }


    @Bean(name = "systemTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() throws NamingException {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}

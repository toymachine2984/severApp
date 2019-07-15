package com.project.server.resourceServer.config.persistence;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "dataEntityManagerFactory",
        transactionManagerRef = "dataTransactionManager",
        basePackages = {"com.project.server.resourceServer.repository"}
)
public class DataPersistenceConfig extends BasePersistenceConfig {


    @Bean(name = "dataEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        return getEntityManagerFactory(super.dataSource(), "com.project.server.resourceServer.entity");
    }


    @Bean(name = "dataTransactionManager")
    public PlatformTransactionManager transactionManager() throws NamingException {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

}

package com.project.server.serverApp.config.persistence;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "systemEntityManagerFactory",
        transactionManagerRef = "systemTransactionManager",
        basePackages = {"com.project.server.serverApp.repository.systemRepository"})
public class SystemPersistenceConfig extends BasePersistenceConfig {

    @Value(value = "${url}")
    private String url;


    @Bean(name = "systemEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        return getEntityManagerFactory(dataSource(), "com.project.server.serverApp.entity.system");
    }

    @Bean
    @Primary
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(url);
    }

    @Bean(name = "systemTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() throws NamingException {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}

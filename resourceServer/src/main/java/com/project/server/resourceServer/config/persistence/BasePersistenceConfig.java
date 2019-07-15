package com.project.server.resourceServer.config.persistence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Component
@PropertySource({"classpath:db.properties"})
public class BasePersistenceConfig {

    private Environment env;

    @Value(value = "${url}")
    private String url;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource, String... packagesToScan) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan(packagesToScan);
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties());

        return localContainerEntityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                setProperty("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
                setProperty("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
                setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
            }
        };
    }


    @Bean
    @Primary
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(url);
    }
}

package com.github.backend_1st.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.github.backend_1st.repository.users",
                        "com.github.backend_1st.repository.posts",
                        "com.github.backend_1st.repository.comments",
                        "com.github.backend_1st.repository.roles",
                        "com.github.backend_1st.repository.userDetails",
                        "com.github.backend_1st.repository.userPrincipal",
                        "com.github.backend_1st.repository.likes",},
        entityManagerFactoryRef = "entityManagerFactoryBean1"
)
public class JpaConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean1() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.github.backend_1st.repository.users",
                            "com.github.backend_1st.repository.posts",
                            "com.github.backend_1st.repository.comments",
                            "com.github.backend_1st.repository.roles",
                            "com.github.backend_1st.repository.userDetails",
                            "com.github.backend_1st.repository.userPrincipal",
                            "com.github.backend_1st.repository.likes");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comment", "true");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());

        return dataSource;
    }
}

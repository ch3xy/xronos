package com.ch3xy.xronos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(value = "com.ch3xy.xronos", considerNestedRepositories = true)
public class PersistenceConfiguration {

    @Autowired
    private DataSource dataSource;
    @Value("${hibernate.default_schema}")
    private String defaultSchema;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.jdbc.batch_size}")
    private String jdbcBatchSize;
    @Value("${hibernate.jdbc.batch_size}")
    private String jdbcFetchSize;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceUnitName(("application"));
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.default_schema", defaultSchema);
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.jdbc.batch_size", jdbcBatchSize);
        properties.setProperty("hibernate.jdbc.fetch_size", jdbcFetchSize);
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        properties.setProperty("org.hibernate.flushMode", "ALWAYS");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");
        return properties;
    }
}

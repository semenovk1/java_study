package com.study.backend.config;

import com.querydsl.sql.OracleTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {

        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionPoolName(environment.getProperty("spring.datasource.driver-class-namespring.datasource.oracleucp.connection-pool-name"));
        pds.setURL(environment.getProperty("spring.datasource.url"));
        pds.setUser(environment.getProperty("spring.datasource.username"));
        pds.setPassword(environment.getProperty("spring.datasource.password"));
        pds.setConnectionFactoryClassName(environment.getProperty("spring.datasource.oracleucp.connection-factory-class-name"));
        pds.setValidateConnectionOnBorrow(true);
        pds.setInitialPoolSize(environment.getRequiredProperty("spring.datasource.oracleucp.initial-pool-size", Integer.class));
        pds.setMinPoolSize(environment.getRequiredProperty("spring.datasource.oracleucp.min-pool-size", Integer.class));
        pds.setMaxPoolSize(environment.getRequiredProperty("spring.datasource.oracleucp.max-pool-size", Integer.class));

        return pds;
    }

    @Bean
    public com.querydsl.sql.Configuration querydslConfiguration(DataSource dataSource) {

        SQLTemplates templates = OracleTemplates.builder()
                                                .printSchema()
                                                .build();

        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);

        return configuration;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SQLQueryFactory queryFactory(DataSource dataSource) {

        SpringConnectionProvider provider = new SpringConnectionProvider(dataSource);

        return new SQLQueryFactory(querydslConfiguration(dataSource), provider);
    }
}

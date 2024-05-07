package com.study.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean scheduler(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setAutoStartup(false);
        schedulerFactory.setSchedulerName("test");

        return schedulerFactory;
    }
}

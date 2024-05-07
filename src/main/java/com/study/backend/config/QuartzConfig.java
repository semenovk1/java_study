package com.study.backend.config;

import com.study.backend.quartz.AutowiringSpringBeanJobFactory;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean scheduler(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setAutoStartup(false);
        schedulerFactory.setSchedulerName("test");

        return schedulerFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        factoryBean.setJobFactory(jobFactory);    // Set jobFactory to AutowiringSpringBeanJobFactory
        return factoryBean;
    }

    @Bean
    public Scheduler getScheduller(SchedulerFactoryBean factoryBean){
        return factoryBean.getScheduler();

    }

}

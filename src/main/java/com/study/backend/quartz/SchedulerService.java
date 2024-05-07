package com.study.backend.quartz;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class SchedulerService {

    @Autowired
    Scheduler scheduler;

    @PostConstruct
    void init() throws Exception{
        scheduler.start();
    }

    public void startTestJob() throws Exception {

        JobDetail jobDetail;
        JobBuilder jobBuilder = JobBuilder.newJob(EmployeeQueryJob.class);
        jobBuilder.withIdentity(UUID.randomUUID().toString());
        jobDetail = jobBuilder.build();

        JobKey key = jobDetail.getKey();

        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity(UUID.randomUUID().toString())
                                        .forJob(key)
                                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
                                        .build();

        scheduler.scheduleJob(jobDetail, trigger);

    }
}

package com.study.backend.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchedService {
    @Autowired
    private Scheduler scheduler;

    public void Start()  throws Exception {
        scheduler.start();
    }

    public void scheduleJob() throws Exception {
        JobDetail jobDetail;
        JobBuilder jobBuilder = JobBuilder.newJob(TestJob.class);

        jobBuilder.withIdentity(UUID.randomUUID().toString());
        jobDetail = jobBuilder.build();

        JobKey key = jobDetail.getKey();

        Trigger trigger =   TriggerBuilder.newTrigger()
                                                                                 .withIdentity(UUID.randomUUID().toString(), "TEST")
                                                                                 .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
                                                                                 .forJob(jobDetail.getKey())
                                                                                 .build();

        if (trigger != null) {

            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

}

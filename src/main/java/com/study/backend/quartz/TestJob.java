package com.study.backend.quartz;

import com.study.backend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
@Slf4j
public class TestJob extends QuartzJobBean {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Job is running");
        context.getJobDetail().getJobDataMap().forEach((k,v) -> {
            log.info("JobData: {} = {}", k,v);
        });

    }
}

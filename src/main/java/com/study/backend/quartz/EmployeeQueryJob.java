package com.study.backend.quartz;

import com.study.backend.enity.Employee;
import com.study.backend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;


@Slf4j
@DisallowConcurrentExecution
public class EmployeeQueryJob extends QuartzJobBean {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Job RUN");

        List<Employee> employeeList = this.employeeRepository.getEmployees();
        employeeList.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
    }
}

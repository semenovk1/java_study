package com.study.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.sql.SQLQuery;
import com.study.backend.dto.FilterDto;
import com.study.backend.dto.FilterFieldDto;
import com.study.backend.enity.Department;
import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.study.backend.patterns.factory.PrinterFactory;
import com.study.backend.repository.DepartmentRepository;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.repository.ManagerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppStart {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @PostConstruct
    public void Init() throws Exception {

        //        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        log.info("Test");

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = employeeRepository.getEmployees();
        employeeList.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
        //
        FilterDto filterEmployee = FilterDto.builder()
                                    .fields(
                                        List.of(FilterFieldDto.builder()
                                                              .name("salary")
                                                              .predicate("Gt")
                                                              .value(List.of("20000"))
                                                              .build()
                                        ))
                                    .build();

        FilterDto filterManager = FilterDto.builder()
                                    .fields(
                                        List.of(FilterFieldDto.builder()
                                                              .name("departmentId")
                                                              .predicate("Gt")
                                                              .value(List.of("1"))
                                                              .build()
                                        ))
                                    .build();


        long startTime = System.nanoTime();
        for(int i = 0; i < 300; i ++)
        {
            log.info(employeeRepository.dumpData(filterEmployee));
//            log.info(managerRepository.dumpData(filterManager));
        }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        log.info("Total time of dump: {} sec" ,totalTime / 1000000000);

        //

    }
}

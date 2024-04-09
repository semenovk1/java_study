package com.study.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.backend.enity.Employee;
import com.study.backend.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppStart {

    private final EmployeeRepository employeeRepository;

    @PostConstruct
    public void Init() {
        log.info("Test");
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = employeeRepository.getEmployees();
        employeeList.stream().map(Employee::toString).forEach(e -> log.info("Value: {}", e));
    }
}

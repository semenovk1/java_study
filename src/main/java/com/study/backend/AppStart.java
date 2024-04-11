package com.study.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.backend.enity.Department;
import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.study.backend.repository.DepartmentRepository;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.repository.ManagerRepository;
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
    private final ManagerRepository managerRepository;
    private final DepartmentRepository departmentRepository;

    @PostConstruct
    public void Init() {
        log.info("Test");
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = employeeRepository.getEmployees();
        employeeList.stream().map(Employee::toString).forEach(e -> log.info("Value: {}", e));

        List<Manager> managerList = managerRepository.getManagers();
        managerList.stream().map(Manager::toString).forEach(e -> log.info("Value: {}", e));

        List<Department> depList = departmentRepository.getDepartments();
        depList.stream().map(Department::toString).forEach(e -> log.info("Value: {}", e));

    }
}

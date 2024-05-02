package com.study.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.backend.dto.FilterDto;
import com.study.backend.dto.FilterFieldDto;
import com.study.backend.enity.Department;
import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.study.backend.repository.DepartmentRepository;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.repository.ManagerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppStart {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final DepartmentRepository departmentRepository;

    @PostConstruct
    public void Init() throws Exception{

//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        log.info("Test");
//        ObjectMapper mapper = new ObjectMapper();
//        List<Employee> employeeList = employeeRepository.getEmployees();
//        employeeList.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
//
//        List<Manager> managerList = managerRepository.getManagers();
//        managerList.stream().map(Manager::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
//
//        List<Department> depList = departmentRepository.getDepartments();
//        depList.stream().map(Department::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));



        //Get All With name Ivan
//        List<FilterFieldDto> fields =List.of(
//
//            FilterFieldDto.builder().name("employmentDate").predicate("Gt").value(List.of("2024-02-01 00:00")).build()
//        );
//        List<Employee> employeeList1 = employeeRepository.getEmployeesByFilter(FilterDto.builder().fields(fields).build());
//
//        employeeList1.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));

//        //Get All With Ivan Ivanoff
//        List<Employee> employeeList2 = employeeRepository.getEmployeesByFilter("Norman", "Mccormick", null ,null);
//        employeeList2.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
//
//        //Get All  Ivan Ivanoff and Salary > 50000
//        List<Employee> employeeList3 = employeeRepository.getEmployeesByFilter("Norman", null, 50000D ,1);
//        employeeList3.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
//
//        //Get All  Ivan Ivanoff and Salary > 50000
//        List<Employee> employeeList4 = employeeRepository.getEmployeesByFilter(null, null, 20000D ,1);
//        employeeList4.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));


        //employeeList.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));

    }
}

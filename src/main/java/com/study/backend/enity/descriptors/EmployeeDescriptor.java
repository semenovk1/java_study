package com.study.backend.enity.descriptors;

import com.study.backend.enity.Employee;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.study.demo.querydsl.entities.QEmployees.employees;
public class EmployeeDescriptor {
    public static final Map<String, FiledDescriptor<?>> fieldDescriptorMap = new HashMap<>() {
        {
            put(Employee.Fields.id, new FiledDescriptor<>(employees.idEmployee, Long.class));
            put(Employee.Fields.name, new FiledDescriptor<>(employees.dsName, String.class));
            put(Employee.Fields.lastname,new FiledDescriptor<>( employees.dsLastname, String.class));
            put(Employee.Fields.isActive, new FiledDescriptor<>(employees.flActive, Boolean.class));
            put(Employee.Fields.employmentDate, new FiledDescriptor<>(employees.dtEmployment, LocalDateTime.class));
            put(Employee.Fields.resignationDate, new FiledDescriptor<>(employees.dtResignation, LocalDateTime.class));
            put(Employee.Fields.salary, new FiledDescriptor<>(employees.mtSalary, Double.class));
        }};
}

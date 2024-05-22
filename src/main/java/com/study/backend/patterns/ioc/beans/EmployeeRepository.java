package com.study.backend.patterns.ioc.beans;

import com.study.backend.enity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private Employee getRandom(){
        int index = (int)Math.floor(Math.random()*1000);
        return Employee.builder().id((long)index).name(String.format("Employee_%d", index)).salary(10000 + Math.floor(Math.random()*100000)).isActive(true).build();

    }

    public List<Employee> getAllEmployee(){
        return DataGenerator.generate(100, this::getRandom);
    }
}

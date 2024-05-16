package com.study.backend.service;

import com.study.backend.enity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    public void addEmploee(Employee employee) throws Exception{

        if(employee.getSalary() < 0){
            throw  new Exception("AAAA");
        }

    }
    public void updateEmployee(Employee employee) throws Exception{
        if(employee.getSalary() < 0){
            throw  new Exception("AAAA");
        }
    }

    public void updateEmployeeWhenMoonIsShining(Employee employee) throws Exception{
        if(employee.getSalary() < 0){
            throw  new Exception("AAAA");
        }
    }
}

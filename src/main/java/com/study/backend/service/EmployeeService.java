package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;
import com.study.backend.enity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    ValidationService validationService;

    public void addEmploee(Employee employee) throws Exception{

        List<ValidationErrorDto> errors = validationService.validate(employee);

        if(errors != null &&  !errors.isEmpty()){
            errors.forEach(e -> log.info("VALIDATION ERROR: {}",e.toString()));
            throw new Exception("Failed");
        }
    }
    public void updateEmployee(Employee employee) throws Exception{
        List<ValidationErrorDto> errors = validationService.validate(employee);

        if(!errors.isEmpty()){
            errors.forEach(e -> log.info(e.toString()));
            throw new Exception("Failed");
        }
    }

    public void updateEmployeeWhenMoonIsShining(Employee employee) throws Exception{
        List<ValidationErrorDto> errors = validationService.validate(employee);

        if(!errors.isEmpty()){
            errors.forEach(e -> log.info(e.toString()));
            throw new Exception("Failed");
        }
    }
}

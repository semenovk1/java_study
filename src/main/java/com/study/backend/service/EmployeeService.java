package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;
import com.study.backend.enity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final ValidationSeriviceImpl validationSerivice;

    public void addEmployee(Employee e){

        List<ValidationErrorDto>  errors = validationSerivice.validate(e);

        if(errors.size() > 0){
            //has errors
            errors.stream().forEach(ee -> {
                log.info(e.toString());
            });

        }
    }

}

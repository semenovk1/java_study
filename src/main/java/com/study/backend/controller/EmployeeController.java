package com.study.backend.controller;

import com.study.backend.dto.FilterDto;
import com.study.backend.enity.Employee;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.service.AuthUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/employee", produces = APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "basicAuth")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(@AuthenticationPrincipal AuthUser user){

        return ResponseEntity.ok(employeeRepository.getEmployees());
    }

    @PostMapping
    public ResponseEntity getByFilter(@RequestBody FilterDto filterDto, @AuthenticationPrincipal AuthUser user){
        try {
            return ResponseEntity.ok(employeeRepository.getEmployeesByFilter(filterDto));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}

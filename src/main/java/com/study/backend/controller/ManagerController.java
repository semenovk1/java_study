package com.study.backend.controller;

import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.repository.ManagerRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/manager", produces = APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "basicAuth")
public class ManagerController {

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping
    public ResponseEntity<List<Manager>> getAll(){
        return ResponseEntity.ok(managerRepository.getManagers());
    }
}

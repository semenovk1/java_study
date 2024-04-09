package com.study.backend.controller;

import com.study.backend.enity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/api/employee", produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

}

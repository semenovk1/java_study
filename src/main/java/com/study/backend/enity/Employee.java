package com.study.backend.enity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// без доп. поля manager
//@Getter
//@Builder
//public class Employee {
//
//    private Long id;
//    private String name;
//    private String lastname;
//    private Double salary;
//    private Boolean isActive;
//    private Long managerId;
//    private LocalDateTime employmentDate;
//    private LocalDateTime resignationDate;
//}

@Getter
@Builder
@Setter
@ToString
public class Employee {

    private Long id;
    private String name;
    private String lastname;
    private Double salary;
    private Boolean isActive;
    private Long managerId;
    private LocalDateTime employmentDate;
    private LocalDateTime resignationDate;

    private String manager;
}

package com.study.backend.enity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

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
@FieldNameConstants
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

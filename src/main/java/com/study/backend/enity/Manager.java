package com.study.backend.enity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Builder
@ToString
@FieldNameConstants
public class Manager {

    private Long id;
    private String name;
    private String lastname;

    // department
    private Long departmentId;
    private String departmentName;
}

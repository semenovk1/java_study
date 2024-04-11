package com.study.backend.enity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Department {

    private Long id;
    private String name;

}
package com.study.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class ValidationErrorDto {
    private CheckType checkType;
    private List<String> properties;
}

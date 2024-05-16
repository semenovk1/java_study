package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;

import java.util.List;

public interface ValidationService {

    <T> List<ValidationErrorDto> validate(T entity, Class<?>... validationGroups);
}

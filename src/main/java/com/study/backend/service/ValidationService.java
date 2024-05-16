package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;
import net.sf.jsqlparser.util.validation.ValidationError;

import java.util.List;

public interface ValidationService {

    <T> List<ValidationErrorDto> validate(T entity, Class<?>... validationGroups);
}

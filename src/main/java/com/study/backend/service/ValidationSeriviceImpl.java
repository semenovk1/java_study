package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;
import com.study.backend.validation.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ValidationSeriviceImpl implements ValidationService {

    private final Validator validator;
    @Autowired
    public ValidationSeriviceImpl(Validator validator) {

        this.validator = validator;
    }
    @Override
    public <T> List<ValidationErrorDto> validate(T entity, Class<?>... validationGroups) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity, validationGroups);

        return ValidationUtils.convert(violations);
    }
}

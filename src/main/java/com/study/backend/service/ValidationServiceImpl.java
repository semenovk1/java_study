package com.study.backend.service;

import com.study.backend.dto.ValidationErrorDto;
import com.study.backend.validation.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Override
    public <T> List<ValidationErrorDto> validate(T entity, Class<?>... validationGroups) {
        Set<ConstraintViolation<T>> validationInfo = validator.validate(entity, validationGroups);

        return ValidationUtils.convert(validationInfo);
    }
}

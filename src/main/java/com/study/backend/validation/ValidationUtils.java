package com.study.backend.validation;

import com.study.backend.dto.ValidationErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.metadata.ConstraintDescriptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.study.backend.validation.CheckType.*;

public class ValidationUtils {

    public static <T> List<ValidationErrorDto> convert(Set<ConstraintViolation<T>> constraintViolations) {

        List<ValidationErrorDto> validationErrors = null;

        if (constraintViolations != null && constraintViolations.size() > 0) {

            validationErrors = new ArrayList<>();

            for (ConstraintViolation<?> violation : constraintViolations) {
                String propName = "unknown";
                CheckType type = Unknown;

                Path path = violation.getPropertyPath();

                if (path != null) {
                    propName = path.toString();
                }

                ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor();
                if (descriptor != null) {
                    Annotation annotation = descriptor.getAnnotation();

                    if (annotation != null) {
                        type = convert(annotation.annotationType());
                    }
                }

                validationErrors.add(ValidationErrorDto.builder()
                                                    .checkType(type)
                                                    .properties(List.of(propName))
                                                    .build());
            }
        }

        return validationErrors;
    }

    public static <T extends Annotation> CheckType convert(Class<T> annotationType) {

        if (annotationType == null) {
            return Unknown;
        }

        if (annotationType.isAssignableFrom(jakarta.validation.constraints.NotNull.class)
            || annotationType.isAssignableFrom(jakarta.validation.constraints.NotEmpty.class)) {

            return NotEmpty;
        }
        if (annotationType.isAssignableFrom(Size.class)
            || annotationType.isAssignableFrom(Digits.class)
            || annotationType.isAssignableFrom(Min.class)) {

            return OutOfBounds;
        }
        if (annotationType.isAssignableFrom(jakarta.validation.constraints.Null.class)) {

            return Empty;
        }

        return Unknown;
    }
}

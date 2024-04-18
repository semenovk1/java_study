package com.study.backend.enity.descriptors;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FiledDescriptor<T extends Comparable<?>> {
    private ComparableExpressionBase<T> tableField;
    private Class<T> filedType;

}

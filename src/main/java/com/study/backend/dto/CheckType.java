package com.study.backend.dto;

public enum CheckType {
    NotEmpty,
    Unique,
    OutOfBounds,
    ReferenceNotFound,
    EqualProperties,
    Empty,
    Unknown,
    NotSameSrcDst,
    InvalidExpression
}

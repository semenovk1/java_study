package com.study.backend.patterns;

import com.querydsl.sql.SQLQuery;

public interface ObjectDumper<T> {
    String dumpData(SQLQuery<T> query) throws  Exception;

}

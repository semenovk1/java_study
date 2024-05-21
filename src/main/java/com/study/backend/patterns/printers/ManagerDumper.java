package com.study.backend.patterns.printers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.querydsl.sql.SQLQuery;
import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.study.backend.patterns.ObjectDumper;

import java.util.List;

public class ManagerDumper implements ObjectDumper<Manager> {

    @Override
    public String dumpData(SQLQuery<Manager> query) throws  Exception {

        List<Manager> data = query.fetch();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(data);
    }
}

package com.study.backend.patterns.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.querydsl.sql.SQLQuery;
import com.study.backend.enity.Employee;
import com.study.backend.patterns.ObjectDumper;

import java.util.List;

public class EmployeeCacheProxy implements ObjectDumper<Employee> {

    private static String queryCache = null;

    private static String cachedData = null;

    //private ObjectMapper objectMapper = new ObjectMapper()


    private boolean isQueryCached(String query) throws Exception {
        if(queryCache == null){
            return false;
        }
        return query.equals(queryCache);

    }

    private void saveCache(String query, String data) throws Exception{
        cachedData = data;
        queryCache = query;
    }

    @Override
    public String dumpData(SQLQuery<Employee> query) throws  Exception {
        String queryString = query.toString();
        if(isQueryCached(queryString) && cachedData != null){
            return cachedData;
        }

        List<Employee> data = query.fetch();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String res = objectMapper.writeValueAsString(data);

        saveCache(queryString, res);

        return res;


    }
}

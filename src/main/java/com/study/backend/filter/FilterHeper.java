package com.study.backend.filter;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.study.backend.dto.FilterDto;
import com.study.backend.dto.FilterFieldDto;
import com.study.backend.enity.Employee;
import com.study.backend.enity.descriptors.EmployeeDescriptor;
import com.study.backend.enity.descriptors.FiledDescriptor;

import java.util.List;
import java.util.Map;

import static com.study.demo.querydsl.entities.QEmployees.employees;

public class FilterHeper {

    static private < T extends Comparable<?>> BooleanExpression getExpressionWithPredicate(FiledDescriptor<T> fieldEx, String predicate, String value) throws Exception{
        switch (predicate){
            case "Eq":
                return Expressions.booleanOperation(Ops.EQ, fieldEx.getTableField(), Expressions.constant(value));
            case "Gt":
                return Expressions.booleanOperation(Ops.GT, fieldEx.getTableField(),  Expressions.constant(value));
            case "Lt":
                return Expressions.booleanOperation(Ops.LT, fieldEx.getTableField(),  Expressions.constant(value));
        }

        return fieldEx.getTableField().isNotNull();
    }
    static  private <T extends Comparable<?>> BooleanExpression getExpression(FiledDescriptor<T> fieldEx, String predicate, List<String> values) throws Exception{
        BooleanExpression baseExpr = null;
        for (String v: values) {
            baseExpr = baseExpr != null ? baseExpr.or(getExpressionWithPredicate(fieldEx, predicate, v)) : getExpressionWithPredicate(fieldEx, predicate, v);
        }

        return baseExpr;
    }


    static public <T> List<T> getByFilter(SQLQuery<T> q , Map<String, FiledDescriptor<?>> fieldDescriptorMap,  FilterDto filter) throws Exception {

        return makeFilterQuery(q, fieldDescriptorMap, filter).fetch();
    }


    static public <T> SQLQuery<T> makeFilterQuery(SQLQuery<T> q , Map<String, FiledDescriptor<?>> fieldDescriptorMap,  FilterDto filter) throws Exception {

        for (FilterFieldDto field : filter.getFields()) {
            q.where(getExpression(fieldDescriptorMap.get(field.getName()), field.getPredicate(), field.getValue()));
        }

        return q;
    }
}

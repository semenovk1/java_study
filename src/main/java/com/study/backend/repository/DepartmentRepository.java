package com.study.backend.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;
import com.querydsl.sql.SQLQueryFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.study.backend.enity.Department;

import static com.study.demo.querydsl.entities.QDepartments.departments;


@Transactional
@Repository
public class DepartmentRepository {

    private final SQLQueryFactory queryFactory;
    private final MappingProjection<Department> projection;

    public DepartmentRepository(SQLQueryFactory queryFactory) {

        this.queryFactory = queryFactory;

        // Слайд 9 before department

                projection = new MappingProjection<>(Department.class, departments.all()) {

                    @Override
                    protected Department map(Tuple tuple) {

                        return Department.builder()
                                      .id(tuple.get(departments.idDepartment))
                                      .name(tuple.get(departments.dsName))
                                      .build();
                    }
                };


    }

    public List<Department> getDepartments() {

                return queryFactory.from(departments).select(projection).fetch();


    }

}

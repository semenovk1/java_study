package com.study.backend.repository;

import com.querydsl.sql.SQLQuery;
import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;
import com.querydsl.core.types.Path;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.study.demo.querydsl.entities.QDepartments.departments;
import static com.study.demo.querydsl.entities.QEmployees.employees;
import static com.study.demo.querydsl.entities.QManagers.managers;


@Transactional
@Repository
public class ManagerRepository {

    private final SQLQueryFactory queryFactory;
    private final MappingProjection<Manager> projection;

    public ManagerRepository(SQLQueryFactory queryFactory) {

        this.queryFactory = queryFactory;

        // Слайд 9 before department

//                projection = new MappingProjection<>(Manager.class, managers.all()) {
//
//                    @Override
//                    protected Manager map(Tuple tuple) {
//
//                        return Manager.builder()
//                                      .id(tuple.get(managers.idManager))
//                                      .name(tuple.get(managers.dsName))
//                                      .lastname(tuple.get(managers.dsLastname))
//                                .departmentId(tuple.get(managers.idDepartment))
//                                      .build();
//                    }
//                };

        // Слайд 12 department
        projection = new MappingProjection<>(Manager.class, Stream.concat(Stream.of(managers.all()),
                                                                          Stream.of(departments.idDepartment, departments.dsName))
                                                                  .toArray(Path[]::new)) {

            @Override
            protected Manager map(Tuple tuple) {

                return Manager.builder()
                              .id(tuple.get(managers.idManager))
                              .name(tuple.get(managers.dsName))
                              .lastname(tuple.get(managers.dsLastname))
                              .departmentId(tuple.get(departments.idDepartment))
                              .departmentName(tuple.get(departments.dsName))
                              .build();
            }
        };
    }
    //salaryOp < 0 - Less
    //salaryOp = 0 - Eq
    //salaryOp > 0 - Gr
    public List<Manager> getEmployeesByFilter(String name, String lastName) {
        //name AND lastName AND salary (Less, Grater, Eql)
        SQLQuery<Manager> q = queryFactory.query().select(projection).from(managers).innerJoin(departments)
                                          .on(managers.idDepartment.eq(departments.idDepartment));

        if (name != null) {
            q.where(managers.dsName.eq(name));
        }

        if (lastName != null) {
            q.where(managers.dsLastname.eq(lastName));
        }

        return q.fetch();
    }

//    public List<Manager> getEmployeesByFilterAlt(List<Objects> fieldNamesAndValues) {
//        //name AND lastName AND salary (Less, Grater, Eql)
//        SQLQuery<Manager> q = queryFactory.query().select(projection).from(managers).innerJoin(departments)
//                                          .on(managers.idDepartment.eq(departments.idDepartment));
//
//        fieldNames.stream().forEach((fn -> {
//            q.where(getFieldByName(fn).eq(fieldValue));
//            q.where(employees.dsName.eq("name"));
//        }));
//
//        return q.fetch();
//    }
//
//    public List<Manager> getEmployeesByFilterAlt2(List<Objects> fieldNamesAndValues) {
//        //name AND lastName AND salary (Less, Grater, Eql)
//       return FilterHelper<Manager>.filter(fieldNamesAndValues);
//    }

    public List<Manager> getManagers() {

        // Слайд 9 before department
//                return queryFactory.from(managers).select(projection).fetch();

//         Слайд 12 department
        return queryFactory.from(managers)
                           .innerJoin(departments)
                           .on(managers.idDepartment.eq(departments.idDepartment))
                           .select(projection).fetch();
    }

    public List<Manager> getByLastname(String lastname) {

        // Слайд 9 before department
//        return queryFactory.from(managers).where(managers.dsLastname.eq(lastname)).select(projection).fetch();

        // Слайд 12 department
        return queryFactory.from(managers)
                           .innerJoin(departments)
                           .on(managers.idDepartment.eq(departments.idDepartment))
                           .where(managers.dsLastname.eq(lastname))
                           .select(projection).fetch();
    }
}

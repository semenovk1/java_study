package com.study.backend.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;
import com.querydsl.core.types.Path;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.study.backend.enity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static com.study.demo.querydsl.entities.QEmployees.employees;
import static com.study.demo.querydsl.entities.QManagers.managers;


@Transactional
@Repository
public class EmployeeRepository {

    private final SQLQueryFactory queryFactory;
    private final MappingProjection<Employee> projection;

    public EmployeeRepository(SQLQueryFactory queryFactory) {

        this.queryFactory = queryFactory;

//         проекция без доп. поля manager
//        projection = new MappingProjection<>(Employee.class, employees.all()) {
//
//            @Override
//            protected Employee map(Tuple tuple) {
//
//                return Employee.builder()
//                               .id(tuple.get(employees.idEmployee))
//                               .name(tuple.get(employees.dsName))
//                               .lastname(tuple.get(employees.dsLastname))
//                               .salary(tuple.get(employees.mtSalary))
//                               .isActive(tuple.get(employees.flActive))
//                               .managerId(tuple.get(employees.idManager))
//                               .employmentDate(tuple.get(employees.dtEmployment))
//                               .resignationDate(tuple.get(employees.dtResignation))
//                               .build();
//            }
//        };

        projection = new MappingProjection<Employee>(Employee.class, Stream.concat(Stream.of(employees.all()),
                                                                                   Stream.of(managers.dsName, managers.dsLastname))
                                                                           .toArray(Path[]::new)) {

            @Override
            protected Employee map(Tuple tuple) {

                return Employee.builder()
                               .id(tuple.get(employees.idEmployee))
                               .name(tuple.get(employees.dsName))
                               .lastname(tuple.get(employees.dsLastname))
                               .salary(tuple.get(employees.mtSalary))
                               .isActive(tuple.get(employees.flActive))
                               .managerId(tuple.get(employees.idManager))
                               .employmentDate(tuple.get(employees.dtEmployment))
                               .resignationDate(tuple.get(employees.dtResignation))
                               .manager(String.join(" ", tuple.get(managers.dsName), tuple.get(managers.dsLastname)))
                               .build();
            }
        };
    }

    //salaryOp < 0 - Less
    //salaryOp = 0 - Eq
    //salaryOp > 0 - Gr
    public List<Employee> getEmployeesByFilter(String name, String lastName, Double salary, Integer salaryOp) {
        //name AND lastName AND salary (Less, Grater, Eql)
        SQLQuery<Employee> q = queryFactory.query().select(projection).from(employees).innerJoin(managers)
                                           .on(employees.idManager.eq(managers.idManager));

        if(name != null){
            q.where(employees.dsName.eq(name));
        }

        if(lastName != null){
            q.where(employees.dsLastname.eq(lastName));
        }

        if(salary != null){
            if(salaryOp < 0)
                q.where(employees.mtSalary.lt(salary));
            else if(salaryOp > 0)
                q.where(employees.mtSalary.gt(salary));
            else
                q.where(employees.mtSalary.eq(salary));
        }


        return q.fetch();
    };

    public List<Employee> getEmployees() {

         //запрос без поля manager
//        return queryFactory.from(employees)
//                           .select(projection).fetch();

        return queryFactory.from(employees)
                           .innerJoin(managers)
                           .on(employees.idManager.eq(managers.idManager))
                           .select(projection).fetch();
    }

        public List<Employee> getByName(String name) {

        // запрос без поля manager
//        return queryFactory.from(employees)
//                           .where(employees.dsName.eq(name))
//                           .select(projection)
//                           .fetch();

        return queryFactory.from(employees)
                           .innerJoin(managers)
                           .on(employees.idManager.eq(managers.idManager))
                           .where(employees.dsName.eq(name))
                           .select(projection)
                           .fetch();
    }
}

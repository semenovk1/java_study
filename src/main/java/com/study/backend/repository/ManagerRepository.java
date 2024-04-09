//package com.study.backend.repository;
//
//import com.nlabteam.demo.querydsl.entities.Manager;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.MappingProjection;
//import com.querydsl.core.types.Path;
//import com.querydsl.sql.SQLQueryFactory;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//import static com.nlabteam.demo.querydsl.entities.QDepartments.departments;
//import static com.nlabteam.demo.querydsl.entities.QManagers.managers;
//
//@Transactional
//@Repository
//public class ManagerRepository {
//
//    private final SQLQueryFactory queryFactory;
//    private final MappingProjection<Manager> projection;
//
//    public ManagerRepository(SQLQueryFactory queryFactory) {
//
//        this.queryFactory = queryFactory;
//
//        // Слайд 9 before department
//
//        //        projection = new MappingProjection<>(Manager.class, managers.all()) {
//        //
//        //            @Override
//        //            protected Manager map(Tuple tuple) {
//        //
//        //                return Manager.builder()
//        //                              .id(tuple.get(managers.idManager))
//        //                              .name(tuple.get(managers.dsName))
//        //                              .lastname(tuple.get(managers.dsLastname))
//        //                              .build();
//        //            }
//        //        };
//
//        // Слайд 12 department
//        projection = new MappingProjection<>(Manager.class, Stream.concat(Stream.of(managers.all()),
//                                                                          Stream.of(departments.idDepartment, departments.dsName))
//                                                                  .toArray(Path[]::new)) {
//
//            @Override
//            protected Manager map(Tuple tuple) {
//
//                return Manager.builder()
//                              .id(tuple.get(managers.idManager))
//                              .name(tuple.get(managers.dsName))
//                              .lastname(tuple.get(managers.dsLastname))
//                              .departmentId(tuple.get(departments.idDepartment))
//                              .departmentName(tuple.get(departments.dsName))
//                              .build();
//            }
//        };
//    }
//
//    public List<Manager> getManagers() {
//
//        // Слайд 9 before department
//        //        return queryFactory.from(managers).select(projection).fetch();
//
//        // Слайд 12 department
//        return queryFactory.from(managers)
//                           .innerJoin(departments)
//                           .on(managers.idDepartment.eq(departments.idDepartment))
//                           .select(projection).fetch();
//    }
//
//    public List<Manager> getByLastname(String lastname) {
//
//        // Слайд 9 before department
////        return queryFactory.from(managers).where(managers.dsLastname.eq(lastname)).select(projection).fetch();
//
//        // Слайд 12 department
//        return queryFactory.from(managers)
//                           .innerJoin(departments)
//                           .on(managers.idDepartment.eq(departments.idDepartment))
//                           .where(managers.dsLastname.eq(lastname))
//                           .select(projection).fetch();
//    }
//}

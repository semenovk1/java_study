package com.study.backend.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.study.backend.enity.Manager;
import com.study.backend.enity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static com.study.demo.querydsl.entities.QDepartments.departments;
import static com.study.demo.querydsl.entities.QManagers.managers;
import static com.study.demo.querydsl.entities.QUsers.users;
//import static com.study.demo.querydsl.entities.QManagers.managers;

@Transactional
@Repository
public class UsersRepository {

    private final SQLQueryFactory queryFactory;
    private final MappingProjection<Users> projection;
    public UsersRepository(SQLQueryFactory queryFactory) {

        this.queryFactory = queryFactory;


        projection = new MappingProjection<>(Users.class, users.all())
                 {

            @Override
            protected Users map(Tuple tuple) {

                return Users.builder()
                        .id(tuple.get(users.idUser))
                        .ds_name(tuple.get(users.dsUser))
                        .password(tuple.get(users.password))
                        .build();
            }
        };
    }

    public List<Users> getUserbylogin(String login) {
        //name AND lastName AND salary (Less, Grater, Eql)
        SQLQuery<Users> q = queryFactory.query().select(projection).from(users).where(users.dsUser.eq(login));


        return q.fetch();
    }
}

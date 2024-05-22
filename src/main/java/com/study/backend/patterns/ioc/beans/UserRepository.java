package com.study.backend.patterns.ioc.beans;

import com.study.backend.enity.Manager;
import com.study.backend.enity.Users;

import java.util.List;

public class UserRepository {

    private Users getRandom(){
        int index = (int)Math.floor(Math.random()*1000);
        String prefix = Math.random() >  0.5 ? "Manager" : "Employee";
        return Users.builder().id((long)index).ds_name(String.format("%s_%d",prefix, index)).build();

    }

    public List<Users> getAllUsers(){
        return DataGenerator.generate(100, this::getRandom);
    }
}

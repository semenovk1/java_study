package com.study.backend.patterns.ioc.beans;

import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;

import java.util.List;

public class ManagerRepository {

    private Manager getRandom(){
        int index = (int)Math.floor(Math.random()*1000);
        return Manager.builder().id((long)index).name(String.format("Manager_%d", index)).build();

    }

    public List<Manager> getAllManagers(){
        return DataGenerator.generate(100, this::getRandom);
    }
}

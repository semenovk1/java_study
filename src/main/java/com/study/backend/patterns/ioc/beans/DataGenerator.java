package com.study.backend.patterns.ioc.beans;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator<T> {
    public interface GenFunc<T> {
        T get();
    }
    static public <T> List<T> generate(int count, GenFunc<T> gf){
        List<T> list = new ArrayList<T>();

        for(int i=0; i < count; i ++){
            list.add(gf.get());
        }

        return  list;
    }

}

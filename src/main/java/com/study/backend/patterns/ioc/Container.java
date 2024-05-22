package com.study.backend.patterns.ioc;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class Container {

    Map<String, InjectableCtor> registered =  new HashMap<>();

    Map<String, Injectable> context = new HashMap<>();

    public void register(String id, InjectableCtor ctor) {
        registered.put(id, ctor);
    }

    public void init() throws Exception{
        //create instances
        registered.forEach((key, value) -> {
            log.info("Instantiating type: {}", key);
            Injectable injectable = value.create();
            context.put(key, injectable);
        });

        //autowire
        autowire();
    }

    public <T> T getInstance(String key){ //serivce locator
        Injectable target = context.get(key);
        if(target == null){
            throw new RuntimeException(String.format("Not found Bean %s", key));
        }

        return (T) target;
    }

    private void autowire() {
        context.forEach((key, value) -> {
            Map<String, Injector> f = value.getFieldsToInject();

            //field type, injector
            f.forEach((fieldName,fieldInjector) -> {
                Injectable target = getInstance(fieldName);
                log.info("Autowire dependency: Inject {} to {}", fieldName, key);
                fieldInjector.inject(target);
            });
        });

    }


}

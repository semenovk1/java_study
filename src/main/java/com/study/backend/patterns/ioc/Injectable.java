package com.study.backend.patterns.ioc;

import lombok.Getter;

import java.util.Map;


@Getter
public class Injectable {

    private Map<String, Injector> fieldsToInject;

   protected void autowire(Map<String, Injector> fieldsToInject){
       this.fieldsToInject = fieldsToInject;
   }

}

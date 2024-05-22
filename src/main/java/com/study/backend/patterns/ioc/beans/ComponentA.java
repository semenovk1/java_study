package com.study.backend.patterns.ioc.beans;

import com.study.backend.patterns.ioc.Injectable;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class ComponentA extends Injectable {

    private ComponentB componentB;

    public ComponentA(){
        super();
        autowire(new HashMap<>() {{
            put(ComponentB.class.getName(), (d) -> componentB = (ComponentB) d);
        }});

    }


    public void testComponentB(){
        componentB.ping();
    }

    public void ping(){
        log.info("Component A PING");
    }
}

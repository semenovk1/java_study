package com.study.backend.patterns.ioc.beans;

import com.study.backend.patterns.ioc.Injectable;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class ComponentB extends Injectable {

    private ComponentA componentA;

    public ComponentB(){
        super();
        autowire(new HashMap<>() {{
            put(ComponentA.class.getName(), (d) -> componentA = (ComponentA) d);
        }});

    }

    public void testComponentA(){
        componentA.ping();
    }

    public void ping(){
        log.info("Component B PING");
    }
}

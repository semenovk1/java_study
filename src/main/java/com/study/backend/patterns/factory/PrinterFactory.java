package com.study.backend.patterns.factory;

import com.study.backend.patterns.ObjectDumper;
import com.study.backend.patterns.printers.ManagerDumper;
import com.study.backend.patterns.printers.EmployeeDumper;
import com.study.backend.patterns.proxy.EmployeeCacheProxy;


public class PrinterFactory {

    public static <T> ObjectDumper<T> getDumperFor(Class<T> clazz){

        switch (clazz.getName()){
            case "com.study.backend.enity.Employee":
                return (ObjectDumper<T>) new EmployeeCacheProxy(); //point of change

            case "com.study.backend.enity.Manager":
                return (ObjectDumper<T>) new ManagerDumper();

            default:
                throw new RuntimeException("Unknown Entity to Log");
        }
    }
}

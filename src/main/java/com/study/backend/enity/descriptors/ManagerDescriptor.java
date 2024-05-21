package com.study.backend.enity.descriptors;

import com.study.backend.enity.Manager;

import java.util.HashMap;
import java.util.Map;

import static com.study.demo.querydsl.entities.QManagers.managers;

public class ManagerDescriptor {
    public static final Map<String, FiledDescriptor<?>> fieldDescriptorMap = new HashMap<>() {
        {
            put(Manager.Fields.id, new FiledDescriptor<>(managers.idManager, Long.class));
            put(Manager.Fields.name, new FiledDescriptor<>(managers.dsName, String.class));
            put(Manager.Fields.lastname,new FiledDescriptor<>( managers.dsLastname, String.class));
            put(Manager.Fields.departmentId,new FiledDescriptor<>( managers.idDepartment, Long.class));
        }};
}

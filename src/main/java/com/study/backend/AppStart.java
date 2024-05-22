package com.study.backend;

import com.study.backend.patterns.ioc.beans.ComponentA;
import com.study.backend.patterns.ioc.beans.ComponentB;
import com.study.backend.patterns.ioc.Container;
import com.study.backend.repository.EmployeeRepository;
import com.study.backend.repository.ManagerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppStart {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @PostConstruct
    public void Init() throws Exception {

        //        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        log.info("Test");

//        ObjectMapper mapper = new ObjectMapper();
//        List<Employee> employeeList = employeeRepository.getEmployees();
//        employeeList.stream().map(Employee::toString).forEach(e -> log.info("\033[1;32m Value:\033[0m \033[0;36m{}\033[0m", e));
//        //
//        FilterDto filterEmployee = FilterDto.builder()
//                                    .fields(
//                                        List.of(FilterFieldDto.builder()
//                                                              .name("salary")
//                                                              .predicate("Gt")
//                                                              .value(List.of("20000"))
//                                                              .build()
//                                        ))
//                                    .build();
//
//        FilterDto filterManager = FilterDto.builder()
//                                    .fields(
//                                        List.of(FilterFieldDto.builder()
//                                                              .name("departmentId")
//                                                              .predicate("Gt")
//                                                              .value(List.of("1"))
//                                                              .build()
//                                        ))
//                                    .build();
//
//
//        long startTime = System.nanoTime();
//        for(int i = 0; i < 300; i ++)
//        {
//            log.info(employeeRepository.dumpData(filterEmployee));
////            log.info(managerRepository.dumpData(filterManager));
//        }
//
//        long endTime   = System.nanoTime();
//        long totalTime = endTime - startTime;
//        log.info("Total time of dump: {} sec" ,totalTime / 1000000000);

        //Test IoC
        Container container = new Container();

        container.register(ComponentA.class.getName(), ComponentA::new);
        container.register(ComponentB.class.getName(), ComponentB::new);

        container.init();

        ComponentA a = container.getInstance(ComponentA.class.getName());
        a.testComponentB();


        ComponentB b = container.getInstance(ComponentB.class.getName());

        b.testComponentA();


    }
}

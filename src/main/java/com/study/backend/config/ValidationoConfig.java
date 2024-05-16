package com.study.backend.config;

import com.study.backend.enity.Employee;
import com.study.backend.enity.Manager;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.MinDef;
import org.hibernate.validator.cfg.defs.NotNullDef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationoConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {

        return new LocalValidatorFactoryBean() {

            @Override
            protected void postProcessConfiguration(jakarta.validation.Configuration<?> configuration) {

                if (configuration instanceof HibernateValidatorConfiguration hibernateValidatorConfiguration) {
                    var constraintMapping = hibernateValidatorConfiguration.createConstraintMapping();

                    mapConstraints(constraintMapping);
                    hibernateValidatorConfiguration.addMapping(constraintMapping);
                }
            }
        };
    }

    public static void mapConstraints(ConstraintMapping constraintMapping) {


        constraintMapping
            .type(Employee.class)
            .field(Employee.Fields.name)
            .constraint(new NotNullDef())
            .field(Employee.Fields.salary)
            .constraint(new NotNullDef())
            .constraint(new MinDef().value(0L))

            .type(Manager.class)
            .field(Manager.Fields.name)
            .constraint(new NotNullDef())

        ;

    }
}

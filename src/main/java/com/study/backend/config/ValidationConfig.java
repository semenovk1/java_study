package com.study.backend.config;

import com.study.backend.enity.Employee;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

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
                .constraint(new NotNullDef());

        }
}

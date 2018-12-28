package com.desolatetimelines.utils.springboot.data;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


public class DataSourceConfigUtils {
    /**
     * Creates a Hibernate properties map with the values specified in the environment properties with the given prefix. Also sets the
     * {@link org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy SpringPhysicalNamingStrategy} as the naming strategy to
     * account for changes brought by Hibernate 5.
     */
    public static Map<String, String> hibernateProperties(Environment env, String dsPrefix) {
        Map<String, String> hibernateProperties = new HashMap<>();

        hibernateProperties.put("hibernate.dialect", env.getProperty(dsPrefix + ".dialect"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty(dsPrefix + ".show_sql"));

        hibernateProperties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

        return hibernateProperties;
    }

    /**
     * Builds an EMF using the beans with the given names, provided the referenced beans can be found in the referenced application context.
     */
    public static LocalContainerEntityManagerFactoryBean createEMF(ApplicationContext ctx, String dsName, String dsPrefix, String domainPkg, String puName) {
        EntityManagerFactoryBuilder builder = (EntityManagerFactoryBuilder) ctx.getBean(EntityManagerFactoryBuilder.class);

        return builder
                    .dataSource((DataSource) ctx.getBean(dsName))
                    .properties(hibernateProperties(ctx.getEnvironment(), dsPrefix))
                    .packages(domainPkg)
                    .persistenceUnit(puName)
              .build();
    }
}

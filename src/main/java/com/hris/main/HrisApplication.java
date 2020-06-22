package com.hris.main;

import com.hris.main.repository.generic.impl.systemRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {HypermediaAutoConfiguration.class,
        RepositoryRestMvcAutoConfiguration.class})
@EnableJpaRepositories(repositoryBaseClass = systemRepositoryImpl.class)
public class HrisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrisApplication.class, args);
    }

}

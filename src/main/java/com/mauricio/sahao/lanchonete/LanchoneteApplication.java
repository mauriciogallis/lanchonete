package com.mauricio.sahao.lanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.mauricio.sahao.lanchonete.dao"})
public class LanchoneteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteApplication.class, args);
    }

}

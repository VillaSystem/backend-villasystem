package com.example.backendvillasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendVillasystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendVillasystemApplication.class, args);
    }

}

package com.chalchal.chalchalserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChalChalServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChalChalServerApplication.class, args);
    }

}

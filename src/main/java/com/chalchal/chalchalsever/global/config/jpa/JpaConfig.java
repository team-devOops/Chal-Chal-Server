package com.chalchal.chalchalsever.global.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.chalchal.chalchalsever")
public class JpaConfig {
}

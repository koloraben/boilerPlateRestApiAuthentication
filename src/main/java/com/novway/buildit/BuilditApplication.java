package com.novway.buildit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BuilditApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuilditApplication.class, args);
	}
}

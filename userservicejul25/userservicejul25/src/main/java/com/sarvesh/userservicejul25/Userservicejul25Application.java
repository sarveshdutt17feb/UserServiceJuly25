package com.sarvesh.userservicejul25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Userservicejul25Application {

	public static void main(String[] args) {
		SpringApplication.run(Userservicejul25Application.class, args);
	}

}

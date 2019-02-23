package com.omnicuris.cme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmeApplication.class, args);
	}

}

package com.accenture.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories("com.accenture")
@ComponentScan("com.accenture")
@EnableAsync
public class AccentureImsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccentureImsApplication.class, args);
	}

}

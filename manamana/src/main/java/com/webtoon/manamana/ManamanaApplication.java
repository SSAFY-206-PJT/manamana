package com.webtoon.manamana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ManamanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManamanaApplication.class, args);
	}

}

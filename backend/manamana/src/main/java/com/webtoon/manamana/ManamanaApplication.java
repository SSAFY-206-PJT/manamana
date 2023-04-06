package com.webtoon.manamana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class ManamanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManamanaApplication.class, args);
	}

}

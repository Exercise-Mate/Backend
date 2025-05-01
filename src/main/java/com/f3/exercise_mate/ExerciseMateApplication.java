package com.f3.exercise_mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExerciseMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseMateApplication.class, args);
	}

}

package com.projectsw.projectsw;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectswApplication {

	public static void main(String[] args) {
		// Load environment variables from .env file for Spring
		Dotenv.configure().systemProperties().load();

		SpringApplication.run(ProjectswApplication.class, args);
	}

}

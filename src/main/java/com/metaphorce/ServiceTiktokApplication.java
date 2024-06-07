package com.metaphorce;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ServiceTiktokApplication {

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {

		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		// Run the Spring Boot application
		SpringApplication.run(ServiceTiktokApplication.class, args);

	}
}

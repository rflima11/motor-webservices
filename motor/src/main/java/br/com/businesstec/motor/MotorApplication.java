package br.com.businesstec.motor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MotorApplication {// extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MotorApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);

	}

}

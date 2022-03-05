package br.com.businesstec.motor;

import br.com.businesstec.motor.service.FluxoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MotorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorApplication.class, args);
	}

	@Bean
	CommandLineRunner lookUp(FluxoService fluxoService) {
		var lista = fluxoService.recuperarFluxosPeloIdCliente(1L);
		return args -> System.out.println(lista);
	}

	@Bean
	ObjectMapper objectMapper() {
		var objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}
}

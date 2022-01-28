package com.dh.digitalCar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DigitalCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalCarApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public ObjectMapper objectMapper() {
//		return new ObjectMapper();
//	}

	@Bean
	@Primary
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

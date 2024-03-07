package com.test.sentra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:sentra.properties")
public class SentraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentraApplication.class, args);
	}

}

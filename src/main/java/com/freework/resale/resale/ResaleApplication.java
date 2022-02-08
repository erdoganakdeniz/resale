package com.freework.resale.resale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.freework.resale.user"})
public class ResaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResaleApplication.class, args);
	}

}

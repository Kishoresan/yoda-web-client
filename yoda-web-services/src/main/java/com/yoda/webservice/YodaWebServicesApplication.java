package com.yoda.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yoda.webservice")
public class YodaWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(YodaWebServicesApplication.class, args);
	}

}

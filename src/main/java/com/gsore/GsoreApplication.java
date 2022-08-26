package com.gsore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GsoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsoreApplication.class, args);
	}

}

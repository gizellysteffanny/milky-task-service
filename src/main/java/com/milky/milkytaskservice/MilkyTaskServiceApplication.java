package com.milky.milkytaskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MilkyTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkyTaskServiceApplication.class, args);
	}

}

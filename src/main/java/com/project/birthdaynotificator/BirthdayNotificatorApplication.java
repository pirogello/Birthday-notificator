package com.project.birthdaynotificator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(".env")
public class BirthdayNotificatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(BirthdayNotificatorApplication.class, args);
	}

}

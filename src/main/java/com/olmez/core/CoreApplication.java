package com.olmez.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.core.model.User;
import com.olmez.core.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class CoreApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User appUser = userRepository.getAppUser();
		if (appUser == null) {
			log.info("Failed to connect to database! * * *");
		} else {
			log.info("*The database connection is successful! * * *");
			log.info("**Core application has started! * * *");
		}
		log.info("***App user: {}", appUser);
	}

}

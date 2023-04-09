package com.olmez.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.core.model.User;
import com.olmez.core.repositories.UserRepository;

@SpringBootApplication
public class CoreApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	public CommandLineRunner loadData(UserRepository userRepository) {
		return args -> {
			log.info("Loading data");
			log.info("Checking for Users from Database");
			List<User> users = userRepository.findAll();
			log.info("Checked users (size:{}) from Database, yeah!", users.size());
			log.info("Core Application is running!");
		};
	}
}

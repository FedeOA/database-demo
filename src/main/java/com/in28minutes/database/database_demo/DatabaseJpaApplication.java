package com.in28minutes.database.database_demo;

import com.in28minutes.database.database_demo.entity.Person;
import com.in28minutes.database.database_demo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class DatabaseJpaApplication implements CommandLineRunner {

	private final PersonJpaRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DatabaseJpaApplication(PersonJpaRepository repository) {
		this.repository = repository;
	}

    public static void main(String[] args) {
		SpringApplication.run(DatabaseJpaApplication.class, args);
	}

	@Override
	public void run(String... args){
		logger.info("All users -> {}", repository.findAll());
		logger.info("Person with id 10001 -> {}",repository.findById(10001));
		logger.info("Inserting 10004 -> {}",repository.update(new Person("Jose","Cordoba",new Date())));
		repository.deleteById(10002);
	}
}

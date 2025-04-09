package com.in28minutes.database.database_demo;

import com.in28minutes.database.database_demo.entity.Person;
import com.in28minutes.database.database_demo.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Date;


//@SpringBootApplication
public class DatabaseJdbcApplication implements CommandLineRunner {

	private final PersonJdbcDao dao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DatabaseJdbcApplication(PersonJdbcDao dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
		SpringApplication.run(DatabaseJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All users -> {}", dao.findAll());
		logger.info("Person with id 10001 -> {}",dao.findById(10001));
		logger.info("Deleting 10003 -> No of rows deleted : {} ",dao.deleteById(10003));
		logger.info("Inserting 10004 -> {}",dao.insertPerson(new Person(10004,"Jose","Cordoba",new Date())));
	}
}

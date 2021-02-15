package com.embl.poc.embl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.embl.poc.embl.model.Person;
import com.embl.poc.embl.repository.PersonRepository;

@SpringBootApplication
public class EmblApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmblApplication.class, args);
	}

	 @Bean
	    CommandLineRunner initDatabase(PersonRepository repository) {
	        return args -> {
	        	repository.save(new Person("Demo1", "Person1", 30 , "Blue"));
	        	repository.save(new Person("Demo2", "Person2", 30 , "Green"));
	        };
	    }
}

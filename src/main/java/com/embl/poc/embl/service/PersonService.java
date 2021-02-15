package com.embl.poc.embl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.poc.embl.exception.PersonNotFoundException;
import com.embl.poc.embl.model.Person;
import com.embl.poc.embl.model.PersonDTO;
import com.embl.poc.embl.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * The service class which contains business logic for .the API
 * This layer is used to validate request, perform business logic and call dao layer to perform
 * database operations
 * 
 * @author Syed.Hasan
 */
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * Returns all persons entries from DB
	 *
	 * @return list of all Persons in DB
	 * @throws PersonNotFoundException if there is no entries in database
	 */  
	public List<PersonDTO> findAll() {
		List<Person> persons = personRepository.findAll();
		if (persons.isEmpty()) {
			throw new PersonNotFoundException();
		}
		return persons.stream().map(this::convertToPersonDTO).collect(Collectors.toList());
	}
	
	/**
	 * Returns a person object from DB on the basis of given id
	 *
	 * @param id
	 * @return list of all Persons in DB
	 * @throws PersonNotFoundException if there is no person with given id in database
	 */ 
	public PersonDTO getPerson(Long id) {
		PersonDTO personDTO = null;
		if (id != null) {
			personDTO = convertToPersonDTO(personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));

		}
		return personDTO;
	}
	
	/**
	 * Save a new person object in DB
	 *
	 * @param personDTO
	 * @return PersonDTO
	 */ 
	public PersonDTO savePerson(PersonDTO newPerson) {
		if (newPerson != null) {
			personRepository.save(convertToPerson(newPerson));
		}

		return newPerson;
	}
	
	/**
	 * Modify a person object on the basis of given id.
	 * If id not available in database it will create a new Person
	 *
	 * @param id
	 * @return PersonDTO 
	 */ 
	public PersonDTO editPerson(PersonDTO editedPerson, Long id) {
		Person personSaved = personRepository.findById(id).map(person->{
			person.setAge(editedPerson.getAge());
			person.setFavouriteColor(editedPerson.getFavouriteColor());
			person.setFirstName(editedPerson.getFirstName());
			person.setLastName(editedPerson.getLastName());
			return personRepository.save(person);
		}).orElseGet(()->
			 personRepository.save(convertToPerson(editedPerson))
		);
		return convertToPersonDTO(personSaved);
	}
	
	/**
	 * Deletes a person object on the basis of given id.
	 *
	 * @param id
	 * @throws PersonNotFoundException if there is no person with given id in database
	 * @return list of all Persons in DB
	 */ 
	public String deletePerson(Long id) {
		if(personRepository.findById(id).isPresent()) {
			personRepository.deleteById(id);
		}else {
			throw new PersonNotFoundException();
		}
		
		return "Person with id "+id+" deleted successfully!";
	}

	/**
	 * Utility method to convert a PersonDTO to Person entity.
	 * 
	 * @param PersonDTO
	 * @return Person
	 */ 
	private Person convertToPerson(PersonDTO editedPerson) {
		return new Person(editedPerson.getFirstName(), editedPerson.getLastName(),
					editedPerson.getAge(), editedPerson.getFavouriteColor());
	}
	
	/**
	 * Utility method to convert a Person entity to PersonDTO.
	 * 
	 * @param Person
	 * @return PersonDTO
	 */ 
	private PersonDTO convertToPersonDTO(Person person) {

		return new PersonDTO(person.getFirstName(), person.getLastName(), person.getAge(), person.getFavouriteColor());

	}

}

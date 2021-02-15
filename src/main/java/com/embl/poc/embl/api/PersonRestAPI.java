package com.embl.poc.embl.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.poc.embl.config.JsonObjectWrapper;
import com.embl.poc.embl.model.PersonDTO;
import com.embl.poc.embl.service.PersonService;
/**
 * The controller class which serves every request.
 * 
 * @author Syed.Hasan
 */
@RestController
@RequestMapping("/embl")
public class PersonRestAPI {
	
	@Autowired
	private PersonService personService;

	/**
	 * Returns all persons entries from database
	 *
	 * @return list of all Persons in database.
	 */    
	@GetMapping("/person/getall")
	public Map<String, List<PersonDTO>> getAllPerson() {
		
	    return JsonObjectWrapper.withLabel("Person", personService.findAll());
	}
	
	/**
	 * Returns person object from DB on the basis of given id
	 *
	 * @param persons Id
	 * @return PersonDTO
	 */
	@GetMapping("/person/get/{id}")
	public Map<String, PersonDTO> getPersonWithId(@PathVariable Long id) {
		  return JsonObjectWrapper.withLabel("Person", personService.getPerson(id));
	}
	
	/**
	 * Creates a new person object and store it in db.
	 *
	 * @param PersonDTO
	 * @return PersonDTO
	 */
	@PostMapping("/person/create")
	public Map<String, PersonDTO> createPerson(@RequestBody PersonDTO newPerson) {
	    return JsonObjectWrapper.withLabel("Person",personService.savePerson(newPerson));
	}
	
	/**
	 * Modify a person object on the basis of given id and store it in db.
	 * This method will create a new Person object if id is not available in database
	 *
	 * @param PersonDTO , Person Id
	 * @return PersonDTO
	 */
	@PutMapping("/person/edit/{id}")
	public Map<String, PersonDTO> editPerson(@RequestBody PersonDTO editedPerson , @PathVariable Long id) {
		 return JsonObjectWrapper.withLabel("Person",personService.editPerson(editedPerson,id));
	}
	
	/**
	 * Delete a person object  from the database on the basis of given id.
	 *
	 * @param Person Id
	 * @return String
	 */
	@DeleteMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable Long id) {
		 return personService.deletePerson(id);
	}

}

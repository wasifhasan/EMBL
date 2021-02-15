package com.embl.poc.embl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.embl.poc.embl.model.Person;
import com.embl.poc.embl.model.PersonDTO;
import com.embl.poc.embl.repository.PersonRepository;
import com.embl.poc.embl.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

 @Mock
 private PersonRepository mockRepository ;
 
 @InjectMocks
 private PersonService personService;
 
 private static Person mockPerson;
 
 private static PersonDTO mockPersonDTO;
 
 @BeforeEach
 public void setup() {
	 mockPerson = new Person("TestFName", "TestLName", 10, "Blue");
	 mockPersonDTO =new PersonDTO("TestFName", "TestLName", 10, "Blue");
 }
 
 @Test
 public void findAllTest() {
	 Mockito.when(mockRepository.findAll()).thenReturn(Arrays.asList(mockPerson));
	 List<PersonDTO> result = personService.findAll();
	 assertNotNull(result);
	 assertEquals(1, result.size());
	}

 @Test
 public void getPersonTest() {
	 Optional<Person> optionalPerson = Optional.ofNullable(mockPerson);
	 Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(optionalPerson);
	 PersonDTO result = personService.getPerson(5L);
	 assertNotNull(result);
	 assertEquals("TestFName", result.getFirstName());
	}

 @Test
 public void savePersonTest() {
	 Mockito.when(mockRepository.save(mockPerson)).thenReturn(mockPerson);
	 PersonDTO result = personService.savePerson(mockPersonDTO);
	 assertNotNull(result);
	}

 @Test
 public void editPersonTest() {
	 mockPersonDTO.setFirstName("newFname");
	 Optional<Person> optionalPerson = Optional.ofNullable(mockPerson);
	 Mockito.when(mockRepository.findById(5L)).thenReturn(optionalPerson);
	 Mockito.when(mockRepository.save(mockPerson)).thenReturn(mockPerson);
	 PersonDTO result = personService.editPerson(mockPersonDTO, 5L);
	 assertNotNull(result);
	 assertEquals("newFname", result.getFirstName());
	}

 
}

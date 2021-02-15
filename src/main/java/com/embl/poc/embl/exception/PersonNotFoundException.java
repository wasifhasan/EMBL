package com.embl.poc.embl.exception;

public class PersonNotFoundException extends RuntimeException {
	
	public PersonNotFoundException() {
	    super("There is no person available" );
	  }

	public PersonNotFoundException(Long id) {
	    super("Person with "+ id +" does not exist" );
	  }
}

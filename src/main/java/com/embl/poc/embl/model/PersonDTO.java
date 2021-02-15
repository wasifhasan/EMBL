package com.embl.poc.embl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The DTO to be used by end users to interact with the API
 * 
 * @author Syed.Hasan
 */
@JsonRootName("Person")
public class PersonDTO {
	
	public PersonDTO() {
		super();
	}
	
	public PersonDTO(String firstName, String lastName, Integer age, String favouriteColor) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColor = favouriteColor;
	}
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("age")
	private Integer age;
	
	@JsonProperty("favourite_color")
	private String favouriteColor;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFavouriteColor() {
		return favouriteColor;
	}

	public void setFavouriteColor(String favouriteColor) {
		this.favouriteColor = favouriteColor;
	}
}

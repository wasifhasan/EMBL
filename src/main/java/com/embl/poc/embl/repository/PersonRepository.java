package com.embl.poc.embl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embl.poc.embl.model.Person;

/**
 * The Repository interface which has all basic CRUD methods
 * this interface is used to store,retrieve,edit, delete values in database
 * 
 * @author Syed.Hasan
 */

public interface PersonRepository extends JpaRepository<Person, Long> {

}

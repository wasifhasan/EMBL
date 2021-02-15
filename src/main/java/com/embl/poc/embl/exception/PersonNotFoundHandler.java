package com.embl.poc.embl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonNotFoundHandler {
   @ExceptionHandler(value = PersonNotFoundException.class)
   public ResponseEntity<Object> exception(PersonNotFoundException exception) {
      return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
   }
}
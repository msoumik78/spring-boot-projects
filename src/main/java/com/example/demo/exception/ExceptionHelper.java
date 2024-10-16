package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHelper  extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = { InvalidInputException.class })
  public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = { PasswordMismatchException.class })
  public ResponseEntity<Object> handleWrongPassword(PasswordMismatchException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
  }
}

package com.cursospring.aulamc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import com.cursospring.aulamc.services.exceptions.DataIntegrityException;
import com.cursospring.aulamc.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResoucesExceptionHandler {
   
   @ExceptionHandler(ObjectNotFoundException.class)
   public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
      StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis() );
      return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
   }
   @ExceptionHandler(DataIntegrityException.class)
   public ResponseEntity<StandartError> DataIntegrity(DataIntegrityException e, HttpServletRequest request){
      StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis() );
      return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
   }
}

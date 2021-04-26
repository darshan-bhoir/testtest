package com.emirates.connection.data.service.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emirates.connection.data.service.exception.BadRequestException;
import com.emirates.connection.data.service.exception.FlightConnectionNotFoundException;
import com.emirates.connection.data.service.model.ErrorDTO;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String ERROR_MESSAGE = "Unable to process your request due to an Internal Issue.";

	@ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDTO("D001",ERROR_MESSAGE), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
	
    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<Object> handleConflict(BadRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDTO(ex.getErrorCode(),ex.getErrorMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(value = { FlightConnectionNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(FlightConnectionNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDTO(ex.getErrorCode(),ex.getErrorMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}

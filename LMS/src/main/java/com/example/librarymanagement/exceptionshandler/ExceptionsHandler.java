package com.example.librarymanagement.exceptionshandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.librarymanagement.utils.ResponseEntityBuilder;

@ControllerAdvice //for catching exceptions by all controller instead of each.
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		List<String> details =new ArrayList<String>();
		
		details.add(ex.getMessage());
		
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST,"Resource Not Found"
				,details);
		
		return ResponseEntityBuilder.build(err);
		
	}
	
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex, HttpHeaders headers,
		HttpStatus status, WebRequest request){
			List<String> details = new ArrayList<String>();
			details.add(ex.getMessage());
			
			ApiError err= new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST,"Bad JSON request",details);
			
			return ResponseEntityBuilder.build(err);
		}
	public ResponseEntity<Object> handleall(Exception ex, WebRequest request){
		
		List<String> details = new ArrayList<String>();
		details.add(ex.getLocalizedMessage());
		
		ApiError err= new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST,"Error occured",details);
		
		return ResponseEntityBuilder.build(err);
	}
	
}

	


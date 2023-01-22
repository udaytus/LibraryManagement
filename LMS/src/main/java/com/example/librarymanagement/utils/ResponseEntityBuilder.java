package com.example.librarymanagement.utils;

import org.springframework.http.ResponseEntity;

import com.example.librarymanagement.exceptionshandler.ApiError;

public class ResponseEntityBuilder {
	
	public static ResponseEntity<Object> build(ApiError apierror){
		return new ResponseEntity<>(apierror,apierror.getStatus());
	}

}

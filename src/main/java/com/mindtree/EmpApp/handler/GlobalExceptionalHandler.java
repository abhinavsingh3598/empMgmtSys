package com.mindtree.EmpApp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.EmpApp.common.ApiResponse;
import com.mindtree.EmpApp.controller.ControllerEmp;
import com.mindtree.EmpApp.exception.EmployeeIdNotFound;

@RestControllerAdvice(assignableTypes = { ControllerEmp.class })
public class GlobalExceptionalHandler {

	@ExceptionHandler(EmployeeIdNotFound.class)
	public ResponseEntity<ApiResponse> GlobalEmployeeExceptionHandler(Exception e) {

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Search Unsuccessfull");
		apiResponse.setError(true);
		apiResponse.setStatus(HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

	}

}

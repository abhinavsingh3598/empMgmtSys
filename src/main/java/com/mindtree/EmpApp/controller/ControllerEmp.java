package com.mindtree.EmpApp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.EmpApp.common.ApiResponse;
import com.mindtree.EmpApp.exception.EmployeeIdNotFound;
import com.mindtree.EmpApp.exception.ServiceException;
import com.mindtree.EmpApp.model.Employee;
import com.mindtree.EmpApp.service.ServiceEmp;

@RestController
public class ControllerEmp {
	@Autowired
	ServiceEmp serviceEmp;

	@PostMapping("add")
	public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee)  {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(serviceEmp.addEmployee(employee));
		apiResponse.setMessage("Employee Added Sucessfully:");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

	}

	@GetMapping("getAll")
	public ResponseEntity<ApiResponse> getAllEmployee() {
		ApiResponse apiResponse = new ApiResponse();
		Set<Employee> set=serviceEmp.getAllEmployee();
		apiResponse.setBody(set);
		apiResponse.setMessage("Getting all the Employees:");
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("getById/{employeeId}")
	public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable int employeeId) throws EmployeeIdNotFound  {

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(serviceEmp.getEmployeeById(employeeId));
		apiResponse.setMessage("Employee Detail of " + employeeId);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

	@DeleteMapping("delete/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable int employeeId) throws EmployeeIdNotFound  {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setBody(serviceEmp.deleteEmployee(employeeId));
		apiResponse.setMessage("deleted employeeID " + employeeId);
		apiResponse.setError(false);
		apiResponse.setStatus(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

	}

}

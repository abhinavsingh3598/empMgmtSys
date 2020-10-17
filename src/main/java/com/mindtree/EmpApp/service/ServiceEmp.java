package com.mindtree.EmpApp.service;

import java.util.Set;

import com.mindtree.EmpApp.exception.EmployeeIdNotFound;
import com.mindtree.EmpApp.model.Employee;

public interface ServiceEmp {

	String addEmployee(Employee employee);

	Set<Employee> getAllEmployee();

	Employee getEmployeeById(int employeeId) throws EmployeeIdNotFound;

	String deleteEmployee(int employeeId) throws EmployeeIdNotFound;

}

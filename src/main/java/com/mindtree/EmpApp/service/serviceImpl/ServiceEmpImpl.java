package com.mindtree.EmpApp.service.serviceImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.EmpApp.exception.EmployeeIdNotFound;
import com.mindtree.EmpApp.model.Employee;
import com.mindtree.EmpApp.repository.EmployeeRepository;
import com.mindtree.EmpApp.service.ServiceEmp;
@Service
public class ServiceEmpImpl implements ServiceEmp {

	@Autowired EmployeeRepository employeeRepository;
	@Override
	public String addEmployee(Employee employee) {
		Employee employee2=employeeRepository.save(employee);
		
		return "data saved successfully";
	}

	@Override
	public Set<Employee> getAllEmployee() {
		
		return employeeRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws EmployeeIdNotFound {
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeIdNotFound("id is not found"));

		return employee;
	}

	@Override
	public String deleteEmployee(int employeeId) throws EmployeeIdNotFound {
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeIdNotFound("id is not found"));
employeeRepository.delete(employee);

		return "successfully deleted";
	}

}

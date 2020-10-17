package com.mindtree.EmpApp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.EmpApp.exception.EmployeeIdNotFound;
import com.mindtree.EmpApp.exception.ServiceException;
import com.mindtree.EmpApp.model.Employee;
import com.mindtree.EmpApp.service.serviceImpl.ServiceEmpImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ControllerEmpTest {
	@Autowired
	private MockMvc mockmvc;
	
	@InjectMocks
	private ControllerEmp ControllerEmp;

	@Mock
	private ServiceEmpImpl serviceEmpImpl;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		mockmvc=MockMvcBuilders.standaloneSetup(ControllerEmp).build();
	}
	@Test
	void testAddEmployee()  {
		String expected="successfully inserted";
		Employee employee=new Employee(2, "abhi");
		when(serviceEmpImpl.addEmployee(employee)).thenReturn(expected);
		assertEquals(expected,ControllerEmp.addEmployee(employee).getBody().getBody() );
//assertEquals(expected, actual);
		
	}

	@Test
	void testGetAllEmployee()  {
		Set<Employee> set=new HashSet();
		set.add(new Employee(1, "abhinav"));
		set.add(new Employee(2, "rahul"));
		set.add(new Employee(3, "abhinav"));
		when(serviceEmpImpl.getAllEmployee()).thenReturn(set);
		assertEquals(set, ControllerEmp.getAllEmployee().getBody().getBody());
	//	assertEquals(expected, actual);
	}

	@Test
	void testGetEmployeeById() throws ServiceException {
		Employee employee=new Employee(2, "abhi");
		when(serviceEmpImpl.getEmployeeById(2)).thenReturn(employee);
		assertEquals(employee, ControllerEmp.getEmployeeById(2).getBody().getBody());
	}

	@Test
	void testDeleteEmployee() throws EmployeeIdNotFound {
		String expected="successfully deleted";
		Employee employee=new Employee(2, "abhi");
		when(serviceEmpImpl.deleteEmployee(2)).thenReturn(expected);
		assertEquals(expected,ControllerEmp.deleteEmployee(2).getBody().getBody() );

	}

}

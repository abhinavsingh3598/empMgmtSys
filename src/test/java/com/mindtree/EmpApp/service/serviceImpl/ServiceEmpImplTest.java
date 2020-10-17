package com.mindtree.EmpApp.service.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import com.mindtree.EmpApp.model.Employee;
import com.mindtree.EmpApp.repository.EmployeeRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ServiceEmpImplTest {
	@Autowired
	private MockMvc mockmvc;
 
	@InjectMocks
	private ServiceEmpImpl serviceEmpImpl;

	@Mock
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		mockmvc=MockMvcBuilders.standaloneSetup(serviceEmpImpl).build();
	}
	
	@Test
	void testAddEmployee() {
		String expected="data saved successfully";
		Employee employee=new Employee(1, "abhinav");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(expected,serviceEmpImpl.addEmployee(employee) );	
	}

	@Test
	void testGetAllEmployee() {
		Set<Employee> set=new HashSet();
		Employee employee=new Employee(1, "abhinav");
		set.add(employee);
		
		List<Employee> list=new ArrayList<Employee>();
		list.add(employee);
		when(employeeRepository.findAll()).thenReturn(list);
		assertEquals(serviceEmpImpl.getAllEmployee(), set);
	}

	@Test
	void testGetEmployeeById() throws EmployeeIdNotFound {
		Employee employee=new Employee(1, "abhinav");
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		
		assertEquals(serviceEmpImpl.getEmployeeById(1),employee );
	}

	@Test
	void testDeleteEmployee() throws EmployeeIdNotFound {
	
	Employee employee=new Employee(1, "abhinav");
	employeeRepository.delete(employee);
	
verify(employeeRepository,times(1)).delete(employee);
	}

}

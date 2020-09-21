package com.dozendog.simplespringboot.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dozendog.simplespringboot.model.Employee;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;



public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService service;
	
    @Mock
    private JdbcTemplate jtmMock;

	//@Test
	public void findAll_return_success() {
		
		
		
		Employee employee1 = new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		Employee employee2 = new Employee(2, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0);
    	
		List<Employee> mockOutput = new ArrayList<Employee>();
	    mockOutput.add(employee1);
	    mockOutput.add(employee2);
		
		String sql="SELECT * FROM employee";

		
		//service = new EmployeeService();
		jtmMock = mock(JdbcTemplate.class);
		
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		Mockito.when(jtmMock.query(sql,new BeanPropertyRowMapper<>(Employee.class))).thenReturn(mockOutput);
		
		
        
        List<Employee> actual = service.findAll();
 
        assertEquals(2, actual.size());
	}
	
	
	//@Test
	public void find_by_id_return_success() {
		
		Employee mockOutput = new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		
		String sql="SELECT * FROM employee WHERE id = ?";
		
		//service = new EmployeeService();
		jtmMock = mock(JdbcTemplate.class);
		
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		Mockito.when(jtmMock.queryForObject(sql,new Object[]{1},new BeanPropertyRowMapper<>(Employee.class))).thenReturn(mockOutput);
	       
        Employee actual = service.find(1);
        
        assertEquals(1, actual.getId());
        assertEquals("John", actual.getFirstName());
	}

	/*
	public boolean insert(Employee employee) {
		
		
		
		boolean success=false;
		
		String sql = "INSERT INTO employee(firstname,surename,titlename,address,dateofbirth,citizenid,"
				+ "nationality,position,salary,gendar,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		int status = jtm.update(sql, 
				employee.getFirstName(),
				employee.getSureName(),
				employee.getTitleName(),
				employee.getAddress(),
				employee.getDateOfBirth(),
				employee.getCitizenid(),
				employee.getNationality(),
				employee.getPosition(),
				employee.getSalary(),
				employee.getGendar(),
				employee.getStatus());
		
		if(status>0){
			success=true;
		}
		return success;
	}
	
	
	public boolean update(Employee employee) {
		
		
		
		boolean success=false;
		
		String sql = "UPDATE employee SET firstname=?,surename=?,titlename=?,address=?,dateofbirth=?,citizenid=?,"
				+ "nationality=?,position=?,salary=?,gendar=?,status=? WHERE id=?";
		
		int status = jtm.update(sql, 
				employee.getFirstName(),
				employee.getSureName(),
				employee.getTitleName(),
				employee.getAddress(),
				employee.getDateOfBirth(),
				employee.getCitizenid(),
				employee.getNationality(),
				employee.getPosition(),
				employee.getSalary(),
				employee.getGendar(),
				employee.getStatus(),
				employee.getId());
		
		if(status>0){
			success=true;
		}
		return success;
	}
	
	//@Cacheable(cacheNames="demoCache", key="#id")  
	public boolean delete(int id) {
		
		
		
		boolean success=false;	
		String sql = "DELETE FROM employee WHERE id = ?";
		
		int status = jtm.update(sql,id);	
		if(status>0){
			success=true;
		}
		return success;
	}
	
	*/
}

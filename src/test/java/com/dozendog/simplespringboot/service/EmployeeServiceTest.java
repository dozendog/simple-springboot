package com.dozendog.simplespringboot.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.dozendog.simplespringboot.model.Employee;



@TestPropertySource(locations="classpath:test.properties")
public class EmployeeServiceTest {

	@Mock
	private EmployeeService service;
	
    @InjectMocks
    private JdbcTemplate jtmMock;
    
    private DataSource dataSource;
    
    
    private DataSource getDataSource() {
    	if(dataSource==null) {
    		dataSource = new EmbeddedDatabaseBuilder()
        			.setType(EmbeddedDatabaseType.H2)
        			.addScript("data.sql")
        			.build();
    	}
    	return dataSource;
    }
    
	@Test
	public void findAll_return_success() {
		
		
		
		//Employee employee1 = new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		//Employee employee2 = new Employee(2, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0);
    	
		//List<Employee> mockOutput = new ArrayList<Employee>();
	    //mockOutput.add(employee1);
	    //mockOutput.add(employee2);
		
		//String sql="SELECT * FROM employee";

		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		//Mockito.when(jtmMock.query(sql,new BeanPropertyRowMapper<>(Employee.class))).thenReturn(mockOutput);
		
        List<Employee> actual = service.findAll();
 
        assertEquals(2, actual.size());
        assertEquals("John",actual.get(0).getFirstName());
        assertEquals("Stark",actual.get(1).getSureName());
	}
	
	
	@Test
	public void find_by_id_return_success() {
		
		//Employee mockOutput = new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		//String sql="SELECT * FROM employee WHERE id = ?";
		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		//Mockito.when(jtmMock.queryForObject(sql,new Object[] {1},new BeanPropertyRowMapper<>(Employee.class))).thenReturn(mockOutput);
	       
        Employee actual = service.find(1);
        
        assertEquals(1, actual.getId());
        assertEquals("John", actual.getFirstName());
	}

	@Test
	public void insert_return_success() {
		
		Employee mockInput = new Employee(3, "Mr.", "Jonathan", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		   
        boolean actual = service.insert(mockInput);
        
        assertEquals(true, actual);
        
	}
	
	@Test
	public void update_return_success() {
		
		Employee mockInput = new Employee(3, "Mr.", "Jonathan", "Wick","555/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		
		//insert before updated
		service.insert(mockInput);
		Employee updateEmployee = service.find(3);
		updateEmployee.setFirstName("Jonathan and Winston");
		   
        boolean actual = service.update(updateEmployee);
        
        assertEquals(true, actual);
	}
	
	@Test
	public void delete_return_success() {
		
		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		   
        boolean actual = service.delete(2);
        
        assertEquals(true, actual);
	}
	
	@Test
	public void delete_return_failed() {
		
		
		service = new EmployeeService();
		jtmMock = new JdbcTemplate(getDataSource());
		 
		ReflectionTestUtils.setField(service, "jtm", jtmMock, JdbcTemplate.class);
		   
        boolean actual = service.delete(10);
        
        assertEquals(false, actual);
	}

}

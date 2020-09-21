package com.dozendog.simplespringboot.controller;

import java.math.BigDecimal;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dozendog.simplespringboot.model.Employee;
import com.dozendog.simplespringboot.service.EmployeeService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.isA;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIntegrationTests {
	
    @Autowired
    private MockMvc mvc;
	
    @MockBean
	private EmployeeService employeeService;
    
    private static String token;
    
    
    private String obtainAccessToken() throws Exception {
    	
    	if(token==null) {
    		
        	String content = "{\"username\":\"user\",\"password\":\"password\"}";
       	 
    	    ResultActions result 
    	      = mvc.perform(post("/authentication")
    	        .content(content)
    	        .contentType(MediaType.APPLICATION_JSON)
    	        .accept(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk());
    	 
    	    String resultString = result.andReturn().getResponse().getContentAsString();
    	 
    	    JacksonJsonParser jsonParser = new JacksonJsonParser();
    	    token = jsonParser.parseMap(resultString).get("token").toString();
    	}
   	 
    	return token;
	}
    

    
    
    @Test
	public void findAllEmployee_return_success() throws Exception {
    	

    	ArrayList<Employee> employeeList = new ArrayList<Employee>();
    	employeeList.add(new Employee(3, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0));
    	employeeList.add(new Employee(4, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0));
    	
    	//method & return value
	 	given(employeeService.findAll()).willReturn(employeeList);
	 
	    mvc.perform(get("/service/v1/employee/all").header("Authorization", "Bearer " + obtainAccessToken()))
	      	.andExpect(status().isOk())
	      	.andExpect(jsonPath("$.employeeList", hasSize(2)))
	      	.andExpect(jsonPath("$.employeeList[0].firstName", is("John")))
	      	.andExpect(jsonPath("$.employeeList[1].sureName", is("Stark")));

	}  
    
    
    
    @Test
	public void findEmployeeById_return_success() throws Exception {
    	

    	int id = 3;
    	Employee employee = new Employee(3, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0);
    	
    	//method & return value
	 	given(employeeService.find(id)).willReturn(employee);
	 
	    mvc.perform(get("/service/v1/employee/3").header("Authorization", "Bearer " + obtainAccessToken()))
	      	.andExpect(status().isOk())
	      	.andExpect(jsonPath("$.employee.id", is(3)))
	      	.andExpect(jsonPath("$.employee.firstName", is("John")));

	}  


    @Test
	public void addNewEmployee_return_success() throws Exception {
    	
    	String content="{\"employee\":{\"id\":2,\"titleName\":\"Mr.\",\"firstName\":\"Tony\",\"sureName\":\"Stark\",\"address\":\"456/1 Rama4 102 Bangkok Thailand\",\"dateOfBirth\":\"30/11/1974\",\"citizenid\":\"1234567890001\",\"nationality\":\"Thai\",\"position\":\"engineer\",\"salary\":300000,\"gendar\":\"male\",\"status\":0}}";
    	Employee employee = new Employee(2, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0);
    	
    	//method & return value
	 	given(employeeService.insert(isA(Employee.class))).willReturn(true);
	 
	    mvc.perform(post("/service/v1/employee").header("Authorization", "Bearer " + obtainAccessToken())
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.accept(MediaType.APPLICATION_JSON)
	    		.content(content))
	      	.andExpect(status().isCreated());

	}  

    
    @Test
	public void updateEmployee_return_success() throws Exception {
    	
    	String content="{\"employee\":{\"id\":2,\"titleName\":\"Mr.\",\"firstName\":\"Tony2\",\"sureName\":\"Stark\",\"address\":\"456/1 Rama4 102 Bangkok Thailand\",\"dateOfBirth\":\"30/11/1974\",\"citizenid\":\"1234567890001\",\"nationality\":\"Thai\",\"position\":\"engineer\",\"salary\":300000,\"gendar\":\"male\",\"status\":0}}";
    	Employee employee = new Employee(2, "Mr.", "Tony2", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0);
    	
    	//method & return value
	 	given(employeeService.update(isA(Employee.class))).willReturn(true);
	 
	    mvc.perform(put("/service/v1/employee").header("Authorization", "Bearer " + obtainAccessToken())
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.accept(MediaType.APPLICATION_JSON)
	    		.content(content))
	      	.andExpect(status().isOk());

	}  
   
    
    @Test
	public void deleteEmployeeById_return_success() throws Exception {
    	
    	int id = 3;
    	
    	//method & return value
	 	given(employeeService.delete(id)).willReturn(true);
	 
	    mvc.perform(delete("/service/v1/employee/3").header("Authorization", "Bearer " + obtainAccessToken()))
	      	.andExpect(status().isOk());

	}  
    

}

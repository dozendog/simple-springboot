package com.dozendog.simplespringboot.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dozendog.simplespringboot.model.Employee;
import com.dozendog.simplespringboot.model.RqBodyEmployee;
import com.dozendog.simplespringboot.model.RsBodyEmployee;
import com.dozendog.simplespringboot.model.RsBodyEmployeeList;
import com.dozendog.simplespringboot.service.EmployeeService;
import com.dozendog.simplespringboot.util.ApplicationResources;



@RestController
@RequestMapping(path = ApplicationResources.URL_PATTERN.SERVICE_V1)
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	private  Logger logger = LogManager.getLogger(this.getClass());
	
	
	
    @RequestMapping(value = "/employee/initiate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RsBodyEmployeeList> initEmployee(@RequestHeader HttpHeaders headers) throws Exception {
  
    	logger.info("calling EmployeeController.initEmployee()");
    	
    	//business logic
    	boolean success = employeeService.initData();
 	
        HttpStatus status = null;
        
        // check result for set HttpStatus
        status = !success ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        
        return new ResponseEntity<>(status);
    }
    

    @RequestMapping(value = "/employee/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RsBodyEmployeeList> findAllEmployee(@RequestHeader HttpHeaders headers) throws Exception {
  
    	logger.info("calling EmployeeController.findAllEmployee()");
    	
    	//business logic
    	ArrayList<Employee> employeeList = employeeService.findAll();
    	
    	RsBodyEmployeeList response = new RsBodyEmployeeList();
    	response.setEmployeeList(employeeList);
 	
        HttpStatus status = null;
        // check null response for set HttpStatus
        if (response != null) {
            status = employeeList==null||employeeList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        }
        return new ResponseEntity<>(response, status);
    }
    
    
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RsBodyEmployee> findEmployeeById(@RequestHeader HttpHeaders headers
    										  			 ,@PathVariable("id") int id) throws Exception {
  
    	logger.info("calling EmployeeController.findEmployeeById()");
    	
    	//business logic
    	Employee employee = employeeService.find(id);
    	
    	RsBodyEmployee response = new RsBodyEmployee();
    	response.setEmployee(employee);
 	
        HttpStatus status = null;
        // check null response for set HttpStatus
        if (response != null) {
            status = employee==null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        }
        return new ResponseEntity<>(response, status);
    }
    
    
    @RequestMapping(value = "/employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewEmployee(@RequestHeader HttpHeaders headers
    										,@RequestBody RqBodyEmployee employee) throws Exception {
  
    	logger.info("calling EmployeeController.addNewEmployee()");
    	
    	//business logic
    	boolean success = employeeService.insert(employee.getEmployee());
 	
        HttpStatus status = null;
        
        // check result for set HttpStatus
        status = !success ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        
        return new ResponseEntity<>(status);
    }
    
    
    @RequestMapping(value = "/employee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployee(@RequestHeader HttpHeaders headers
    										,@RequestBody RqBodyEmployee employee) throws Exception {
  
    	logger.info("calling EmployeeController.updateEmployee()");
    	
    	//business logic
    	boolean success = employeeService.update(employee.getEmployee());
    	
        HttpStatus status = null;
        
        // check result for set HttpStatus
        status = !success ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        
        return new ResponseEntity<>(status);
    }
    
    
    
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEmployeeById(@RequestHeader HttpHeaders headers
    										  	,@PathVariable("id") int id) throws Exception {
  
    	logger.info("calling EmployeeController.deleteEmployeeById()");
    	
    	//business logic
    	boolean success = employeeService.delete(id);
    	
        HttpStatus status = null;
        
        // check result for set HttpStatus
        status = !success ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        
        return new ResponseEntity<>(status);
    }
}

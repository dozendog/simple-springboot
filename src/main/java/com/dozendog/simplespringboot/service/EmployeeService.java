package com.dozendog.simplespringboot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dozendog.simplespringboot.model.Employee;


@Service
public class EmployeeService {
	
	private static Logger logger = LogManager.getLogger(EmployeeService.class);
	
	protected static HashMap<Integer, Employee> employeeMap = new HashMap<>();  
	

	
	//@Cacheable(cacheNames="demoCache")  
	public boolean initData() {
		
		logger.info("calling EmployeeService.initData()");
		
		boolean success=false;
	
		//if ID is exist, then update employee data
		employeeMap.put(1, new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0));  
		employeeMap.put(2, new Employee(2, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0));  
		success = true;
		
		return success;
	}
	
 
	public ArrayList<Employee> findAll() {
		
		logger.info("calling EmployeeService.findAll()");
		
		ArrayList<Employee> list = null;
		
		//if data in cache is null, then return null
		if(employeeMap!=null&&!employeeMap.isEmpty()) {
			
			list = new ArrayList<Employee>();
			for (Employee employee : employeeMap.values()) {
				list.add(employee);
			}
		}
		return list;
	}
	

	public Employee find(int id) {
		
		logger.info("calling EmployeeService.find():"+id);
		
		Employee employee = null;
		
		//if data in cache is null, then return null
		if(employeeMap!=null&&!employeeMap.isEmpty()) {
			employee = employeeMap.get(id);
		}
		return employee;
	}

	
	public boolean insert(Employee employee) {
		
		logger.info("calling EmployeeService.insert():"+employee.getId());
		
		boolean success=false;
		
		//if data in cache is null, then return null
		if(employeeMap!=null) {
			
			//if ID is not exist, then add a new employee
			if(!employeeMap.containsKey(employee.getId())) {
				employeeMap.put(employee.getId(),employee);
				success=true;
			}
		}
		return success;
	}
	
	
	public boolean update(Employee employee) {
		
		logger.info("calling EmployeeService.update():"+employee.getId());
		
		boolean success=false;
		
		//if data in cache is null, then return null
		if(employeeMap!=null) {
			
			//if ID is exist, then update employee data
			if(employeeMap.containsKey(employee.getId())) {
				employeeMap.put(employee.getId(),employee);
				success=true;
			}
		}
		return success;
	}
	
	//@Cacheable(cacheNames="demoCache", key="#id")  
	public boolean delete(int id) {
		
		logger.info("calling EmployeeService.delete():"+id);
		
		boolean success=false;
		
		//if data in cache is null, then return null
		if(employeeMap!=null) {
			
			//if ID is exist, then update employee data
			if(employeeMap.containsKey(id)) {
				employeeMap.remove(id);
				success=true;
			}
		}
		return success;
	}
}

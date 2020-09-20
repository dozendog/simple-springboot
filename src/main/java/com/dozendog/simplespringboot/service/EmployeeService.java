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
	
	private final static Logger logger = LogManager.getLogger(EmployeeService.class);
	
	static HashMap<Integer, Employee> employeeMap = new HashMap<>();  
	
	static {  
		employeeMap.put(1, new Employee(1, "Mr.", "John", "Wick","123/2 Rama9 35 Bangkok Thailand","30/11/1975","1234567890123","Thai","boogieman",new BigDecimal(200000),"male",0));  
		employeeMap.put(2, new Employee(2, "Mr.", "Tony", "Stark","456/1 Rama4 102 Bangkok Thailand","30/11/1974","1234567890001","Thai","engineer",new BigDecimal(300000),"male",0));  
	 
	} 
	

	@Cacheable(cacheNames="demoCache", key="#id")  
	public static ArrayList<Employee> findAll() {
		
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
	
	@Cacheable(cacheNames="demoCache", key="#id")  
	public static Employee find(int id) {
		
		logger.info("calling EmployeeService.find():"+id);
		
		Employee employee = null;
		
		//if data in cache is null, then return null
		if(employeeMap!=null&&!employeeMap.isEmpty()) {
			employee = employeeMap.get(id);
		}
		return employee;
	}
	
	@Cacheable(cacheNames="demoCache", key="#id")  
	public static boolean insert(Employee employee) {
		
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
	
	@Cacheable(cacheNames="demoCache", key="#id")  
	public static boolean update(Employee employee) {
		
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
	
	@Cacheable(cacheNames="demoCache", key="#id")  
	public static boolean delete(int id) {
		
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

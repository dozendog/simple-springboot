package com.dozendog.simplespringboot.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dozendog.simplespringboot.model.Employee;


@Service
public class EmployeeService {
	
	private static Logger logger = LogManager.getLogger(EmployeeService.class);
	
    @Autowired
    private JdbcTemplate jtm;

	
	public List<Employee> findAll() {
		
		logger.info("calling EmployeeService.findAll()");

        String sql = "SELECT * FROM employee";
        List<Employee> list = jtm.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        
        return list;
	}
	

	public Employee find(int id) {
		
		logger.info("calling EmployeeService.find():"+id);
		
        String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = jtm.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<>(Employee.class));

		return employee;
	}

	
	public boolean insert(Employee employee) {
		
		logger.info("calling EmployeeService.insert():"+employee.getId());
		
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
		
		logger.info("calling EmployeeService.update():"+employee.getId());
		
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
		
		logger.info("calling EmployeeService.delete():"+id);
		
		boolean success=false;	
		String sql = "DELETE FROM employee WHERE id = ?";
		
		int status = jtm.update(sql,id);	
		if(status>0){
			success=true;
		}
		return success;
	}
}

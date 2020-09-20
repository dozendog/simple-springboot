package com.dozendog.simplespringboot.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RqBodyEmployee implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2545168785555730810L;

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}

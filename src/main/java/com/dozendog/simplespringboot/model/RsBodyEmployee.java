package com.dozendog.simplespringboot.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RsBodyEmployee implements Serializable {
    private static final long serialVersionUID = 4505936555661962227L;

    private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}

package com.dozendog.simplespringboot.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RsBodyEmployeeList implements Serializable {
    private static final long serialVersionUID = 4505936555661962227L;

    private ArrayList<Employee> employeeList;

	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}

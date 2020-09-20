package com.dozendog.simplespringboot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 608403889346039282L;
	private int id;
	private String titleName;
	private String firstName;
	private String sureName;
	private String address;
	private String dateOfBirth;
	private String citizenid;
	private String nationality;
	private String position;
	private BigDecimal salary;
	private String gendar;
	private int status;
	
	public Employee(){}
	
	public Employee(int id,String titleName,String firstName,String sureName,String address,String dateOfBirth,
		String citizenid,String nationality,String position,BigDecimal salary,String gendar,int status) {
		this.id=id;
		this.titleName=titleName;
		this.firstName=firstName;
		this.sureName=sureName;
		this.address=address;
		this.dateOfBirth=dateOfBirth;
		this.citizenid=citizenid;
		this.nationality=nationality;
		this.position=position;
		this.salary=salary;
		this.gendar=gendar;
		this.status=status;  //0-active,1-inactive
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCitizenid() {
		return citizenid;
	}
	public void setCitizenid(String citizenid) {
		this.citizenid = citizenid;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public String getGendar() {
		return gendar;
	}
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
	

}

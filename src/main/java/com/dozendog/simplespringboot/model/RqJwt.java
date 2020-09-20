package com.dozendog.simplespringboot.model;

import java.io.Serializable;

public class RqJwt implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6284046072546884224L;

	private String username;
	private String password;

	// need default constructor for JSON Parsing
	public RqJwt() {
	}

	public RqJwt(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

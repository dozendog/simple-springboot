package com.dozendog.simplespringboot.model;

import java.io.Serializable;

public class RsJwt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2548404968721023122L;
	
	private String jwttoken = "";

	public RsJwt(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}

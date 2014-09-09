package com.projectx.data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {

	@Id
	private String email;
	
	private String name;

	
	protected Email(){}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Email [ email=" + email + ", name=" + name + "]";
	}


	public Email( String email, String name) {
		this.email = email;
		this.name = name;
	}


	
	
}

package com.projectx.rest.domain;

public class GetByEmailDTO {

	private String email;

	public GetByEmailDTO() {
	
	}
	
	public GetByEmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}

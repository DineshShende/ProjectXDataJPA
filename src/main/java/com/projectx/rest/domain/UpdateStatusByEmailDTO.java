package com.projectx.rest.domain;

public class UpdateStatusByEmailDTO {

	private String email;
	private String status;
	
	public UpdateStatusByEmailDTO() {
	
	}
	
	
	public UpdateStatusByEmailDTO(String email, String status) {
		super();
		this.email = email;
		this.status = status;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

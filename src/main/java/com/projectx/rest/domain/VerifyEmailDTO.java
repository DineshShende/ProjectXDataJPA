package com.projectx.rest.domain;

public class VerifyEmailDTO  {

	private String email;
	private Long emailHash;
	
	public VerifyEmailDTO() {
	
	}
	
	public VerifyEmailDTO(String email, Long emailHash) {
		super();
		this.email = email;
		this.emailHash = emailHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}
	
	
	
	
}

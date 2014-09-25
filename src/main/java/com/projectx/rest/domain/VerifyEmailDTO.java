package com.projectx.rest.domain;

public class VerifyEmailDTO  {

	private Long customerId;
	private Long emailHash;
	
	public VerifyEmailDTO() {
	
	}

	public VerifyEmailDTO(Long customerId, Long emailHash) {
		super();
		this.customerId = customerId;
		this.emailHash = emailHash;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}
	
	
	
}

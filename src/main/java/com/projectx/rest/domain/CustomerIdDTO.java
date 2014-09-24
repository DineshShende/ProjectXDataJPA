package com.projectx.rest.domain;

public class CustomerIdDTO {

	Long customerId;

	public CustomerIdDTO() {
		super();
	
	}
	
	public CustomerIdDTO(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
}

package com.projectx.rest.domain.quickregister;

import com.projectx.data.util.annotation.CustomerDetailsValid;


public class CustomerIdDTO {

	private Long customerId;

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

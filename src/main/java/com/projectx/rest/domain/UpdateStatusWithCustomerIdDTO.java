package com.projectx.rest.domain;

public class UpdateStatusWithCustomerIdDTO {

	private Long customerId;
	private String status;
	
	public UpdateStatusWithCustomerIdDTO() {
		super();
	}

	public UpdateStatusWithCustomerIdDTO(Long customerId, String status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

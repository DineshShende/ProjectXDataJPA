package com.projectx.rest.domain;

import com.projectx.data.domain.CustomerQuickRegisterEntity;

public class CustomerQuickRegisterDTO {
	
	private Long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long mobile;
	
	private Integer pin;
	
	private String status;

	private Integer mobilePin;
	
	private String emailHash;

	
	
	public CustomerQuickRegisterDTO() {
		super();
	}


	public CustomerQuickRegisterEntity toCustomerQuickRegisterEntity()
	{
		CustomerQuickRegisterEntity customerEntity=new CustomerQuickRegisterEntity();
		customerEntity.setEmail(this.email);
		customerEntity.setEmailHash(this.emailHash);
		customerEntity.setFirstName(this.firstName);
		customerEntity.setLastName(this.lastName);
		customerEntity.setMobile(this.mobile);
		customerEntity.setMobilePin(this.mobilePin);
		customerEntity.setPin(this.pin);
		customerEntity.setStatus(this.status);
		
		
		return customerEntity;
		
	}

	public CustomerQuickRegisterDTO(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pin,
			String status, Integer mobilePin, String emailHash) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
		this.mobilePin = mobilePin;
		this.emailHash = emailHash;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}



	public Integer getPin() {
		return pin;
	}



	public void setPin(Integer pin) {
		this.pin = pin;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Integer getMobilePin() {
		return mobilePin;
	}



	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}



	public String getEmailHash() {
		return emailHash;
	}



	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}
	
	

}

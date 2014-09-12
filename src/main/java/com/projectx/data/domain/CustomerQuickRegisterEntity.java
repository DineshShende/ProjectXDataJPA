package com.projectx.data.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CustomerQuickRegisterEntity implements Serializable{
	
	private String firstName;
	private String lastName;
	
	@EmbeddedId 
	CustomerQuickRegisterKey key;
	
	private Integer pin;
	private String status;

	private Integer mobilePin;
	private Long emailHash;

	
	public CustomerQuickRegisterEntity()
	{
		
	}

	
	
	
	public CustomerQuickRegisterEntity(String firstName, String lastName,
			CustomerQuickRegisterKey key, Integer pin, String status,
			Integer mobilePin, Long emailHash) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.key = key;
		this.pin = pin;
		this.status = status;
		this.mobilePin = mobilePin;
		this.emailHash = emailHash;
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

	public CustomerQuickRegisterKey getKey() {
		return key;
	}

	public void setKey(CustomerQuickRegisterKey key) {
		this.key = key;
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

	public Long getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}
	
	
	
	
}

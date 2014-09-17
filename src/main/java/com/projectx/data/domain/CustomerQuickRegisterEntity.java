package com.projectx.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerQuickRegisterEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID")
	private Long customerId;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	
	//@EmbeddedId 
	//CustomerQuickRegisterKey key;
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@Column(name="MOBILE",unique=true)
	private Long mobile;
	
	@Column(name="PIN")
	private Integer pin;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="MOBILE_PIN")
	private Integer mobilePin;
	
	@Column(name="EMAIL_HASH",nullable=true)
	private Long emailHash;

	
	public CustomerQuickRegisterEntity()
	{
		
	}


	
	
	public CustomerQuickRegisterEntity(String firstName, String lastName,
			String email, Long mobile, Integer pin, String status,
			Integer mobilePin, Long emailHash) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
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


	public Long getEmailHash() {
		return emailHash;
	}


	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}

	
	
		
	
}

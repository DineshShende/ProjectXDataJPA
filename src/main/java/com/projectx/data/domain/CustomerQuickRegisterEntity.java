package com.projectx.data.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

@Entity
public class CustomerQuickRegisterEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMERID")
	private Long customerId;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@Column(name="MOBILE",unique=true)
	private Long mobile;
	
	@Column(name="PIN")
	private Integer pin;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="MOBILEPIN")
	private Integer mobilePin;
	
	@Column(name="EMAILHASH",nullable=true)
	private String emailHash;
	
	@Max(3)
	@Column(name="MOBILEVERIFICATIONATTEMPTS")
	private Integer mobileVerificationAttempts;
	
	@Column (name="MOBILEPINSENTTIME")
	private Date mobilePinSentTime;
	
	@Column(name="EMAILHASHSENTTIME")
	private Date emailHashSentTime;
	
	@Column(name="LASTSTATUSCHANGETIME")
	private Date lastStatusChangedTime;
	
	
	public CustomerQuickRegisterEntity()
	{
		
	}

	public CustomerQuickRegisterEntity(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pin,
			String status, Integer mobilePin, String emailHash,
			Integer mobileVerificationAttempts, Date mobilePinSentTime,
			Date emailHashSentTime, Date lastStatusChangedTime) {
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
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.mobilePinSentTime = mobilePinSentTime;
		this.emailHashSentTime = emailHashSentTime;
		this.lastStatusChangedTime = lastStatusChangedTime;
		
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

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	public Date getMobilePinSentTime() {
		return mobilePinSentTime;
	}

	public void setMobilePinSentTime(Date mobilePinSentTime) {
		this.mobilePinSentTime = mobilePinSentTime;
	}

	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}

	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}

	public Date getLastStatusChangedTime() {
		return lastStatusChangedTime;
	}

	public void setLastStatusChangedTime(Date lastStatusChangedTime) {
		this.lastStatusChangedTime = lastStatusChangedTime;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	//@JsonSerialize(using=JsonDateSerializer.class)
	public Date getLastStatusChangedTime() {
		return lastStatusChangedTime;
	}

	//@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setLastStatusChangedTime(Date lastStatusChangedTime) {
		this.lastStatusChangedTime = lastStatusChangedTime;
	}
	*/	
	
}

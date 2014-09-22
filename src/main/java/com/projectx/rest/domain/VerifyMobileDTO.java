package com.projectx.rest.domain;

public class VerifyMobileDTO {
	
	private Long mobile;
	private Integer mobilePin;
	
	public VerifyMobileDTO() {
	
	}
	
	public VerifyMobileDTO(Long mobile, Integer mobilePin) {
		super();
		this.mobile = mobile;
		this.mobilePin = mobilePin;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}
	
	
	

}

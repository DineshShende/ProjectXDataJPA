package com.projectx.rest.domain;

public class UpdateStatusByMobileDTO {
	
	private Long mobile;
	private String status;
	
	
	public UpdateStatusByMobileDTO() {
	
	}
	
	public UpdateStatusByMobileDTO(Long mobile, String status) {
		super();
		this.mobile = mobile;
		this.status = status;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}

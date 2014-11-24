package com.projectx.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerMobileVerificationDetails {

	@Id @Column(name="CUSTOMERID")
	private Long customerId;
	
	@Column(name="MOBILE")
	private Long mobile;
	
	@Column(name="MOBILETYPE")
	private String mobileType;
	
	@Column(name="MOBILEPIN")
	private Integer mobilePin;
	
	@Column(name="MOBILEVERIFICATIONATTEMPTS")
	private Integer mobileVerificationAttempts;
	
	@Column(name="RESENDCOUNT")
	private Integer resendCount;

	public CustomerMobileVerificationDetails() {

	}

	public CustomerMobileVerificationDetails(Long customerId, Long mobile,
			String mobileType, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
		this.mobileType = mobileType;
		this.mobilePin = mobilePin;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.resendCount = resendCount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	@Override
	public String toString() {
		return "CustomerMobileVerificationDetails [customerId=" + customerId
				+ ", mobile=" + mobile + ", mobileType=" + mobileType
				+ ", mobilePin=" + mobilePin + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + ", resendCount=" + resendCount
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerMobileVerificationDetails other = (CustomerMobileVerificationDetails) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		return true;
	}
	
	
}

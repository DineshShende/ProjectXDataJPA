package com.projectx.data.domain.quickregister;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EmailVerificationKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="CUSTOMERID")
	private Long customerId;
	
	@Column(name="CUSTOMERTYPE")
	private Integer customerType;
	
	@Column(name="EMAILTYPE")
	private Integer emailType;

	public EmailVerificationKey() {
		
	}

	public EmailVerificationKey(Long customerId, Integer customerType,
			Integer emailType) {

		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
	}

	
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	@Override
	public String toString() {
		return "EmailVerificationKey [customerId=" + customerId
				+ ", customerType=" + customerType + ", emailType=" + emailType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
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
		EmailVerificationKey other = (EmailVerificationKey) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		return true;
	}

	
	
}

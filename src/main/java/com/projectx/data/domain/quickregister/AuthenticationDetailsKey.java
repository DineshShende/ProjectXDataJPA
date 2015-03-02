package com.projectx.data.domain.quickregister;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class AuthenticationDetailsKey implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name="CUSTOMERID")
	private Long customerId;
	
	@NotNull
	@Column(name="CUSTOMERTYPE")
	private Integer customerType;

	public AuthenticationDetailsKey() {

	}

	public AuthenticationDetailsKey(Long customerId, Integer customerType) {

		this.customerId = customerId;
		this.customerType = customerType;
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

	
	
	@Override
	public String toString() {
		return "AuthenticationDetailsKey [customerId=" + customerId
				+ ", customerType=" + customerType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
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
		AuthenticationDetailsKey other = (AuthenticationDetailsKey) obj;
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
		return true;
	}
	
	
	
	
}

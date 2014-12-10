package com.projectx.data.domain.completeregister;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

@Embeddable
public class DocumentKey  implements Serializable {

	@Column(name="CUSTOMERID")
	private Long customerId;
	
	@Column(name="CUSTOMERTYPE")
	private Integer customerType;
	
	@Column(name="DOCUMENTNAME")
	private String documentName;

	public DocumentKey() {

	}

	public DocumentKey(Long customerId, Integer customerType,
			String documentName) {
		this.customerId = customerId;
		this.customerType = customerType;
		this.documentName = documentName;
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

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Override
	public String toString() {
		return "DocumentKey [customerId=" + customerId + ", customerType="
				+ customerType + ", documentName=" + documentName + "]";
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
				+ ((documentName == null) ? 0 : documentName.hashCode());
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
		DocumentKey other = (DocumentKey) obj;
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
		if (documentName == null) {
			if (other.documentName != null)
				return false;
		} else if (!documentName.equals(other.documentName))
			return false;
		return true;
	}
	
	
	
	
}

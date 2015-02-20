package com.projectx.data.domain.async;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RETRIGGERDETAILS")
public class RetriggerDetails {

	@Column(name="RETRIGGERID")
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long retriggerId;
	
	@Column(name="SERVICE")
	private String service;
	
	@Column(name="DATA")
	private String data;

	public RetriggerDetails() {

	}

	public RetriggerDetails(String service, String data) {
		super();
		this.service = service;
		this.data = data;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
	public Long getRetriggerId() {
		return retriggerId;
	}

	public void setRetriggerId(Long retriggerId) {
		this.retriggerId = retriggerId;
	}

	@Override
	public String toString() {
		return "RetriggerDetails [retriggerId=" + retriggerId + ", service="
				+ service + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((retriggerId == null) ? 0 : retriggerId.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		RetriggerDetails other = (RetriggerDetails) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (retriggerId == null) {
			if (other.retriggerId != null)
				return false;
		} else if (!retriggerId.equals(other.retriggerId))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}

	
	
	
}

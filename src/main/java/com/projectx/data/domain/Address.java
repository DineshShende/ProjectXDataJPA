package com.projectx.data.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AddressTable")
public class Address {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESSID")
	private Long addressId;
	
	@Column(name="ADDRESSLINE")
	private String addressLine;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="PINCODE")
	private Integer pincode;
	
	@Column(name="INSERTTIME")
	private Date insertTime;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="UPDATEBY")
	private String updatedBy;

	public Address() {

	}

	public Address(Long addressId, String addressLine, String city,
			String state, Integer pincode, Date insertTime, Date updateTime,
			String updatedBy) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressLine="
				+ addressLine + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result
				+ ((addressLine == null) ? 0 : addressLine.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (addressLine == null) {
			if (other.addressLine != null)
				return false;
		} else if (!addressLine.equals(other.addressLine))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}
	
	
	
	
	
}

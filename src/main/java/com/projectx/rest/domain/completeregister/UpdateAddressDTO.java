package com.projectx.rest.domain.completeregister;

import com.projectx.data.domain.completeregister.Address;

public class UpdateAddressDTO {

	private Long customerId;
	
	private Address address;

	public UpdateAddressDTO() {

	}

	public UpdateAddressDTO(Long customerId, Address address) {
		super();
		this.customerId = customerId;
		this.address = address;
	}

	@Override
	public String toString() {
		return "UpdateFirmAddressDTO [customerId=" + customerId + ", address="
				+ address + "]";
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}

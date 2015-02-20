package com.projectx.data.repository.request;

import java.util.List;

import com.projectx.data.domain.request.FreightRequestByCustomer;

import com.projectx.data.domain.request.FreightRequestByVendor;

public interface FreightRequestByCustomerCustomRepository {

	
	List<FreightRequestByCustomer> getMatchingCustomerRequest(FreightRequestByVendor freightRequestByVendor);
}

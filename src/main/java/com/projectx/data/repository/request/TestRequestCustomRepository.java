package com.projectx.data.repository.request;

import java.util.List;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.TestRequest;

public interface TestRequestCustomRepository {
	
	
	
	List<TestRequest> getMatchingVendorRequest(FreightRequestByCustomer freightRequestByCustomer);

}

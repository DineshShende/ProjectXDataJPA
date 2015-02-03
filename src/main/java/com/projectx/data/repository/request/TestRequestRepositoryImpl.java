package com.projectx.data.repository.request;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.TestRequest;

public class TestRequestRepositoryImpl extends QueryDslRepositorySupport
		implements TestRequestCustomRepository {

	public TestRequestRepositoryImpl() {
		super(TestRequest.class);

	}

	@Override
	public List<TestRequest> getMatchingVendorRequest(
			FreightRequestByCustomer freightRequestByCustomer) {

		//QTestRequest qTestRequest;
		
		
		//from(paths)
		
		return null;
	}

	

}

package com.projectx.data.repository.request;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.domain.request.TestRequest;

public interface TestRequestRepository extends CrudRepository<TestRequest, Long>,TestRequestCustomRepository {

	List<TestRequest> findByVendorId(Long vendorId);
	
}

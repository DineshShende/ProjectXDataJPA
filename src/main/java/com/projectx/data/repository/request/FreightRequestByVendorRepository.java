package com.projectx.data.repository.request;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.request.FreightRequestByVendor;

public interface FreightRequestByVendorRepository extends 
		CrudRepository<FreightRequestByVendor, Long>/*,FreightRequestByVendorCustomRepository*/ {

	
	
	List<FreightRequestByVendor> findByVendorId(Long vendorId);
}

package com.projectx.data.repository.handshake;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.handshake.DealDetails;

public interface DealDetailsRepository extends CrudRepository<DealDetails, Long> {

	DealDetails findByFreightRequestByCustomerId(Long customerId);
	
	DealDetails findByFreightRequestByVendorId(Long vendorsId);
	
}

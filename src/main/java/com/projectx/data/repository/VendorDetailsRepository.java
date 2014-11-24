package com.projectx.data.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.VendorDetails;

@Repository
public interface VendorDetailsRepository extends CrudRepository<VendorDetails, UUID> {

	@Override
	VendorDetails save(VendorDetails vendorDetails);
	
}

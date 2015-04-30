package com.projectx.data.repository.completeregister;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.VehicleDetails;

@Repository
public interface VehicleDetailsRepository extends
		CrudRepository<VehicleDetails, Long> {
	
	
	@Query(value="select * from vehicledetails where vendorid=:vendorId",nativeQuery=true)
	List<VehicleDetails> getVehiclesByVendorId(@Param("vendorId")Long vendorId);
	
	VehicleDetails findByRegistrationNumber(String registrationNumber);
	
	VehicleDetails findByChassisNumber(String chassisNumber);
	
}

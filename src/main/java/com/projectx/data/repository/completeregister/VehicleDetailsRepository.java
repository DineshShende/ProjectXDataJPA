package com.projectx.data.repository.completeregister;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.VehicleDetailsDTO;

@Repository
public interface VehicleDetailsRepository extends
		CrudRepository<VehicleDetailsDTO, Long> {
	
	
	@Query(value="select * from vehicledetails where vendorid=:vendorId",nativeQuery=true)
	List<VehicleDetailsDTO> getVehiclesByVendorId(@Param("vendorId")Long vendorId);
	
	VehicleDetailsDTO findByRegistrationNumber(String registrationNumber);
	
}

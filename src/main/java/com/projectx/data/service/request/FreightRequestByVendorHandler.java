package com.projectx.data.service.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.domain.request.TestRequest;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@Component
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Override
	public TestRequest toFreightRequestByVendor(
			FreightRequestByVendorDTO freightRequestByVendorDTO) {


		VehicleDetailsDTO vehicleDetailsDTO=vehicleDetailsRepository.findByRegistrationNumber(freightRequestByVendorDTO.getVehicleRegistrationNumber());
		
		TestRequest testRequest=new TestRequest(freightRequestByVendorDTO.getRequestId(), vehicleDetailsDTO,
				freightRequestByVendorDTO.getSource(),freightRequestByVendorDTO.getDestination(),freightRequestByVendorDTO.getDriverId(),
				freightRequestByVendorDTO.getAvailableDate(),freightRequestByVendorDTO.getAvailableTime(),freightRequestByVendorDTO.getPickupRangeInKm(),
				freightRequestByVendorDTO.getVendorId(),freightRequestByVendorDTO.getStatus(), freightRequestByVendorDTO.getInsertTime(), 
				freightRequestByVendorDTO.getUpdateTime(), freightRequestByVendorDTO.getUpdatedBy());
		
		
		return testRequest;
	}

	@Override
	public FreightRequestByVendorDTO toFreightRequestByVendorDTO(
			TestRequest freightRequestByVendor) {

		FreightRequestByVendorDTO freightRequestByVendorDTO=new FreightRequestByVendorDTO(freightRequestByVendor.getRequestId(),
				freightRequestByVendor.getVehicleDetailsId().getRegistrationNumber(), freightRequestByVendor.getSource(),
				freightRequestByVendor.getDestination(),freightRequestByVendor.getDriverId(),freightRequestByVendor.getAvailableDate(),
				freightRequestByVendor.getAvailableTime(),freightRequestByVendor.getPickupRangeInKm(),freightRequestByVendor.getVendorId(),
				freightRequestByVendor.getStatus(), freightRequestByVendor.getInsertTime(), freightRequestByVendor.getUpdateTime(),freightRequestByVendor.getUpdatedBy());
		
		
		return freightRequestByVendorDTO;
	}

}

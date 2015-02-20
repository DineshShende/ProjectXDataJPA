package com.projectx.data.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;

@RestController
@RequestMapping(value="/vehicle")
public class VehicleDetailsController {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public VehicleDetailsDTO save(@RequestBody VehicleDetailsDTO vehicleDetails)
	{
		VehicleDetailsDTO savedEntity=vehicleDetailsRepository.save(vehicleDetails);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getVehiclesByVendorId/{vendorId}")
	public List<VehicleDetailsDTO> getVehiclesByVendorId(@PathVariable Long vendorId)
	{
		List<VehicleDetailsDTO> vehicleDetailsList=vehicleDetailsRepository.getVehiclesByVendorId(vendorId);
		
		return vehicleDetailsList;
	}



	@RequestMapping(value="/getByRegistrationNumber/{registrationNumber}")
	public ResponseEntity<VehicleDetailsDTO> findRegistrationNumber(@PathVariable String registrationNumber)
	{
		ResponseEntity<VehicleDetailsDTO> result=null;
		
		VehicleDetailsDTO vehicleDetails=vehicleDetailsRepository.findByRegistrationNumber(registrationNumber);
		
		if(vehicleDetails ==null)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		else
		{
			result=new ResponseEntity<VehicleDetailsDTO>(vehicleDetails, HttpStatus.FOUND);
		}
		
		return result;
	}

	
	
	@RequestMapping(value="/getById/{vehicleId}")
	public ResponseEntity<VehicleDetailsDTO> findOne(@PathVariable Long vehicleId)
	{
		ResponseEntity<VehicleDetailsDTO> result=null;
		
		VehicleDetailsDTO vehicleDetails=vehicleDetailsRepository.findOne(vehicleId);
	
		if(vehicleDetails ==null)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		else
		{
			result=new ResponseEntity<VehicleDetailsDTO>(vehicleDetails, HttpStatus.FOUND);
		}
		
		return result;
	}
	
	@RequestMapping(value="/deleteById/{vehicleId}")
	public Boolean deleteById(@PathVariable Long vehicleId)
	{
		vehicleDetailsRepository.delete(vehicleId);
		
		return true;
	}

	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer vehicleDetails=(int) vehicleDetailsRepository.count();
		
		return vehicleDetails;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		vehicleDetailsRepository.deleteAll();
		
		return true;
	}
}

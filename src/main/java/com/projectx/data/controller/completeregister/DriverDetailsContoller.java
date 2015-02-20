package com.projectx.data.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.repository.completeregister.DriverDetailsCustomRepository;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;

@RestController
@RequestMapping(value="/driver")
public class DriverDetailsContoller {

	@Autowired
	DriverDetailsCustomRepository driverDetailsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<DriverDetails> save(@RequestBody DriverDetails driverDetails)
	{
		ResponseEntity<DriverDetails> savedEntityResponse=null;
		
		DriverDetails savedEntity=null;
		
		try
		{
			savedEntity=driverDetailsRepository.save(driverDetails);
			
			savedEntityResponse=new ResponseEntity<DriverDetails>(savedEntity, HttpStatus.CREATED);
		}
		catch(DataIntegrityViolationException e)
		{
			savedEntityResponse=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return savedEntityResponse;
				
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<DriverDetails> update(@RequestBody DriverDetails driverDetails)
	{
		ResponseEntity<DriverDetails> result=null;
		
		DriverDetails savedEntity=new DriverDetails();
		
		try
		{
			savedEntity=driverDetailsRepository.update(driverDetails);
			
			result=new ResponseEntity<DriverDetails>(savedEntity, HttpStatus.OK);
		}
		catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
				
	}
	
	@RequestMapping(value="/updateMobileAndMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateMobileAndMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO mobileVerificationStatusDTO)
	{
		Integer result=driverDetailsRepository
				.updateMobileAndMobileVerificationStatus(mobileVerificationStatusDTO.getCustomerId(), mobileVerificationStatusDTO.getMobile(),
						mobileVerificationStatusDTO.getStatus());
		
		return result;
	}
	
	@RequestMapping(value="/getDriversByVendorId/{vendorId}")
	public List<DriverDetails> getDriversByVendorId(@PathVariable Long vendorId)
	{
		List<DriverDetails> driverList=driverDetailsRepository.getDriverListByVendorId(vendorId);
		
		return driverList;
	}
	
	@RequestMapping(value="/getById/{driverId}")
	public ResponseEntity<DriverDetails> findOne(@PathVariable Long driverId)
	{
		ResponseEntity<DriverDetails> result=null;
		
		DriverDetails driverDetails=null;
		
		driverDetails=driverDetailsRepository.findOne(driverId);
		
		if(driverDetails==null)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			result=new ResponseEntity<DriverDetails>(driverDetails, HttpStatus.FOUND);
		}
			
		return result;
	}
	
	@RequestMapping(value="/deleteById/{driverId}")
	public Boolean deleteById(@PathVariable Long driverId)
	{
		driverDetailsRepository.deleteByKey(driverId);
		
		return true;
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer driverDetails=driverDetailsRepository.count();
		
		return driverDetails;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		driverDetailsRepository.deleteAll();
		
		return true;
	}
}

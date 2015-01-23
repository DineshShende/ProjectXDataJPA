package com.projectx.data.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	public DriverDetails save(@RequestBody DriverDetails driverDetails)
	{
		DriverDetails savedEntity=null;
		
		try
		{
			savedEntity=driverDetailsRepository.save(driverDetails);
		}
		catch(DataIntegrityViolationException e)
		{
			return new DriverDetails();
		}
		
		return savedEntity;
				
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public DriverDetails update(@RequestBody DriverDetails driverDetails)
	{
		DriverDetails savedEntity=new DriverDetails();
		
		try
		{
			savedEntity=driverDetailsRepository.update(driverDetails);
		}
		catch(DataIntegrityViolationException e)
		{
			return driverDetailsRepository.findOne(driverDetails.getDriverId());
		}
		
		return savedEntity;
				
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
	public DriverDetails findOne(@PathVariable Long driverId)
	{
		DriverDetails driverDetails=driverDetailsRepository.findOne(driverId);
		
		return driverDetails;
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

package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;


@Component
@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsCustomRepository vendorDetailsRepository;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public VendorDetails save(@RequestBody VendorDetails vendorDetails)
	{
		VendorDetails savedEntity=vendorDetailsRepository.save(vendorDetails);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public VendorDetails update(@RequestBody VendorDetails vendorDetails)
	{
		VendorDetails updatedEntity=vendorDetailsRepository.update(vendorDetails);
		
		return updatedEntity;
	}
	
	@RequestMapping(value="/getById/{vendorId}")
	public VendorDetails findOne(@PathVariable("vendorId") Long vendorId)
	{
		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO updateVerificationStatusDTO)
	{
		Integer updateStatus=vendorDetailsRepository.updateMobileAndVerificationStatus(updateVerificationStatusDTO.getCustomerId(),
				updateVerificationStatusDTO.getMobile(),updateVerificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public Integer updateEmailVerificationStatus(@RequestBody UpdateEmailVerificationStatusDTO updateVerificationStatusDTO)
	{
		Integer updateStatus=vendorDetailsRepository.updateEmailAndVerificationStatus(updateVerificationStatusDTO.getCustomerId(), 
				updateVerificationStatusDTO.getEmail(),updateVerificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer fetchedResult=vendorDetailsRepository.count();
		
		return fetchedResult;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		Boolean fetchedResult=vendorDetailsRepository.deleteAll();
		
		return fetchedResult;
	}
}

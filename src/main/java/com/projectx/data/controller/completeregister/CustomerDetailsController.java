package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateAddressDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;


@RestController
@RequestMapping(value="/customer/completeregister")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerDetails saveCustomerDetails(@RequestBody CustomerDetails customerDetails)
	{
		//System.out.println(customerDetails);
		CustomerDetails details=customerDetailsCustomRepository.save(customerDetails);
		
		//System.out.println(details);
		
		return details;
	}
	@RequestMapping(value="/{customerId}",method=RequestMethod.GET)
	public CustomerDetails findOne(@PathVariable("customerId")Long customerId )
	{
		CustomerDetails fetchedEntity=customerDetailsCustomRepository.findOne(customerId);
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/updateFirmAddress",method=RequestMethod.POST)
	public CustomerDetails updateFirmAddress(@RequestBody UpdateAddressDTO addressDTO)
	{
		CustomerDetails updatedEntity=customerDetailsCustomRepository.updateFirmAddress(addressDTO);
		
		return updatedEntity;
	}
	
	@RequestMapping(value="/updateHomeAddress",method=RequestMethod.POST)
	public CustomerDetails updateHomeAddress(@RequestBody UpdateAddressDTO addressDTO)
	{
		CustomerDetails updatedEntity=customerDetailsCustomRepository.updateHomeAddress(addressDTO);
		
		System.out.println(updatedEntity);
		
		return updatedEntity;
	}
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO verificationStatusDTO)
	{
		Integer updateStatus=customerDetailsCustomRepository.updateMobileVerificationStatus(verificationStatusDTO.getCustomerId(),
				verificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/updateSecondaryMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateSecondaryMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO verificationStatusDTO)
	{
		Integer updateStatus=customerDetailsCustomRepository.updateSecondaryMobileVerificationStatus(verificationStatusDTO.getCustomerId(),
				verificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Long count()
	{
		return customerDetailsCustomRepository.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		return true;
	}
}

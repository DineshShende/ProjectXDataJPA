package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateAddressDTO;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusDTO;


@RestController
@RequestMapping(value="/customer/completeregister")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CustomerDetails> saveCustomerDetails(@RequestBody CustomerDetails customerDetails)
	{
		ResponseEntity<CustomerDetails> result=null;
		
		try{		
		
			CustomerDetails details=customerDetailsCustomRepository.save(customerDetails);
			result=new ResponseEntity<CustomerDetails>(details, HttpStatus.CREATED);
		
		}
		catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		return result;
	}
	@RequestMapping(value="/{customerId}",method=RequestMethod.GET)
	public ResponseEntity<CustomerDetails> findOne(@PathVariable("customerId")Long customerId )
	{
		ResponseEntity<CustomerDetails> result=null;
		
		CustomerDetails fetchedEntity=customerDetailsCustomRepository.findOne(customerId);
		
		if(fetchedEntity!=null)
		{
			result=new ResponseEntity<CustomerDetails>(fetchedEntity, HttpStatus.FOUND);
		}
		
		else
		{	
			result=new ResponseEntity<CustomerDetails>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO verificationStatusDTO)
	{
		Integer updateStatus=customerDetailsCustomRepository.updateMobileAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
				verificationStatusDTO.getMobile(),verificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/updateSecondaryMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateSecondaryMobileVerificationStatus(@RequestBody UpdateMobileVerificationStatusDTO verificationStatusDTO)
	{
		Integer updateStatus=customerDetailsCustomRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
				verificationStatusDTO.getMobile(),verificationStatusDTO.getStatus());
		
		return updateStatus;
	}
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public Integer updateEmailVerificationStatus(@RequestBody UpdateEmailVerificationStatusDTO verificationStatusDTO)
	{
		Integer updateStatus=customerDetailsCustomRepository.updateEmailAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
				verificationStatusDTO.getEmail(),verificationStatusDTO.getStatus());
		
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

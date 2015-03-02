package com.projectx.data.controller.completeregister;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;
import com.projectx.data.util.validator.AuthenticationDetailsValidator;
import com.projectx.data.util.validator.CustomerDetailsValidator;
import com.projectx.rest.domain.completeregister.UpdateAddressDTO;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;


@RestController
@RequestMapping(value="/customer/completeregister")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CustomerDetails> saveCustomerDetails(@Valid @RequestBody CustomerDetails customerDetails,BindingResult resultValid)
	{
		if(resultValid.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
			result=new ResponseEntity<CustomerDetails>(fetchedEntity, HttpStatus.FOUND);
		else
			result=new ResponseEntity<CustomerDetails>(HttpStatus.NO_CONTENT);
		
		
		return result;
	}
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateMobileVerificationStatus(@Valid @RequestBody UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
	
		try{
		
			Integer updateStatus=customerDetailsCustomRepository.updateMobileAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
					verificationStatusDTO.getMobile(),verificationStatusDTO.getStatus(),verificationStatusDTO.getUpdatedBy());
			
			result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/updateSecondaryMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateSecondaryMobileVerificationStatus(@Valid @RequestBody UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		try{
		
			Integer updateStatus=customerDetailsCustomRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
					verificationStatusDTO.getMobile(),verificationStatusDTO.getStatus(),verificationStatusDTO.getUpdatedBy());
			
			result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateEmailVerificationStatus(@Valid @RequestBody UpdateEmailVerificationStatusUpdatedByDTO verificationStatusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		try{
			Integer updateStatus=customerDetailsCustomRepository.updateEmailAndVerificationStatusInMainEntity(verificationStatusDTO.getCustomerId(),
					verificationStatusDTO.getEmail(),verificationStatusDTO.getStatus(),verificationStatusDTO.getUpdatedBy());
			
			result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
		return result;
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

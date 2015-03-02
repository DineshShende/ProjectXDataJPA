package com.projectx.data.controller.quickregister;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;

@RestController
@RequestMapping(value="/customer/quickregister/emailVerification")
public class EmailVerificationController {

	@Autowired
	EmailVerificationDetailsRepository customerEmailVerificationDetailsRepository;
	
	@RequestMapping(value="/saveEmailVerificationDetails",method=RequestMethod.POST)
	public ResponseEntity<EmailVerificationDetails> saveEmailVerificationEntity(@Valid @RequestBody EmailVerificationDetails emailVerificationDetails,
			BindingResult resultValid)
	{
		if(resultValid.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); 
		
		ResponseEntity<EmailVerificationDetails> result=null;
		
		try{
			EmailVerificationDetails savedEmailVerificationDetails=customerEmailVerificationDetailsRepository.save(emailVerificationDetails);
			result=new ResponseEntity<EmailVerificationDetails>(savedEmailVerificationDetails, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
				
		return result;
		
	}
	
	@RequestMapping(value="/getEmailVerificationDetailsByCustomerIdAndEmail",method=RequestMethod.POST)
	public ResponseEntity<EmailVerificationDetails> getEmailVerificationDetailsByCustomerIdAndEmail(@RequestBody CustomerIdTypeEmailTypeDTO customerIdEmailDTO)
	{
		ResponseEntity<EmailVerificationDetails> result=null;
		
		EmailVerificationKey key=new EmailVerificationKey(customerIdEmailDTO.getCustomerId(), customerIdEmailDTO.getCustomerType(),
				customerIdEmailDTO.getEmailType());
		
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVerificationDetailsRepository
				.findOne(key);
				
		
		if(fetchedEmailVerificationDetails!=null)
			result=new ResponseEntity<EmailVerificationDetails>(fetchedEmailVerificationDetails, HttpStatus.FOUND);
		else
			result=new  ResponseEntity<>(HttpStatus.NO_CONTENT);
				
		
		return result;
		
	}
	
	
	@RequestMapping(value="/getEmailVerificationDetailsByEmail",method=RequestMethod.POST)
	public ResponseEntity<EmailVerificationDetails> getEmailVerificationDetailsByEmail(@RequestBody EmailDTO emailDTO)
	{
		ResponseEntity<EmailVerificationDetails> result=null;
		
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVerificationDetailsRepository
				.findByEmail(emailDTO.getEmail());
		
		if(fetchedEmailVerificationDetails==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<EmailVerificationDetails>(fetchedEmailVerificationDetails, HttpStatus.FOUND);
		
		return result;
		
	}

	@RequestMapping(value="/resetEmailHashAndEmailHashSentTime",method=RequestMethod.POST)
	public ResponseEntity<Integer> resetEmailHashAndEmailHashSentTime(@RequestBody UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto)
	{
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.resetEmailHashAndEmailHashSentTime(dto.getCustomerId(),dto.getCustomerType(), dto.getEmailType(), dto.getEmailHash(), dto.getEmailHashSentTime(),
						dto.getResendCount(),new Date(),dto.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
		
	}
	
	@RequestMapping(value="/incrementResendCountByCustomerIdAndEmail",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementResendCountByCustomerIdAndEmail(@RequestBody CustomerIdTypeEmailTypeUpdatedByDTO customerIdEmailDTO)
	{
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.incrementResendCountByCustomerIdAndEmail(customerIdEmailDTO.getCustomerId(),customerIdEmailDTO.getCustomerType(), customerIdEmailDTO.getEmailType(),
						new Date(),customerIdEmailDTO.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
		
	}
	
	@RequestMapping(value="/deleteByKey",method=RequestMethod.POST)
	public ResponseEntity<Boolean> deleteByKey(@RequestBody EmailVerificationKey key)
	{
		ResponseEntity<Boolean> result=null;
		
		try{
			customerEmailVerificationDetailsRepository.delete(key);
			result=new ResponseEntity<Boolean>(true, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getCount")
	public Integer getEmailVerificationCount()
	{
		
		return new Integer((int) customerEmailVerificationDetailsRepository
				.count());
		
	}
	
	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting")
	public Boolean clearEmailVerificationForTesting()
	{
		customerEmailVerificationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	
	/*
	@RequestMapping(value="/test")
	public EmailVerificationDetails getEmailVerificationDetails()
	{
		return new EmailVerificationDetails(new EmailVerificationKey(212L,1, 2) , "dineshshe@gmail.com", "skjgwjhsgfjguriueyiryeyriuyeiur", new Date(), 0,new Date(), new Date(), "ME");
	}
	*/
}

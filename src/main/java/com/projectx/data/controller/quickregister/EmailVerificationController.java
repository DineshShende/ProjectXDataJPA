package com.projectx.data.controller.quickregister;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;

@RestController
@RequestMapping(value="/customer/quickregister/emailVerification")
public class EmailVerificationController {

	@Autowired
	EmailVerificationDetailsRepository customerEmailVerificationDetailsRepository;
	
	@RequestMapping(value="/saveEmailVerificationDetails",method=RequestMethod.POST)
	public EmailVerificationDetails saveEmailVerificationEntity(@RequestBody EmailVerificationDetails emailVerificationDetails)
	{
		EmailVerificationDetails savedEmailVerificationDetails=customerEmailVerificationDetailsRepository.save(emailVerificationDetails);
		
		return savedEmailVerificationDetails;
		
	}
	
	@RequestMapping(value="/getEmailVerificationDetailsByCustomerIdAndEmail",method=RequestMethod.POST)
	public EmailVerificationDetails getEmailVerificationDetailsByCustomerIdAndEmail(@RequestBody CustomerIdTypeEmailTypeDTO customerIdEmailDTO)
	{
		EmailVerificationKey key=new EmailVerificationKey(customerIdEmailDTO.getCustomerId(), customerIdEmailDTO.getCustomerType(),
				customerIdEmailDTO.getEmailType());
		
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVerificationDetailsRepository
				.findOne(key);
				
		
		if(fetchedEmailVerificationDetails==null)
			return new EmailVerificationDetails();
		
		return fetchedEmailVerificationDetails;
		
	}
	
	
	@RequestMapping(value="/getEmailVerificationDetailsByEmail",method=RequestMethod.POST)
	public EmailVerificationDetails getEmailVerificationDetailsByEmail(@RequestBody EmailDTO emailDTO)
	{
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVerificationDetailsRepository
				.findByEmail(emailDTO.getEmail());
		
		if(fetchedEmailVerificationDetails==null)
			return new EmailVerificationDetails();
		
		return fetchedEmailVerificationDetails;
		
	}

	@RequestMapping(value="/resetEmailHashAndEmailHashSentTime",method=RequestMethod.POST)
	public Integer resetEmailHashAndEmailHashSentTime(@RequestBody UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto)
	{
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.resetEmailHashAndEmailHashSentTime(dto.getCustomerId(),dto.getCustomerType(), dto.getEmailType(), dto.getEmailHash(), dto.getEmailHashSentTime(),
						dto.getResendCount());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/incrementResendCountByCustomerIdAndEmail",method=RequestMethod.POST)
	public Integer incrementResendCountByCustomerIdAndEmail(@RequestBody CustomerIdTypeEmailTypeDTO customerIdEmailDTO)
	{
		System.out.println("Received:"+customerIdEmailDTO);
		
		
		
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.incrementResendCountByCustomerIdAndEmail(customerIdEmailDTO.getCustomerId(),customerIdEmailDTO.getCustomerType(), customerIdEmailDTO.getEmailType());
		
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/deleteByKey",method=RequestMethod.POST)
	public Boolean deleteByKey(@RequestBody EmailVerificationKey key)
	{
		customerEmailVerificationDetailsRepository.delete(key);
		
		return true;
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

	package com.projectx.data.controller.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.Date;

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

import com.projectx.data.config.Constants;
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
	Constants constants;
	
	@Autowired
	EmailVerificationDetailsRepository customerEmailVerificationDetailsRepository;
	
	
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
	public ResponseEntity<Integer> resetEmailHashAndEmailHashSentTime(@Valid @RequestBody UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.resetEmailHashAndEmailHashSentTime(dto.getCustomerId(),dto.getCustomerType(), dto.getEmailType(), dto.getEmailHash(), dto.getEmailHashSentTime(),
						dto.getResendCount(),new Date(),dto.getUpdatedBy(),dto.getUpdatedById());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
		
	}
	
	@RequestMapping(value="/incrementResendCountByCustomerIdAndEmail",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementResendCountByCustomerIdAndEmail(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO customerIdEmailDTO,
			BindingResult bindingResult)
	{
		ResponseEntity<Integer> result=null;
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.incrementResendCountByCustomerIdAndEmail(customerIdEmailDTO.getCustomerId(),customerIdEmailDTO.getCustomerType(), customerIdEmailDTO.getEmailType(),
						new Date(),customerIdEmailDTO.getRequestedBy(),customerIdEmailDTO.getRequestedById());
		
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
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			customerEmailVerificationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	
	
}

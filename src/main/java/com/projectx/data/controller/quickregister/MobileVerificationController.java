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
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;

@RestController
@RequestMapping(value="/customer/quickregister/mobileVerification")
public class MobileVerificationController {

	@Autowired
	Constants constants;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	

	@RequestMapping(value="/getMobileVerificationDetailsByCustomerIdAndMobile",method=RequestMethod.POST)
	public ResponseEntity<MobileVerificationDetails> getMobileVerificationDetailsByCustomerIdAndMobile(@RequestBody CustomerIdTypeMobileTypeUpdatedByDTO customerIdMobileDTO)
	{
		ResponseEntity<MobileVerificationDetails> result=null;
		
		MobileVerificationKey key=new MobileVerificationKey(customerIdMobileDTO.getCustomerId(), customerIdMobileDTO.getCustomerType(), customerIdMobileDTO.getMobileType());
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository
				.findOne(key);
		
		if(fetchedMobileVerificationDetails==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<MobileVerificationDetails>(fetchedMobileVerificationDetails, HttpStatus.FOUND);
		
		return result;
		
	}

	@RequestMapping(value="/getMobileVerificationDetailsByMobile",method=RequestMethod.POST)
	public ResponseEntity<MobileVerificationDetails> getMobileVerificationDetailsByMobile(@RequestBody MobileDTO mobileDTO)
	{
		ResponseEntity<MobileVerificationDetails> result=null;
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository
				.findByMobile(mobileDTO.getMobile());
		
		if(fetchedMobileVerificationDetails==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<MobileVerificationDetails>(fetchedMobileVerificationDetails, HttpStatus.FOUND);
		
		return result;
		
	}

	
	@RequestMapping(value="/updateMobilePinAndMobileVerificationAttemptsAndResendCount",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateMobilePinAndMobileVerificationAttemptsAndResendCount
			(@Valid @RequestBody UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.updateMobilePinAndMobileVerificationAttemptsAndResendCount(dto.getCustomerId(),dto.getCustomerType(), dto.getMobileType(),
						dto.getMobilePin(),dto.getMobileVerificationAttempts(),dto.getResendCount(),new Date(),dto.getUpdatedBy(),dto.getUpdatedById());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
		
	}
	
	@RequestMapping(value="/incrementMobileVerificationAttempts",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementMobileVerificationAttempts
			(@Valid @RequestBody CustomerIdTypeMobileTypeUpdatedByDTO mobileDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(),mobileDTO.getMobileType(),
						new Date(),mobileDTO.getRequestedBy(),mobileDTO.getRequestedById());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
		
	}
	
	@RequestMapping(value="/incrementResendCount",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementResendCount
			(@Valid @RequestBody CustomerIdTypeMobileTypeUpdatedByDTO mobileDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementResendCount(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(), mobileDTO.getMobileType(),new Date(),
						mobileDTO.getRequestedBy(),mobileDTO.getRequestedById());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
	}
	
	@RequestMapping(value="/deleteByKey",method=RequestMethod.POST)
	public ResponseEntity<Boolean> deleteByKey(@RequestBody MobileVerificationKey key)
	{
		ResponseEntity<Boolean> result=null;
		
		try{
			customerMobileVerificationDetailsRepository.delete(key);
			result=new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return result;
	}
		
	@RequestMapping(value="/getCount")
	public Integer mobileVerificationCount()
	{
		
		return new Integer((int) customerMobileVerificationDetailsRepository
				.count());
	}
	
	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting")
	public Boolean clearMobileVerificationForTesting()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			customerMobileVerificationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	
	
}

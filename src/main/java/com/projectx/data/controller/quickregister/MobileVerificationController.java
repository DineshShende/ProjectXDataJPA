package com.projectx.data.controller.quickregister;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;

@RestController
@RequestMapping(value="/customer/quickregister/mobileVerification")
public class MobileVerificationController {

	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	

	@RequestMapping(value="/saveMobileVerificationDetails",method=RequestMethod.POST)
	public MobileVerificationDetails saveMobileVerificationEntity(@RequestBody MobileVerificationDetails mobileVerificationDetails)
	{
		MobileVerificationDetails savedmobileVerificationDetails=customerMobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		return savedmobileVerificationDetails;
		
	}
	
	@RequestMapping(value="/getMobileVerificationDetailsByCustomerIdAndMobile",method=RequestMethod.POST)
	public ResponseEntity<MobileVerificationDetails> getMobileVerificationDetailsByCustomerIdAndMobile(@RequestBody CustomerIdTypeMobileTypeDTO customerIdMobileDTO)
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
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount
			(@RequestBody UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.updateMobilePinAndMobileVerificationAttemptsAndResendCount(dto.getCustomerId(),dto.getCustomerType(), dto.getMobileType(),
						dto.getMobilePin(),dto.getMobileVerificationAttempts(),dto.getResendCount());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/incrementMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer incrementMobileVerificationAttempts
			(@RequestBody CustomerIdTypeMobileTypeDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(),mobileDTO.getMobileType());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/incrementResendCount",method=RequestMethod.POST)
	public Integer incrementResendCount
			(@RequestBody CustomerIdTypeMobileTypeDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementResendCount(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(), mobileDTO.getMobileType());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/deleteByKey",method=RequestMethod.POST)
	public Boolean deleteByKey(@RequestBody MobileVerificationKey key)
	{
		customerMobileVerificationDetailsRepository.delete(key);
		
		return true;
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
		customerMobileVerificationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	
	/*
	@RequestMapping(value="/test")
	public MobileVerificationDetails getMobileVerificationDetails()
	{
		return new MobileVerificationDetails(new MobileVerificationKey(212L, 1, 1), 9960821869L, 10000, 0, 0, null, null, null);
	}
	*/
}

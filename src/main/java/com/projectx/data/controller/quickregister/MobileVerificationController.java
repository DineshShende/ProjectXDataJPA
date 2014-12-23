package com.projectx.data.controller.quickregister;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
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
	public MobileVerificationDetails getMobileVerificationDetailsByCustomerIdAndMobile(@RequestBody CustomerIdTypeMobileDTO customerIdMobileDTO)
	{
		MobileVerificationKey key=new MobileVerificationKey(customerIdMobileDTO.getCustomerId(), customerIdMobileDTO.getCustomerType(), customerIdMobileDTO.getMobile());
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository
				.findOne(key);
		
		if(fetchedMobileVerificationDetails==null)
			return new MobileVerificationDetails();
		
		return fetchedMobileVerificationDetails;
		
	}
	
	@RequestMapping(value="/updateMobilePinAndMobileVerificationAttemptsAndResendCount",method=RequestMethod.POST)
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount
			(@RequestBody UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.updateMobilePinAndMobileVerificationAttemptsAndResendCount(dto.getCustomerId(),dto.getCustomerType(), dto.getMobile(),
						dto.getMobilePin(),dto.getMobileVerificationAttempts(),dto.getResendCount());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/incrementMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer incrementMobileVerificationAttempts
			(@RequestBody CustomerIdTypeMobileDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(),mobileDTO.getMobile());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/incrementResendCount",method=RequestMethod.POST)
	public Integer incrementResendCount
			(@RequestBody CustomerIdTypeMobileDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementResendCount(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(), mobileDTO.getMobile());
		
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
		System.out.println("In controller:");
		System.out.println(customerMobileVerificationDetailsRepository.count());
		
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
	
	@RequestMapping(value="/test")
	public MobileVerificationDetails getMobileVerificationDetails()
	{
		return new MobileVerificationDetails(new MobileVerificationKey(212L, 1, 9960821869L), 1, 10000, 0, 0, null, null, null);
	}
	
}

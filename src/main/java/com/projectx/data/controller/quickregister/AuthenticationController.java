package com.projectx.data.controller.quickregister;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailPassword;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;


@RestController
@RequestMapping(value="/customer/quickregister/customerAuthentication")
public class AuthenticationController {
	
	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;

	@Value("${ZERO_COUNT}")
	private  Integer ZERO_COUNT;
	
	@RequestMapping(value="/saveLoginDetails",method=RequestMethod.POST)
	public AuthenticationDetails saveLoginDetails(@RequestBody AuthenticationDetails customerAuthenticationDetails)
	{
		return customerAuthenticationDetailsRepository.save(customerAuthenticationDetails);
	}
	
	@RequestMapping(value="/getLoginDetailsByCustomerIdType",method=RequestMethod.POST)
	public AuthenticationDetails getLoginDetailsByCustomerId(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerIdDTO.getCustomerId(), customerIdDTO.getCustomerType());
		
		AuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.findOne(key);
		
		if(fetchedEntity==null)
			return new AuthenticationDetails();
		
		return fetchedEntity;
	}
	
	
	@RequestMapping(value="/getLoginDetailsByEmail",method=RequestMethod.POST)
	public AuthenticationDetails getLoginDetailsByEmail(@RequestBody EmailDTO emailDTO)
	{
		Optional<AuthenticationDetails> fetchedEntity=customerAuthenticationDetailsRepository.findByEmail(emailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			return new AuthenticationDetails();
		
		return fetchedEntity.get();
	}
	
	@RequestMapping(value="/getLoginDetailsByMobile",method=RequestMethod.POST)
	public AuthenticationDetails getLoginDetailsByMobile(@RequestBody MobileDTO mobileDTO)
	{
		Optional<AuthenticationDetails> fetchedEntity=customerAuthenticationDetailsRepository.findByMobile(mobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			return new AuthenticationDetails();
		
		return fetchedEntity.get();
	}
	
	
	@RequestMapping(value="/updatePasswordEmailPasswordAndPasswordTypeAndCounts",method=RequestMethod.POST)
	public Integer updatePasswordAndPasswordTypeAndCounts(@RequestBody UpdatePasswordEmailPasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO)
	{
		
		
		Integer updateStatus=customerAuthenticationDetailsRepository
				.updatePasswordEmailPasswordAndPasswordTypeAndCounts(passwordAndPasswordTypeDTO.getCustomerId(),passwordAndPasswordTypeDTO.getCustomerType(),
						passwordAndPasswordTypeDTO.getPassword(),passwordAndPasswordTypeDTO.getEmailPassword(),
						passwordAndPasswordTypeDTO.getPasswordType(), ZERO_COUNT, ZERO_COUNT);
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/incrementResendCount",method=RequestMethod.POST)
	public Integer incrementResendCount(@RequestBody  CustomerIdTypeDTO customerIdDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementResendCount(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType());
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/incrementLastUnsucessfullAttempts",method=RequestMethod.POST)
	public Integer incrementLastUnsucessfullAttempts(@RequestBody  CustomerIdTypeDTO customerIdDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementLastUnsucessfullAttempts(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType());
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/getCount" )
	public Integer loginDetailsCount()
	{
		return new Integer((int) customerAuthenticationDetailsRepository.count());
	}

	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting")
	public Boolean clearLoginDetailsForTesting()
	{
		customerAuthenticationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	
	
	/*
	@RequestMapping(value="/updateEmailPasswordAndPasswordTypeAndCounts",method=RequestMethod.POST)
	public Integer updateEmailPasswordAndPasswordTypeAndCounts(@RequestBody UpdateEmailPassword emailPassword)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.updateEmailPasswordAndPasswordTypeAndCounts(emailPassword.getCustomerId(),emailPassword.getCustomerType(),emailPassword.getEmailPassword(),
						"Default", ZERO_COUNT, ZERO_COUNT);
		
		
		return updateStatus;
	}
	
	
	@RequestMapping(value="/test")
	public AuthenticationDetails returnAuthenticationDetails()
	{
		return new AuthenticationDetails(new AuthenticationDetailsKey(212L, 1), "dienshshe@gmail.com", 9960821869L, "123456", "Default","1234356ujhgfdghjh", 0, 0,new Date(),new Date(),"CUST_ONLINE");
		
		//return new AuthenticationDetails(key, "dienshshe@gmail.com", 9960821869L, password, passwordType, emailPassword, resendCount, lastUnsucessfullAttempts)
	}
	*/
}

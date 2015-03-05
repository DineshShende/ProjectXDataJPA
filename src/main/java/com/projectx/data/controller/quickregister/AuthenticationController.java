package com.projectx.data.controller.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.config.Constants;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;


@RestController
@RequestMapping(value="/customer/quickregister/customerAuthentication")
public class AuthenticationController {

	@Autowired
	Constants constants;
	
	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;

	@Value("${ZERO_COUNT}")
	private  Integer ZERO_COUNT;

	
	
	
	@RequestMapping(value="/saveLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> saveLoginDetails(@Valid @RequestBody AuthenticationDetails authenticationDetails,BindingResult resultValid)
	{
		if(resultValid.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(authenticationDetails);
			result=new ResponseEntity<AuthenticationDetails>(savedEntity, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getLoginDetailsByCustomerIdType",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> getLoginDetailsByCustomerId(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerIdDTO.getCustomerId(), customerIdDTO.getCustomerType());
		
		AuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.findOne(key);
		
		if(fetchedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<AuthenticationDetails>(fetchedEntity, HttpStatus.FOUND);
		
		return result;
	}
	
	
	@RequestMapping(value="/getLoginDetailsByEmail",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> getLoginDetailsByEmail(@RequestBody EmailDTO emailDTO)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		Optional<AuthenticationDetails> fetchedEntity=customerAuthenticationDetailsRepository.findByEmail(emailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<AuthenticationDetails>(fetchedEntity.get(), HttpStatus.FOUND);
		
		return result;
	}
	
	@RequestMapping(value="/getLoginDetailsByMobile",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> getLoginDetailsByMobile(@RequestBody MobileDTO mobileDTO)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		Optional<AuthenticationDetails> fetchedEntity=customerAuthenticationDetailsRepository.findByMobile(mobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<AuthenticationDetails>(fetchedEntity.get(), HttpStatus.FOUND);
		
		return result;
	}
	
	
	@RequestMapping(value="/updatePasswordEmailPasswordAndPasswordTypeAndCounts",method=RequestMethod.POST)
	public ResponseEntity<Integer> updatePasswordAndPasswordTypeAndCounts(@Valid @RequestBody UpdatePasswordEmailPasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerAuthenticationDetailsRepository
				.updatePasswordEmailPasswordAndPasswordTypeAndCounts(passwordAndPasswordTypeDTO.getCustomerId(),passwordAndPasswordTypeDTO.getCustomerType(),
						passwordAndPasswordTypeDTO.getPassword(),passwordAndPasswordTypeDTO.getEmailPassword(),
						passwordAndPasswordTypeDTO.getPasswordType(), ZERO_COUNT, ZERO_COUNT,new Date(),passwordAndPasswordTypeDTO.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
	}
	
	@RequestMapping(value="/incrementResendCount",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementResendCount(@Valid @RequestBody  CustomerIdTypeUpdatedByDTO customerIdDTO,BindingResult bindingResult)
	{
		ResponseEntity<Integer> result=null;
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementResendCount(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType(),new Date(),customerIdDTO.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
	}
	
	@RequestMapping(value="/incrementLastUnsucessfullAttempts",method=RequestMethod.POST)
	public ResponseEntity<Integer> incrementLastUnsucessfullAttempts(@Valid @RequestBody  CustomerIdTypeUpdatedByDTO customerIdDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementLastUnsucessfullAttempts(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType(),new Date(),customerIdDTO.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
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
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			customerAuthenticationDetailsRepository.deleteAll();
		
		return true;
	}
	
	//***********************Highly Dangerous***************************************/
	

}

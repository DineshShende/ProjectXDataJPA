package com.projectx.data.controller.quickregister;

import java.util.List;
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
import com.projectx.data.domain.commdto.ResponseDTO;
import com.projectx.data.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;

import static com.projectx.data.config.Constants.*;
@RestController
@RequestMapping(value="/customer/quickregister")
public class QuickRegisterController {

	@Autowired
	Constants constants;
	
	@Autowired
	QuickRegisterRepository customerQuickRegisterRepository;
	
	@Value("${MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED}")
	private String MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED;
	
	@Value("${EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED}")
	private String EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED;
	
	@Value("${ALREADY_REPORTED}")
	private String ALREADY_REPORTED;

		
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuickRegisterEntity>> getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		ResponseEntity<ResponseDTO<QuickRegisterEntity>> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity= customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>
											("QUICKREG_ENTITY_NOT_FOUND_BY_MOBILE", null),HttpStatus.OK);
		else
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>
			("", fetchedEntity.get()), HttpStatus.OK);
		
		return result;
	}
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByEmail",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuickRegisterEntity>> getCustomerQuickRegisterEntityByEmail(@RequestBody EmailDTO getByEmailDTO)
	{
		ResponseEntity<ResponseDTO<QuickRegisterEntity>> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByEmail(getByEmailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>("QUICKREG_ENTITY_NOT_FOUND_BY_MOBILE",
					null),HttpStatus.OK);
		else
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>("",
					fetchedEntity.get()),HttpStatus.OK);
		
		return result;
	}
	
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByMobile",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuickRegisterEntity>> getCustomerQuickRegisterEntityByMobile(@RequestBody MobileDTO getByMobileDTO)
	{
		ResponseEntity<ResponseDTO<QuickRegisterEntity>> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByMobile(getByMobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>("QUICKREG_ENTITY_NOT_FOUND_BY_MOBILE",
					null),HttpStatus.OK);
		else
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>("",
					fetchedEntity.get()),HttpStatus.OK);
		
		return result;
	}
	
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Integer>> updateMobileVerificationStatus(@Valid @RequestBody UpdateEmailMobileVerificationStatus updateStatus,
			BindingResult bindingResult) throws InterruptedException
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>("VALIDATION_FAILED",null),HttpStatus.OK);
		
		ResponseEntity<ResponseDTO<Integer>> result=null;
		
		try{
			
			Integer status=customerQuickRegisterRepository.updateMobileVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
					updateStatus.getUpdateTime(), updateStatus.getUpdatedBy(),updateStatus.getUpdatedById());

			result=new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>("",status),HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			errorMessage.append(ALREADY_REPORTED);
			
			return  new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>(errorMessage.toString(),null),
					HttpStatus.ALREADY_REPORTED);
		}
		
		
		
		return result;
	}
	
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Integer>> updateEmailVerificationStatus(@Valid @RequestBody UpdateEmailMobileVerificationStatus updateStatus,
			BindingResult bindingResult) throws InterruptedException
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>("VALIDATION_FAILED",null),HttpStatus.OK);
		
		ResponseEntity<ResponseDTO<Integer>> result=null;
		
		try{
			Integer status=customerQuickRegisterRepository.updateEmailVerificationStatus(updateStatus.getCustomerId(), 
					updateStatus.getStatus(),updateStatus.getUpdateTime(), updateStatus.getUpdatedBy(),updateStatus.getUpdatedById());
			
			result=new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>("",status),HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			errorMessage.append(ALREADY_REPORTED);
			
			return  new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>(errorMessage.toString(),null),
					HttpStatus.ALREADY_REPORTED);
			
		}
		
		
		
		return result;
	}

	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Integer getCount()
	{
		Integer count=(int)customerQuickRegisterRepository.count();
		
		return count;
	}
	
	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting",method=RequestMethod.GET)
	public Boolean clearTableForTesting()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
		 customerQuickRegisterRepository.deleteAll();
 
		 
		 return true;
	}
	//***********************Highly Dangerous***************************************/
	
	/*
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuickRegisterEntity>> saveNewCustomer(@Valid @RequestBody  QuickRegisterEntity quickRegisterEntity,
			BindingResult result)
	{
		ResponseEntity<ResponseDTO<QuickRegisterEntity>> resultResponse=null;
		
		if(result.hasErrors())
		{
			resultResponse=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>
									(new ResponseDTO<QuickRegisterEntity>("VALIDATION_FAILED",null),HttpStatus.OK);
			return resultResponse;
		}
		
		try{
			QuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(quickRegisterEntity);
			resultResponse=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>
									(new ResponseDTO<QuickRegisterEntity>("",savedEntity), HttpStatus.CREATED);
			
		}catch(DataIntegrityViolationException e)
		{
			resultResponse=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return resultResponse;
				
	}
	*/
	
	
	
}

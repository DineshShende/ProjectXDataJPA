package com.projectx.data.controller.quickregister;

import java.util.List;
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

import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;


@RestController
@RequestMapping(value="/customer/quickregister")
public class QuickRegisterController {
	
	@Autowired
	QuickRegisterRepository customerQuickRegisterRepository;
	
       
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> saveNewCustomer(@Valid @RequestBody  QuickRegisterEntity quickRegisterEntity,
			BindingResult result)
	{
		ResponseEntity<QuickRegisterEntity> resultResponse=null;
		
		if(result.hasErrors())
		{
			resultResponse=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			return resultResponse;
		}
		
		try{
			QuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(quickRegisterEntity);
			resultResponse=new ResponseEntity<QuickRegisterEntity>(savedEntity, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			resultResponse=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return resultResponse;
				
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<List<QuickRegisterEntity>> getAllCustomer()
	{
		return new  ResponseEntity<List<QuickRegisterEntity>>(customerQuickRegisterRepository.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		ResponseEntity<QuickRegisterEntity> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity= customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<QuickRegisterEntity>(fetchedEntity.get(), HttpStatus.FOUND);
		
		return result;
	}
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByEmail",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> getCustomerQuickRegisterEntityByEmail(@RequestBody EmailDTO getByEmailDTO)
	{
		ResponseEntity<QuickRegisterEntity> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByEmail(getByEmailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<QuickRegisterEntity>(fetchedEntity.get(), HttpStatus.FOUND);
		
		return result;
	}
	
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByMobile",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> getCustomerQuickRegisterEntityByMobile(@RequestBody MobileDTO getByMobileDTO)
	{
		ResponseEntity<QuickRegisterEntity> result=null;
		
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByMobile(getByMobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<QuickRegisterEntity>(fetchedEntity.get(), HttpStatus.FOUND);
		
		return result;
	}
	
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateMobileVerificationStatus(@Valid @RequestBody UpdateEmailMobileVerificationStatus updateStatus,
			BindingResult bindingResult) throws InterruptedException
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer status=customerQuickRegisterRepository.updateMobileVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(status, HttpStatus.OK);
		
		return result;
	}
	
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateEmailVerificationStatus(@RequestBody UpdateEmailMobileVerificationStatus updateStatus) throws InterruptedException
	{
		ResponseEntity<Integer> result=null;
		
		Integer status=customerQuickRegisterRepository.updateEmailVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
		result=new ResponseEntity<Integer>(status, HttpStatus.OK);
		
		return result;
	}
	
	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting",method=RequestMethod.GET)
	public Boolean clearTableForTesting()
	{
		//System.out.println("Here");
		 customerQuickRegisterRepository.deleteAll();
		//System.out.println(customerQuickRegisterRepository.count()); 
		 
		 return true;
	}
	//***********************Highly Dangerous***************************************/
	
	
}

package com.projectx.data.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.EmailDTO;
import com.projectx.rest.domain.MobileDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdateStatusWithCustomerIdDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;

@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickRegisterEntity saveNewCustomer(@RequestBody CustomerQuickRegisterDTO customerDTO)
	{
		CustomerQuickRegisterEntity customerEntity=customerDTO.toCustomerQuickRegisterEntity();
		
		return customerQuickRegisterRepository.save(customerEntity);
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<CustomerQuickRegisterEntity> getAllCustomer()
	{
		return customerQuickRegisterRepository.findAll(); 
	}
	
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		CustomerQuickRegisterEntity fetchedEntity= customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
		
		if(fetchedEntity==null)
			return new CustomerQuickRegisterEntity();
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/getEmailCount",method=RequestMethod.POST)
	public Integer getEmailCount(@RequestBody EmailDTO emailDTO)
	{
		return customerQuickRegisterRepository.countByEmail(emailDTO.getEmail());
	}
	
	@RequestMapping(value="/getMobileCount",method=RequestMethod.POST)
	public Integer getMobileCount(@RequestBody MobileDTO mobileDTO)
	{
		return customerQuickRegisterRepository.countByMobile(mobileDTO.getMobile());
	}
	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public Integer verifyEmailHash(@RequestBody VerifyEmailDTO emailDTO)
	{
		return customerQuickRegisterRepository.countByCustomerIdAndEmailHash(emailDTO.getCustomerId(), emailDTO.getEmailHash());
	}


	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public Integer verifyEmailHash(@RequestBody VerifyMobileDTO mobileDTO)
	{
		return customerQuickRegisterRepository.countByCustomerIdAndMobilePin(mobileDTO.getCustomerId(), mobileDTO.getMobilePin());
	}


	@RequestMapping(value="/getStatusByCustomerId",method=RequestMethod.POST)
	public String getStatusByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{		 		 
		String status=customerQuickRegisterRepository.fetchStatusByCustomerId(customerDTO.getCustomerId());
		
		return status;
				
	}
			
	@RequestMapping(value="/updateStatusByCustomerId",method=RequestMethod.POST)
	public Integer updateStatusByCustomerId(@RequestBody UpdateStatusWithCustomerIdDTO updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateStatusByCustomerId(updateStatus.getCustomerId(),updateStatus.getStatus(),updateStatus.getStatusChangeTime());
				
		return result;
	}
		
	
	@RequestMapping(value="/updateMobilePin",method=RequestMethod.POST)
	public Integer updateMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		return customerQuickRegisterRepository.updateMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getMobilePin(),updateMobilePin.getUpdateTime());
	}
	
	@RequestMapping(value="/updateEmailHash",method=RequestMethod.POST)
	public Integer updateEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		return customerQuickRegisterRepository.updateEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getEmailHash(),updateEmailHash.getUpdateTime());
	}
	
	@RequestMapping(value="/getMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer getMobileVerificationAttempts(@RequestBody CustomerIdDTO customerId)
	{
		return customerQuickRegisterRepository.getMobileVerificationAttempts(customerId.getCustomerId());
	}
	
	@RequestMapping(value="/incrementMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer incrementMobileVerificationAttempts(@RequestBody CustomerIdDTO customerId)
	{
		return customerQuickRegisterRepository.incrementMobileVerificationAttempts(customerId.getCustomerId());
	}
	
	@RequestMapping(value="/customer")
	public CustomerQuickRegisterEntity returnCustomer()
	{
		
		return standardEmailMobileCustomer();
	}
	
	//***********************Highly Dangerous***************************************/
	
	@RequestMapping(value="/clearForTesting")
	public Boolean clearTableForTesting()
	{
		 customerQuickRegisterRepository.clearTestData();
		 
		 return true;
	}
	//***********************Highly Dangerous***************************************/
	
	
}

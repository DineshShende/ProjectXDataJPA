package com.projectx.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.EmailDTO;
import com.projectx.rest.domain.MobileDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdateStatusWithCustomerIdDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;


@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickRegisterEntity saveNewCustomer(@RequestBody CustomerQuickRegisterEntity customerQuickRegisterEntity)
	{
		return customerQuickRegisterRepository.save(customerQuickRegisterEntity);
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<CustomerQuickRegisterEntity> getAllCustomer()
	{
		return customerQuickRegisterRepository.findAll(); 
	}
	
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		return customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
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

	//---------
	@RequestMapping(value="/getStatusByCustomerId",method=RequestMethod.POST)
	public String getStatusByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		 return customerQuickRegisterRepository.fetchStatusByCustomerId(customerDTO.getCustomerId());
	}
			
	@RequestMapping(value="/updateStatusByCustomerId",method=RequestMethod.POST)
	public Integer updateStatusByCustomerId(@RequestBody UpdateStatusWithCustomerIdDTO updateStatus)
	{
		return customerQuickRegisterRepository.updateStatusByCustomerId(updateStatus.getCustomerId(),updateStatus.getStatus());
	}
		
	
	@RequestMapping(value="/updateMobilePin",method=RequestMethod.POST)
	public Integer updateMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		return customerQuickRegisterRepository.updateMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getMobilePin());
	}
	
	@RequestMapping(value="/updateEmailHash",method=RequestMethod.POST)
	public Integer updateEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		return customerQuickRegisterRepository.updateEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getEmailHash());
	}
	
	
	
	
	/*
	@RequestMapping(value="/updateStatusByCustomerId",method=RequestMethod.POST)
	public Integer updateCustomerStatusByCustomerId(@RequestBody UpdateStatusByCustomerIdDTO mobileDTO)
	{
	
	}
		
	@RequestMapping(value="/updateStatusByMobile",method=RequestMethod.POST)
	public Integer updateCustomerStatusByMobile(@RequestBody UpdateStatusByMobileDTO mobileDTO)
	{
		//System.out.println(mobileDTO.getMobile()+" "+mobileDTO.getStatus());
			
		return customerQuickRegisterRepository.updateStatusByMobile(mobileDTO.getMobile(), mobileDTO.getStatus());
	}
	
	@RequestMapping(value="/updateStatusByEmail",method=RequestMethod.POST)
	public Integer updateCustomerStatusByEmail(@RequestBody UpdateStatusByEmailDTO emailDTO)
	{
		//System.out.println(emailDTO.getEmail()+" "+emailDTO.getStatus());
			
		return customerQuickRegisterRepository.updateStatusByEmail(emailDTO.getEmail(), emailDTO.getStatus());
	}
	
	@RequestMapping(value="/updateMobilePin")
	public Integer updateMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		return  customerQuickRegisterRepository.updateMobilePin(updateMobilePin.getMobile(), updateMobilePin.getMobilePin());
	}
	
	@RequestMapping(value="/updateEmailHash")
	public Integer updateMobilePin(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		return  customerQuickRegisterRepository.updateEmailHash(updateEmailHash.getEmail(), updateEmailHash.getEmailHash());
	}
	*/
		
	/*
	@RequestMapping(value="/getStatusByMobile",method=RequestMethod.POST)
	public String getStatusByEmail(@RequestBody GetByMobileDTO mobileDTO)
	{
		 return customerQuickRegisterRepository.fetchStatusByMobile(mobileDTO.getMobile());
	}
	*/
	/*
	@RequestMapping(value="/getByEmail",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByEmail(@RequestBody GetByEmailDTO emailDTO)
	{
		 return customerQuickRegisterRepository.findByEmail(emailDTO.getEmail());
	}
	*/
}

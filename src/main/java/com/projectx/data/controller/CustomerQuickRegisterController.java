package com.projectx.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.domain.GetByEmailDTO;
import com.projectx.rest.domain.GetByMobileDTO;
import com.projectx.rest.domain.UpdateStatusByEmailDTO;
import com.projectx.rest.domain.UpdateStatusByMobileDTO;


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
	

	@RequestMapping(value="/getStatusByEmail",method=RequestMethod.POST)
	public String getStatusByEmail(@RequestBody GetByEmailDTO emailDTO)
	{
		 return customerQuickRegisterRepository.fetchStatusByEmail(emailDTO.getEmail());
	}
	
	@RequestMapping(value="/getStatusByMobile",method=RequestMethod.POST)
	public String getStatusByEmail(@RequestBody GetByMobileDTO mobileDTO)
	{
		 return customerQuickRegisterRepository.fetchStatusByMobile(mobileDTO.getMobile());
	}
	
	@RequestMapping(value="/getByEmail",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByEmail(@RequestBody GetByEmailDTO emailDTO)
	{
		 return customerQuickRegisterRepository.findByEmail(emailDTO.getEmail());
	}
	
	@RequestMapping(value="/getByMobile",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByMobile(@RequestBody GetByMobileDTO mobileDTO)
	{
			
		return customerQuickRegisterRepository.findByMobile(mobileDTO.getMobile());
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
		System.out.println(emailDTO.getEmail()+" "+emailDTO.getStatus());
			
		return customerQuickRegisterRepository.updateStatusByEmail(emailDTO.getEmail(), emailDTO.getStatus());
	}
	
	
	//public Integer updateMobilePin()
	
	//public Integer updateEmailHash()
	
	
	
}

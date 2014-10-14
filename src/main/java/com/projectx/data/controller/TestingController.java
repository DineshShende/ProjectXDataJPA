package com.projectx.data.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO;


@RestController
public class TestingController {

	
	@RequestMapping(value="/testing/save")
	public Integer saveTesting()
	{
		RestTemplate restTemplate=new RestTemplate();
		
		Integer result= restTemplate.postForObject("http://localhost:9090/customer/quickregister/updateStatusByCustomerId",new UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO(2L, "",new Date(),0) , Integer.class);
		
		System.out.println(result);
		
		return result;
	}
	
	
}

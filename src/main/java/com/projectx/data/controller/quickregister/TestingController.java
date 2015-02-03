package com.projectx.data.controller.quickregister;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterDTO;

/*
import com.projectx.data.domain.quickregister.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;

*/

@RestController
public class TestingController {

	@RequestMapping(value="/testing/save/test")
	public ResponseEntity<VendorDetails> saveTestingTest() 
	{

		VendorDetails dto=new VendorDetails(1L, "Dinesh", "Shende", new Date(), new Address(), 23L, true, "cv", true, "hg", new Date(),new Date(), "hf"); 
		
		ResponseEntity<VendorDetails> result=new ResponseEntity<VendorDetails>(dto, HttpStatus.OK);
		try
		{
		throw new Exception();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
	}
	
	/*
	@RequestMapping(value="/testing/save")
	public ResponseEntity<CustomerQuickRegisterEntity> saveTesting()
	{
		RestTemplate restTemplate=new RestTemplate();
		
		System.out.println("In Test");
		
		//restTemplate.
		
		ResponseEntity<CustomerQuickRegisterEntity> response=restTemplate.postForEntity("http://localhost:9090/testing/save/test", standardEmailMobileCustomer(), CustomerQuickRegisterEntity.class);
		
		//CustomerQuickRegisterEntity result= restTemplate.postForEntity("http://localhost:9090/customer/quickregister/responseEntity",standardEmailMobileCustomer() ,CustomerQuickRegisterEntity.class);
		
		System.out.println(response);
		
		return response;
	}
	
	@RequestMapping(value="/testing/save/test")
	public ResponseEntity<CustomerQuickRegisterEntity> saveTestingTest(@RequestBody CustomerQuickRegisterEntity value)
	{
		RestTemplate restTemplate=new RestTemplate();
		
		//restTemplate.
		
		System.out.println("In Test Test");
		
		ResponseEntity<CustomerQuickRegisterEntity> response=restTemplate.postForEntity("http://localhost:9090/customer/quickregister/responseEntity", value, CustomerQuickRegisterEntity.class);
		
		//CustomerQuickRegisterEntity result= restTemplate.postForEntity("http://localhost:9090/customer/quickregister/responseEntity",standardEmailMobileCustomer() ,CustomerQuickRegisterEntity.class);
		
		System.out.println(response);
		
		return response;
	}
	
	*/
	
}

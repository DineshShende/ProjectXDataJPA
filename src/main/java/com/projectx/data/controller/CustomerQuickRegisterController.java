package com.projectx.data.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerAuthenticationDetails;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.data.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.GetByEmailDTO;
import com.projectx.rest.domain.GetByMobileDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.UpdateEmailHashAndMobilePinSentTimeDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO;

import static com.projectx.data.fixtures.CustomerQuickRegisterEntityDataFixture.*;




@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;  
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickRegisterEntity saveNewCustomer(@RequestBody CustomerQuickRegisterEntity customerEntity)
	{
		//CustomerQuickRegisterEntity customerEntity=customerDTO.toCustomerQuickRegisterEntity();
		
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
	public Integer getEmailCount(@RequestBody GetByEmailDTO emailDTO)
	{
		return customerQuickRegisterRepository.countByEmail(emailDTO.getEmail());
	}
	
	@RequestMapping(value="/getMobileCount",method=RequestMethod.POST)
	public Integer getMobileCount(@RequestBody GetByMobileDTO mobileDTO)
	{
		return customerQuickRegisterRepository.countByMobile(mobileDTO.getMobile());
	}

	
	@RequestMapping(value="/updateStatusAndMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer updateStatusAndMobileVerificationAttempts(@RequestBody UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(updateStatus.getCustomerId(),updateStatus.getStatus(),updateStatus.getStatusChangeTime(),updateStatus.getMobileVerificationAttempts());
				
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
	/*
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public Integer updatePassword(@RequestBody UpdatePasswordAndPasswordTypeDTO updatePassword)
	{
		return customerQuickRegisterRepository.updatePassword(updatePassword.getCustomerId(), updatePassword.getPassword(),updatePassword.getPasswordType());
	}
*/
	@RequestMapping(value="/updateEmailHashAndMobilePinSentTime",method=RequestMethod.POST)
	public Integer updateEmailHashAndMobilePinSentTime(@RequestBody UpdateEmailHashAndMobilePinSentTimeDTO timeDTO)
	{
		
		//System.out.println(timeDTO);
		return customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(timeDTO.getCustomerId(), timeDTO.getEmailSentTime(), timeDTO.getMobilePinSentTime());
	}

	//---------
	@RequestMapping(value="/saveLoginDetails",method=RequestMethod.POST)
	public CustomerAuthenticationDetails saveLoginDetails(@RequestBody CustomerAuthenticationDetails customerAuthenticationDetails)
	{
		return customerAuthenticationDetailsRepository.save(customerAuthenticationDetails);
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public Integer updatePassword(@RequestBody UpdatePasswordAndPasswordTypeDTO updatePasswordAndPasswordTypeDTO)
	{
		return customerAuthenticationDetailsRepository.updatePasswordAndPasswordType(updatePasswordAndPasswordTypeDTO.getCustomerId(),
												updatePasswordAndPasswordTypeDTO.getPassword(), updatePasswordAndPasswordTypeDTO.getPasswordType());
	}
	
	@RequestMapping(value="/verifyLoginDetails")
	public CustomerAuthenticationDetails verifyLoginDetails(@RequestBody LoginVerificationDTO loginVerificationDTO)
	{
		CustomerAuthenticationDetails fetchedDetails= customerAuthenticationDetailsRepository.loginVerification(loginVerificationDTO.getEmail(), loginVerificationDTO.getMobile(),
																				loginVerificationDTO.getPassword());
		
		if(fetchedDetails==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedDetails;
																				
	}
	//***********************Highly Dangerous***************************************/
	
	@RequestMapping(value="/clearForTesting")
	public Boolean clearTableForTesting()
	{
		 customerQuickRegisterRepository.clearTestData();
		 
		 return true;
	}
	//***********************Highly Dangerous***************************************/
	
	
/*	
	@RequestMapping(value="/customer")
	public CustomerQuickRegisterEntity returnCustomer()
	{
		RestTemplate restTemplate=new RestTemplate();
		
		System.out.println("Here");
		
		CustomerQuickRegisterEntity savedEntity=restTemplate.postForObject("http://localhost:9090/customer/quickregister", 
				standardEmailMobileCustomer(), CustomerQuickRegisterEntity.class);
			
		return savedEntity;
	}
	
*/
	/*
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
	*/
	
	/*
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
	*/	
}

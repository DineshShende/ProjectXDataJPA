package com.projectx.data.controller.quickregister;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.completeregister.DocumetDetailsRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.UpdateEmailPassword;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;


@RestController
@RequestMapping(value="/customer/quickregister")
public class QuickRegisterController {
	
	@Autowired
	QuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
    @Qualifier("customerQuickRegisterValidator")
    private Validator validator;
 	
	@InitBinder("customerQuickRegisterEntity")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

       
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> saveNewCustomer(@Valid @RequestBody  QuickRegisterEntity customerEntity,BindingResult result)
	{
		ResponseEntity<QuickRegisterEntity> resultResponse=null;
		
		if(result.hasErrors())
		{
			resultResponse=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			return resultResponse;
		}
		
		return new ResponseEntity<QuickRegisterEntity>(customerQuickRegisterRepository.save(customerEntity), HttpStatus.CREATED);
				
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<QuickRegisterEntity> getAllCustomer()
	{
		return customerQuickRegisterRepository.findAll(); 
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
	public Integer updateMobileVerificationStatus(@RequestBody UpdateEmailMobileVerificationStatus updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateMobileVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
		return result;
	}
	
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public Integer updateEmailVerificationStatus(@RequestBody UpdateEmailMobileVerificationStatus updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateEmailVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
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

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
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.UpdateEmailPassword;
import com.projectx.rest.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;

import static com.projectx.data.fixtures.quickregister.CustomerQuickRegisterEntityDataFixture.*;

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

    private final Integer ZERO_COUNT=0;

    
	@RequestMapping(method=RequestMethod.POST)
	public QuickRegisterEntity saveNewCustomer(@Valid @RequestBody  QuickRegisterEntity customerEntity,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new QuickRegisterEntity();
		}
		return customerQuickRegisterRepository.save(customerEntity);
				
	}
	
	@RequestMapping(value="/responseEntity",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> saveNewCustomerWithResponseEntity(@Valid @RequestBody  QuickRegisterEntity customerEntity,BindingResult result)
	{
		QuickRegisterEntity newEntity;
		
		if(result.hasErrors())
		{
			newEntity=new QuickRegisterEntity();
			return new ResponseEntity<QuickRegisterEntity>(newEntity, HttpStatus.CONFLICT);
		}
		else
		{
			newEntity=customerQuickRegisterRepository.save(customerEntity);
			return new ResponseEntity<QuickRegisterEntity>(newEntity, HttpStatus.CREATED);
		}
		
				
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<QuickRegisterEntity> getAllCustomer()
	{
		return customerQuickRegisterRepository.findAll(); 
	}
	
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public QuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		
		Optional<QuickRegisterEntity> fetchedEntity= customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
		
		if(!fetchedEntity.isPresent())
			return new QuickRegisterEntity();
		
		return fetchedEntity.get();
	}
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByEmail",method=RequestMethod.POST)
	public QuickRegisterEntity getCustomerQuickRegisterEntityByEmail(@RequestBody EmailDTO getByEmailDTO)
	{
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByEmail(getByEmailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			return new QuickRegisterEntity();
		
		return fetchedEntity.get();
	}
	
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByMobile",method=RequestMethod.POST)
	public QuickRegisterEntity getCustomerQuickRegisterEntityByMobile(@RequestBody MobileDTO getByMobileDTO)
	{
		Optional<QuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByMobile(getByMobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			return new QuickRegisterEntity();
		
		return fetchedEntity.get();
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
	
	
	@RequestMapping(value="/test")
	public MobileVerificationDetails test()
	{
		return new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_MOBILE), 2, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	@RequestMapping(value="/customer")
	public QuickRegisterEntity show()
	{
		return standardEmailMobileCustomer();
				
	}
	
	
}

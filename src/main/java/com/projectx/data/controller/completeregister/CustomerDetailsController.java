package com.projectx.data.controller.completeregister;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.config.Constants;
import com.projectx.data.domain.commdto.ResponseDTO;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.data.util.validator.AuthenticationDetailsValidator;
import com.projectx.data.util.validator.CustomerDetailsValidator;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

import static com.projectx.data.config.Constants.*;


@RestController
@RequestMapping(value="/customer/completeregister")
public class CustomerDetailsController {

	@Autowired
	Constants constants;
	
	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Value("${MOBILE_ALREADY_REPORTED}")
	private String MOBILE_ALREADY_REPORTED;
	
	@Value("${EMAIL_ALREADY_REPORTED}")
	private String EMAIL_ALREADY_REPORTED;
	
	@Value("${SEC_MOBILE_ALREADY_REPORTED}")
	private String SEC_MOBILE_ALREADY_REPORTED;
	
	@Value("${MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED}")
	private String MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED;
	
	@Value("${EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED}")
	private String EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED;
	
	@Value("${ALREADY_REPORTED}")
	private String ALREADY_REPORTED;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CustomerDetails>> saveCustomerDetails(@Valid @RequestBody CustomerDetails customerDetails,BindingResult resultValid)
	{
		if(resultValid.hasErrors())
		{
			for(FieldError error:resultValid.getFieldErrors())
			{
				System.out.println(error.getField());
				System.out.println(error.getDefaultMessage());
			}
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		ResponseEntity<ResponseDTO<CustomerDetails>> result=null;
		
		try{		
		
			CustomerDetails details=customerDetailsCustomRepository.save(customerDetails);
			result=new ResponseEntity<ResponseDTO<CustomerDetails>>(new ResponseDTO<CustomerDetails>("",details), HttpStatus.CREATED);
		
		}
		catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			if(mobileVerificationDetailsRepository.findByMobile(customerDetails.getMobile())!=null)
			{
				errorMessage.append(MOBILE_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByMobile(customerDetails.getMobile());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			if(emailVerificationDetailsRepository.findByEmail(customerDetails.getEmail())!=null)
			{
				errorMessage.append(EMAIL_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByEmail(customerDetails.getEmail());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			
			if(mobileVerificationDetailsRepository.findByMobile(customerDetails.getSecondaryMobile())!=null)
			{
				errorMessage.append(SEC_MOBILE_ALREADY_REPORTED);
			}
			
			errorMessage.append(ALREADY_REPORTED);
			
			result=new ResponseEntity<ResponseDTO<CustomerDetails>>(new ResponseDTO<CustomerDetails>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED); 
		}
		return result;
	}
	
	@RequestMapping(value="/{customerId}",method=RequestMethod.GET)
	public ResponseEntity<CustomerDetails> findOne(@PathVariable("customerId")Long customerId )
	{
		ResponseEntity<CustomerDetails> result=null;
		
		CustomerDetails fetchedEntity=customerDetailsCustomRepository.findOne(customerId);
		
		if(fetchedEntity!=null)
			result=new ResponseEntity<CustomerDetails>(fetchedEntity, HttpStatus.FOUND);
		else
			result=new ResponseEntity<CustomerDetails>(HttpStatus.NO_CONTENT);
		
		
		return result;
	}
	
		
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Long count()
	{
		return customerDetailsCustomRepository.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			customerDetailsCustomRepository.deleteAll();
		
		return true;
	}
	

}

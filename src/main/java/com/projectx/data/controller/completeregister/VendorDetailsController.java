package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.config.Constants;
import com.projectx.data.domain.commdto.ResponseDTO;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	Constants constants;
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsRepository;
	
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
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VendorDetails>> save(@Valid @RequestBody VendorDetails vendorDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<VendorDetails>> result=null;
		
		try{
			VendorDetails savedEntity=vendorDetailsRepository.save(vendorDetails);
			result=new ResponseEntity<ResponseDTO<VendorDetails>>(new ResponseDTO<VendorDetails>("",savedEntity), HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			if(mobileVerificationDetailsRepository.findByMobile(vendorDetails.getMobile())!=null)
			{
				errorMessage.append(MOBILE_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByMobile(vendorDetails.getMobile());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			if(emailVerificationDetailsRepository.findByEmail(vendorDetails.getEmail())!=null)
			{
				errorMessage.append(EMAIL_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByEmail(vendorDetails.getEmail());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
						
			if(mobileVerificationDetailsRepository.findByMobile(vendorDetails.getSecondaryMobile())!=null)
			{
				errorMessage.append(SEC_MOBILE_ALREADY_REPORTED);
			}
			
			errorMessage.append(ALREADY_REPORTED);
			
			return new ResponseEntity<ResponseDTO<VendorDetails>>(new ResponseDTO<VendorDetails>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED); 
	
		}
		
		return result;
	}
	
		
	@RequestMapping(value="/getById/{vendorId}")
	public ResponseEntity<VendorDetails> findOne(@PathVariable("vendorId") Long vendorId)
	{
		ResponseEntity<VendorDetails> result=null;
		
		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		if(fetchedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<VendorDetails>(fetchedEntity, HttpStatus.FOUND);
		
		return result;
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer fetchedResult=vendorDetailsRepository.count();
		
		return fetchedResult;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			vendorDetailsRepository.deleteAll();
		
		return true;
	}
}

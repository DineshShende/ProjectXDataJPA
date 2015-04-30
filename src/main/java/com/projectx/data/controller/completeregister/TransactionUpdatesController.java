package com.projectx.data.controller.completeregister;



import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.commdto.ResponseDTO;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;

@RestController
@RequestMapping(value="/transactional")
public class TransactionUpdatesController {

	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsCustomRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
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

	
	@RequestMapping(value="/updateMobileInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updateMobileInDetailsEnityAndAuthenticationDetails(@Valid @RequestBody CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			Boolean updatedStatus=transactionalUpdatesRepository
				.updateMobileInDetailsEnityAndAuthenticationDetails(customerIdTypeMobileTypeDTO.getCustomerId(), customerIdTypeMobileTypeDTO.getCustomerType(),
						customerIdTypeMobileTypeDTO.getMobileType(),customerIdTypeMobileTypeDTO.getRequestedBy(),customerIdTypeMobileTypeDTO.getRequestedById());
			
			result=new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",updatedStatus), HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			errorMessage.append(ALREADY_REPORTED);
			
			return  new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(errorMessage.toString(),null),
					HttpStatus.ALREADY_REPORTED);
		}
		return result;
	}
	
	@RequestMapping(value="/updateEmailInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updateEmailInDetailsEnityAndAuthenticationDetails(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			Boolean updatedStatus=transactionalUpdatesRepository
				.updateEmailInDetailsEnityAndAuthenticationDetails(customerIdTypeEmailTypeDTO.getCustomerId(), customerIdTypeEmailTypeDTO.getCustomerType(),
						customerIdTypeEmailTypeDTO.getEmailType(),customerIdTypeEmailTypeDTO.getRequestedBy(),customerIdTypeEmailTypeDTO.getRequestedById());
		 result=new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",updatedStatus), HttpStatus.OK);
		 
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			errorMessage.append(ALREADY_REPORTED);
			
			return  new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(errorMessage.toString(),null),
					HttpStatus.ALREADY_REPORTED);
		}
		return result;
	}
	
	@RequestMapping(value="/saveNewQuickRegisterEntity",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>> saveNewQuickRegisterEntity(@Valid @RequestBody QuickRegisterEntity quickRegisterEntity,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>> result=null;
		
		try
		{
			CustomerQuickRegisterEmailMobileVerificationEntity returnEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(quickRegisterEntity);
			result=new ResponseEntity<ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>>(
					new ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>("",returnEntity), HttpStatus.CREATED);
			
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			if(mobileVerificationDetailsRepository.findByMobile(quickRegisterEntity.getMobile())!=null)
			{
				errorMessage.append(MOBILE_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByMobile(quickRegisterEntity.getMobile());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			if(emailVerificationDetailsRepository.findByEmail(quickRegisterEntity.getEmail())!=null)
			{
				errorMessage.append(EMAIL_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByEmail(quickRegisterEntity.getEmail());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			errorMessage.append(ALREADY_REPORTED);
			
			result=new ResponseEntity<ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>>(
					new ResponseDTO<CustomerQuickRegisterEmailMobileVerificationEntity>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/deleteQuickRegisterEntityCreateDetails",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CustomerOrVendorDetailsDTO>> deleteQuickRegisterEntityCreateDetails(@Valid @RequestBody QuickRegisterEntity quickRegisterEntity,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<CustomerOrVendorDetailsDTO>> result=null;
				
		try{
			
			CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
			result=new ResponseEntity<ResponseDTO<CustomerOrVendorDetailsDTO>>(
					new ResponseDTO<CustomerOrVendorDetailsDTO>("",customerOrVendorDetailsDTO), HttpStatus.CREATED);
			
		}
		catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			if(mobileVerificationDetailsRepository.findByMobile(quickRegisterEntity.getMobile())!=null)
			{
				errorMessage.append(MOBILE_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByMobile(quickRegisterEntity.getMobile());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			if(emailVerificationDetailsRepository.findByEmail(quickRegisterEntity.getEmail())!=null)
			{
				errorMessage.append(EMAIL_ALREADY_REPORTED);
			}else
			{
				Optional<AuthenticationDetails> authenticationDetails=authenticationDetailsRepository.findByEmail(quickRegisterEntity.getEmail());
				
				if(authenticationDetails.isPresent())
					errorMessage.append(EMAIL_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			}
			
			errorMessage.append(ALREADY_REPORTED);
			
			result=new ResponseEntity<ResponseDTO<CustomerOrVendorDetailsDTO>>(
					new ResponseDTO<CustomerOrVendorDetailsDTO>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
}

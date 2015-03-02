package com.projectx.data.controller.completeregister;



import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
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

	@RequestMapping(value="/updateCustomerDetails",method=RequestMethod.POST)
	public ResponseEntity<CustomerDetails> updateCustomerDetails(@Valid @RequestBody CustomerDetails customerDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<CustomerDetails> result=null;
		CustomerDetails updatedEntity=null;
		try
		{
			updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(customerDetails);
			result=new ResponseEntity<CustomerDetails>(updatedEntity, HttpStatus.OK);
		}
		catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<CustomerDetails>(HttpStatus.ALREADY_REPORTED);
		}
		return result;
		
	}
	
	@RequestMapping(value="/updateVendorDetails",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> updateVendorDetails(@Valid @RequestBody VendorDetails vendorDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<VendorDetails> result=null;
		try{		
		
			VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(vendorDetails);
			
			result=new ResponseEntity<VendorDetails>(updatedEntity, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/updateMobileInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateMobileInDetailsEnityAndAuthenticationDetails(@Valid @RequestBody CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean updatedStatus=transactionalUpdatesRepository
				.updateMobileInDetailsEnityAndAuthenticationDetails(customerIdTypeMobileTypeDTO.getCustomerId(), customerIdTypeMobileTypeDTO.getCustomerType(),
						customerIdTypeMobileTypeDTO.getMobileType(),customerIdTypeMobileTypeDTO.getUpdatedBy());
			
			result=new ResponseEntity<Boolean>(updatedStatus, HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		return result;
	}
	
	@RequestMapping(value="/updateEmailInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateEmailInDetailsEnityAndAuthenticationDetails(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailTypeDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean updatedStatus=transactionalUpdatesRepository
				.updateEmailInDetailsEnityAndAuthenticationDetails(customerIdTypeEmailTypeDTO.getCustomerId(), customerIdTypeEmailTypeDTO.getCustomerType(),
						customerIdTypeEmailTypeDTO.getEmailType(),customerIdTypeEmailTypeDTO.getUpdatedBy());
		 result=new ResponseEntity<Boolean>(updatedStatus, HttpStatus.OK);
		 
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return result;
	}
	
	@RequestMapping(value="/saveNewQuickRegisterEntity",method=RequestMethod.POST)
	public ResponseEntity<CustomerQuickRegisterEmailMobileVerificationEntity> saveNewQuickRegisterEntity(@Valid @RequestBody QuickRegisterEntity quickRegisterEntity,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<CustomerQuickRegisterEmailMobileVerificationEntity> result=null;
		
		try
		{
			CustomerQuickRegisterEmailMobileVerificationEntity returnEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(quickRegisterEntity);
			result=new ResponseEntity<CustomerQuickRegisterEmailMobileVerificationEntity>(returnEntity, HttpStatus.CREATED);
			
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/deleteQuickRegisterEntityCreateDetails",method=RequestMethod.POST)
	public ResponseEntity<CustomerOrVendorDetailsDTO> deleteQuickRegisterEntityCreateDetails(@Valid @RequestBody QuickRegisterEntity quickRegisterEntity,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<CustomerOrVendorDetailsDTO> result=null;
				
		try{
			
			CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
			result=new ResponseEntity<CustomerOrVendorDetailsDTO>(customerOrVendorDetailsDTO, HttpStatus.CREATED);
			
		}
		catch(DataIntegrityViolationException e)
		{
			
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
}

package com.projectx.data.controller.completeregister;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
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
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails customerDetails)
	{
		
		CustomerDetails updatedEntity=null;
		try
		{
			updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(customerDetails);
		}
		catch(DataIntegrityViolationException e)
		{
			return customerDetailsCustomRepository.findOne(customerDetails.getCustomerId());
		}
			
		return updatedEntity;
		
	}
	
	@RequestMapping(value="/updateVendorDetails",method=RequestMethod.POST)
	public VendorDetails updateVendorDetails(@RequestBody VendorDetails vendorDetails)
	{
		VendorDetails updatedEntity=null;
		
		try{		
			
			updatedEntity=transactionalUpdatesRepository.updateVendorDetails(vendorDetails);
			
		}catch(DataIntegrityViolationException e)
		{
			return vendorDetailsCustomRepository.findOne(vendorDetails.getVendorId());
		}
		
		return updatedEntity;
	}
	
	@RequestMapping(value="/updateMobileInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(@RequestBody CustomerIdTypeMobileTypeDTO customerIdTypeMobileTypeDTO)
	{
		Boolean updatedStatus=null;
		
		try{
		updatedStatus=transactionalUpdatesRepository
				.updateMobileInDetailsEnityAndAuthenticationDetails(customerIdTypeMobileTypeDTO.getCustomerId(), customerIdTypeMobileTypeDTO.getCustomerType(),
						customerIdTypeMobileTypeDTO.getMobileType());
		}catch(DataIntegrityViolationException e)
		{
			return false;
		}
		return updatedStatus;
	}
	
	@RequestMapping(value="/updateEmailInDetailsEnityAndAuthenticationDetails",method=RequestMethod.POST)
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(@RequestBody CustomerIdTypeEmailTypeDTO customerIdTypeEmailTypeDTO)
	{
		Boolean updatedStatus=null;
		
		try{
		 updatedStatus=transactionalUpdatesRepository
				.updateEmailInDetailsEnityAndAuthenticationDetails(customerIdTypeEmailTypeDTO.getCustomerId(), customerIdTypeEmailTypeDTO.getCustomerType(),
						customerIdTypeEmailTypeDTO.getEmailType());
		}catch(DataIntegrityViolationException e)
		{
			return false;
		}
		return updatedStatus;
	}
	
	@RequestMapping(value="/saveNewQuickRegisterEntity",method=RequestMethod.POST)
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(@RequestBody QuickRegisterEntity quickRegisterEntity)
	{
		CustomerQuickRegisterEmailMobileVerificationEntity returnEntity=null;
		
		try
		{
			returnEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(quickRegisterEntity);
			
		}catch(DataIntegrityViolationException e)
		{
			EmailVerificationDetails emailVerificationDetails=new EmailVerificationDetails();
			MobileVerificationDetails mobileVerificationDetails=new MobileVerificationDetails();
			QuickRegisterEntity quickRegisterEntity2=new QuickRegisterEntity();
			
			return new CustomerQuickRegisterEmailMobileVerificationEntity(quickRegisterEntity2, emailVerificationDetails, mobileVerificationDetails);
		}
		
		return returnEntity;
	}
	
	@RequestMapping(value="/deleteQuickRegisterEntityCreateDetails",method=RequestMethod.POST)
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(@RequestBody QuickRegisterEntity quickRegisterEntity)
	{
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=new CustomerOrVendorDetailsDTO();
		
		try{
			
			customerOrVendorDetailsDTO=transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
			return customerOrVendorDetailsDTO;
		}
		
		return customerOrVendorDetailsDTO;
	}
}
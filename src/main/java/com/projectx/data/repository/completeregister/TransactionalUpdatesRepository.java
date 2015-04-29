package com.projectx.data.repository.completeregister;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;

@Component
@Profile(value={"Test","Dev","Prod"})
public class TransactionalUpdatesRepository {
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Autowired
	CustomerDetailsCustomRepository customerDetailsRepository;
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	DriverDetailsCustomRepository driverDetailsRepository;

	@Value("${UPDATE_SUCESS}")
	private  Integer UPDATE_SUCESS;
	
	@Value("${ENTITY_TYPE_CUSTOMER}")
	private  Integer ENTITY_TYPE_CUSTOMER;
	
	@Value("${ENTITY_TYPE_VENDOR}")
	private  Integer ENTITY_TYPE_VENDOR;
	
	@Value("${ENTITY_TYPE_VENDORDRIVER}")
	private Integer ENTITY_TYPE_VENDORDRIVER;
	
	@Value("${ENTITY_TYPE_PRIMARY}")
	private  Integer ENTITY_TYPE_PRIMARY;
	
	@Value("${ENTITY_TYPE_SECONDARY}")
	private  Integer ENTITY_TYPE_SECONDARY;
	
	
	
	@Value("${ZERO_COUNT}")
	private  Integer ZERO_COUNT;
	
	@Value("${PASSWORD_TYPE_DEFAULT}")
	private String PASSWORD_TYPE_DEFAULT;
	
	
	
	@Transactional
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType,Integer updatedBy,Long updatedById)
	{
		
		MobileVerificationDetails mobileVerificationEntity=mobileVerificationDetailsRepository
				.findOne(new MobileVerificationKey(entityId, entityType, mobileType));
		
		if(mobileVerificationEntity!=null)
		{	
			Integer detailsTableUpdateStatus=UPDATE_SUCESS;
			Integer authenticationTableUpdateStatus=UPDATE_SUCESS;
			
			if(entityType.equals(ENTITY_TYPE_CUSTOMER))
			{
				if(mobileType.equals(ENTITY_TYPE_PRIMARY))
				{
					detailsTableUpdateStatus=customerDetailsRepository.updateMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true,updatedBy,updatedById);
					
					authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile(),new Date(),updatedBy,updatedById);
				}
				else if(mobileType.equals(ENTITY_TYPE_SECONDARY))
				{
					detailsTableUpdateStatus=customerDetailsRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true,updatedBy,updatedById);
				}
			}
			else if(entityType.equals(ENTITY_TYPE_VENDOR))
			{
				
				if(mobileType.equals(ENTITY_TYPE_PRIMARY))
				{	
					detailsTableUpdateStatus=vendorDetailsRepository.updateMobileAndVerificationStatus(entityId,mobileVerificationEntity.getMobile(), true,updatedBy,updatedById);
				
					authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile(),new Date(),updatedBy,updatedById);
				}
				else if(entityType.equals(ENTITY_TYPE_SECONDARY))
				{
					detailsTableUpdateStatus=vendorDetailsRepository.updateSecondaryMobileAndVerificationStatus(entityId, mobileVerificationEntity.getMobile(), true, updatedBy, updatedById);
				}
				
			}
				
			if(detailsTableUpdateStatus.equals(UPDATE_SUCESS) && authenticationTableUpdateStatus.equals(UPDATE_SUCESS))
				return true;
			else
				return false;
			
		}
		else
			return false;
	}
	
	@Transactional
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType,Integer updatedBy,Long updatedById)
	{
		EmailVerificationDetails emailVerificationEntity=emailVerificationDetailsRepository
				.findOne(new EmailVerificationKey(entityId, entityType, emailType));
		
		if(emailVerificationEntity!=null)
		{
			Integer detailsTableUpdateStatus=UPDATE_SUCESS;
			Integer authenticationTableUpdateStatus=UPDATE_SUCESS;
			
			if(entityType.equals(ENTITY_TYPE_CUSTOMER))
			{
				
				detailsTableUpdateStatus=customerDetailsRepository.updateEmailAndVerificationStatusInMainEntity(entityId, emailVerificationEntity.getEmail(), true,updatedBy,updatedById);
				
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail(),new Date(),updatedBy,updatedById);
				
			}
			else if(entityType.equals(ENTITY_TYPE_VENDOR))
			{
				detailsTableUpdateStatus=vendorDetailsRepository.updateEmailAndVerificationStatus(entityId, emailVerificationEntity.getEmail(),true,updatedBy,updatedById);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail(),new Date(),updatedBy,updatedById);
				
			}
				
			if(detailsTableUpdateStatus.equals(UPDATE_SUCESS) && authenticationTableUpdateStatus.equals(UPDATE_SUCESS))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	
	@Transactional
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity)
	{
		
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterRepository.save(quickRegisterEntity);
		
		EmailVerificationDetails savedCustomerEmailVerificationDetails=new EmailVerificationDetails();
		MobileVerificationDetails savedCustomerMobileVerificationDetails=new MobileVerificationDetails();
		
		if(savedQuickRegisterEntity.getEmail()!=null)
		{
			EmailVerificationDetails newCustomerEmailVerificationDetails=
					new EmailVerificationDetails(new EmailVerificationKey(savedQuickRegisterEntity.getCustomerId(),savedQuickRegisterEntity.getCustomerType(), 
							ENTITY_TYPE_PRIMARY),savedQuickRegisterEntity.getEmail(), null, new Date(), ZERO_COUNT, new Date(), new Date(), 
							quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedById(),quickRegisterEntity.getInsertedById());
						
			savedCustomerEmailVerificationDetails=emailVerificationDetailsRepository.save(newCustomerEmailVerificationDetails);
		}
		
		if(savedQuickRegisterEntity.getMobile()!=null)
		{
			MobileVerificationDetails newCustomerMobileVerificationDetails=
					new MobileVerificationDetails(new MobileVerificationKey(savedQuickRegisterEntity.getCustomerId(),savedQuickRegisterEntity.getCustomerType(), ENTITY_TYPE_PRIMARY),
							savedQuickRegisterEntity.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
							quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedById(),quickRegisterEntity.getInsertedById());
			
			savedCustomerMobileVerificationDetails=mobileVerificationDetailsRepository.save(newCustomerMobileVerificationDetails);

		}
		
		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(savedQuickRegisterEntity.getCustomerId(),savedQuickRegisterEntity.getCustomerType()),
				savedQuickRegisterEntity.getEmail(), savedQuickRegisterEntity.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
				quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedById(),quickRegisterEntity.getInsertedById());
		
		
		authenticationDetailsRepository.save(customerAuthenticationDetails);
				
		return new CustomerQuickRegisterEmailMobileVerificationEntity(savedQuickRegisterEntity, savedCustomerEmailVerificationDetails, savedCustomerMobileVerificationDetails);
		
	}
	
	@Transactional
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetailsEntity(QuickRegisterEntity quickRegisterEntity)
	{
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=new CustomerOrVendorDetailsDTO();

		QuickRegisterEntity fetchedQuickRegisterEntity=quickRegisterRepository.findOne(quickRegisterEntity.getCustomerId());
		
		if(fetchedQuickRegisterEntity==null)
			return customerOrVendorDetailsDTO;
		
		if(quickRegisterEntity.getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
		{
			Address homeAddress=new Address(ENTITY_TYPE_CUSTOMER, "addressLine", "city", "district", "state", 
					quickRegisterEntity.getPincode(), new Date(), new Date(), quickRegisterEntity.getUpdatedBy(),
					quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getUpdatedById(),quickRegisterEntity.getInsertedById());
			
			CustomerDetails customerDetails=new CustomerDetails(quickRegisterEntity.getCustomerId(), quickRegisterEntity.getFirstName(),null,
					quickRegisterEntity.getLastName(), null, homeAddress, quickRegisterEntity.getMobile(),null, quickRegisterEntity.getIsMobileVerified(),
					quickRegisterEntity.getEmail(), quickRegisterEntity.getIsEmailVerified(), null, null, null, null, null, false, null, new Date(), new Date(),
					quickRegisterEntity.getUpdatedBy(),quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getUpdatedById(),
					quickRegisterEntity.getInsertedById());
			
			
			
						
			CustomerDetails savedCustomerDetails=customerDetailsRepository.save(customerDetails);
			
			quickRegisterRepository.delete(quickRegisterEntity.getCustomerId());
			
			customerOrVendorDetailsDTO.setCustomerDetails(savedCustomerDetails);
		}
		else if(quickRegisterEntity.getCustomerType().equals(ENTITY_TYPE_VENDOR))
		{
			
			Address homeAddress=new Address(ENTITY_TYPE_VENDOR, "addressLine", "city", "district", "state", 
					quickRegisterEntity.getPincode(), new Date(), new Date(), quickRegisterEntity.getUpdatedBy(),
					quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getUpdatedById(),quickRegisterEntity.getInsertedById());
			
			
			VendorDetails vendorDetails=new VendorDetails(quickRegisterEntity.getCustomerId(), quickRegisterEntity.getFirstName(),null,
					quickRegisterEntity.getLastName(), null, null, null,homeAddress,quickRegisterEntity.getMobile(),null, quickRegisterEntity.getIsMobileVerified(),
					quickRegisterEntity.getEmail(),quickRegisterEntity.getIsEmailVerified(), null,null,null, new Date(), new Date(),
					quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedBy(),quickRegisterEntity.getInsertedById(),quickRegisterEntity.getInsertedById());
			
			
			
			VendorDetails savedVendorDetails=vendorDetailsRepository.save(vendorDetails);
			
			quickRegisterRepository.delete(quickRegisterEntity.getCustomerId());
			
			customerOrVendorDetailsDTO.setVendorDetails(savedVendorDetails);
			
		}
		
		return customerOrVendorDetailsDTO;
	}
}

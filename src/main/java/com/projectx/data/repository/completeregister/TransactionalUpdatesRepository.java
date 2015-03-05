package com.projectx.data.repository.completeregister;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.CustomerDetails;
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

	@Value("${UPDATE_SUCESS}")
	private  Integer UPDATE_SUCESS;
	
	@Value("${ENTITY_TYPE_CUSTOMER}")
	private  Integer ENTITY_TYPE_CUSTOMER;
	
	@Value("${ENTITY_TYPE_VENDOR}")
	private  Integer ENTITY_TYPE_VENDOR;
	
	@Value("${ENTITY_TYPE_PRIMARY}")
	private  Integer ENTITY_TYPE_PRIMARY;
	
	@Value("${ENTITY_TYPE_SECONDARY}")
	private  Integer ENTITY_TYPE_SECONDARY;
	
	
	
	@Value("${ZERO_COUNT}")
	private  Integer ZERO_COUNT;
	
	@Value("${PASSWORD_TYPE_DEFAULT}")
	private String PASSWORD_TYPE_DEFAULT;
	
	
	@Transactional
	//Will update all changes or none.
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails)
	{
		
		CustomerDetails oldEntity=customerDetailsRepository.findOne(customerDetails.getCustomerId());
		
		if(oldEntity!=null)
		{	
				if(customerDetails.getMobile()!=null && !customerDetails.getMobile().equals(oldEntity.getMobile()))
				{
					
					if(mobileVerificationDetailsRepository
							.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY))!=null)
					{	
						mobileVerificationDetailsRepository
						.updateMobile(customerDetails.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
								customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),customerDetails.getUpdatedBy());
					}
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails
								(new MobileVerificationKey(customerDetails.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
										customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getEmail()!=null && !customerDetails.getEmail().equals(oldEntity.getEmail()))
				{
					if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(customerDetails.getCustomerId(),
							ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY))!=null)
						emailVerificationDetailsRepository.updateEmail(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER,
								ENTITY_TYPE_PRIMARY, customerDetails.getEmail(), null, new Date(), ZERO_COUNT,new Date(),customerDetails.getUpdatedBy());
					else
					{
						EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
								ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(),customerDetails.getUpdatedBy());
						emailVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getSecondaryMobile()!=null && !customerDetails.getSecondaryMobile()
						.equals(oldEntity.getSecondaryMobile()))
				{
					if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), 
							ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY))!=null)
						mobileVerificationDetailsRepository.updateMobile(customerDetails.getCustomerId(),
								ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY, customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),customerDetails.getUpdatedBy());
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails
								(new MobileVerificationKey(customerDetails.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY), customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
						
				}
	
			
				//Will update changed value when verification is successful
				if(  customerDetails.getIsMobileVerified()==null)
				{
						customerDetails.setIsMobileVerified(oldEntity.getIsMobileVerified());
				}	
				
				
				if(customerDetails.getIsEmailVerified()==null)
				{
						customerDetails.setIsEmailVerified(oldEntity.getIsEmailVerified());
				}
					
				
				if(customerDetails.getIsSecondaryMobileVerified()==null)
				{

						customerDetails.setIsSecondaryMobileVerified(oldEntity.getIsSecondaryMobileVerified());
			
				}
				
				customerDetails.setMobile(oldEntity.getMobile());
				customerDetails.setEmail(oldEntity.getEmail());
				customerDetails.setSecondaryMobile(oldEntity.getSecondaryMobile());	
			
			
			return customerDetailsRepository.save(customerDetails);
		}
		else
		{
			if(customerDetails.getMobile()!=null)
			{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
							ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
					
					mobileVerificationDetailsRepository.save(newRecord);
				
			}
			
			if(customerDetails.getEmail()!=null )
			{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
							ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
					emailVerificationDetailsRepository.save(newRecord);
				
			}
			
			if(customerDetails.getSecondaryMobile()!=null)
			{
				MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
						ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY), customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
					
					mobileVerificationDetailsRepository.save(newRecord);
					
			}
		
			AuthenticationDetails authenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER),
					customerDetails.getEmail(), customerDetails.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), customerDetails.getUpdatedBy());
			
			authenticationDetailsRepository.save(authenticationDetails);
			
			if(customerDetails.getIsMobileVerified()==null)
				customerDetails.setIsMobileVerified(false);
			
			if(customerDetails.getIsEmailVerified()==null)
				customerDetails.setIsEmailVerified(false);
			
			if(customerDetails.getIsSecondaryMobileVerified()==null)
				customerDetails.setIsSecondaryMobileVerified(false);
			
			return customerDetailsRepository.save(customerDetails);
			
		}
	}
	
	@Transactional
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails)
	{
		VendorDetails oldEntity=vendorDetailsRepository.findOne(vendorDetails.getVendorId());
		
		if(oldEntity!=null && oldEntity.getVendorId()!=null)
		{
		
			if(vendorDetails.getMobile()!=null && !vendorDetails.getMobile().equals(oldEntity.getMobile()))
			{
				if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY))!=null)
				{	
					mobileVerificationDetailsRepository.updateMobile(vendorDetails.getVendorId(),ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
							vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),vendorDetails.getUpdatedBy());
				}
				else
				{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY), vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), vendorDetails.getUpdatedBy());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
			if(vendorDetails.getEmail()!=null && !vendorDetails.getEmail().equals(oldEntity.getEmail()))
			{
				
				if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR,ENTITY_TYPE_PRIMARY))!=null)
					emailVerificationDetailsRepository.updateEmail(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR,
							ENTITY_TYPE_PRIMARY, vendorDetails.getEmail(), null, new Date(), ZERO_COUNT,new Date(),vendorDetails.getUpdatedBy());
				else
				{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY), vendorDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(), vendorDetails.getUpdatedBy());
					emailVerificationDetailsRepository.save(newRecord);
				}
				
				
			}
	
			//Will update changed value when verification is sucessful
			vendorDetails.setMobile(oldEntity.getMobile());
			vendorDetails.setEmail(oldEntity.getEmail());
			
			if(vendorDetails.getIsEmailVerified()==null)
				vendorDetails.setIsEmailVerified(oldEntity.getIsEmailVerified());
			
			if(vendorDetails.getIsMobileVerified()==null)
				vendorDetails.setIsMobileVerified(oldEntity.getIsMobileVerified());
			
			return vendorDetailsRepository.save(vendorDetails);
		
		}
		else
		{
			if(vendorDetails.getMobile()!=null )
			{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY), vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), vendorDetails.getUpdatedBy());
					
					mobileVerificationDetailsRepository.save(newRecord);
			}
			
			if(vendorDetails.getEmail()!=null )
			{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY), vendorDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(), vendorDetails.getUpdatedBy());
					emailVerificationDetailsRepository.save(newRecord);			
				
			}
			
			AuthenticationDetails authenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR),
					vendorDetails.getEmail(), vendorDetails.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), vendorDetails.getUpdatedBy());
			
			authenticationDetailsRepository.save(authenticationDetails);

			
			return vendorDetailsRepository.save(vendorDetails);

		}
	}

	@Transactional
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType,String updatedBy)
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
					detailsTableUpdateStatus=customerDetailsRepository.updateMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true,updatedBy);
					authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile(),new Date(),updatedBy);
				}
				else if(mobileType.equals(ENTITY_TYPE_SECONDARY))
				{
					detailsTableUpdateStatus=customerDetailsRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true,updatedBy);
				}
			}
			else if(entityType.equals(ENTITY_TYPE_VENDOR))
			{
				detailsTableUpdateStatus=vendorDetailsRepository.updateMobileAndVerificationStatus(entityId,mobileVerificationEntity.getMobile(), true,updatedBy);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile(),new Date(),updatedBy);
				
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
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType,String updatedBy)
	{
		EmailVerificationDetails emailVerificationEntity=emailVerificationDetailsRepository
				.findOne(new EmailVerificationKey(entityId, entityType, emailType));
		
		if(emailVerificationEntity!=null)
		{
			Integer detailsTableUpdateStatus=UPDATE_SUCESS;
			Integer authenticationTableUpdateStatus=UPDATE_SUCESS;
			
			if(entityType.equals(ENTITY_TYPE_CUSTOMER))
			{
				
				detailsTableUpdateStatus=customerDetailsRepository.updateEmailAndVerificationStatusInMainEntity(entityId, emailVerificationEntity.getEmail(), true,updatedBy);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail(),new Date(),updatedBy);
				
			}
			else if(entityType.equals(ENTITY_TYPE_VENDOR))
			{
				detailsTableUpdateStatus=vendorDetailsRepository.updateEmailAndVerificationStatus(entityId, emailVerificationEntity.getEmail(),true,updatedBy);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail(),new Date(),updatedBy);
				
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
							ENTITY_TYPE_PRIMARY),savedQuickRegisterEntity.getEmail(), null, new Date(), ZERO_COUNT, new Date(), new Date(), quickRegisterEntity.getUpdatedBy());
						
			savedCustomerEmailVerificationDetails=emailVerificationDetailsRepository.save(newCustomerEmailVerificationDetails);
		}
		
		if(savedQuickRegisterEntity.getMobile()!=null)
		{
			MobileVerificationDetails newCustomerMobileVerificationDetails=
					new MobileVerificationDetails(new MobileVerificationKey(savedQuickRegisterEntity.getCustomerId(),savedQuickRegisterEntity.getCustomerType(), ENTITY_TYPE_PRIMARY),
							savedQuickRegisterEntity.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), quickRegisterEntity.getUpdatedBy());
			
			savedCustomerMobileVerificationDetails=mobileVerificationDetailsRepository.save(newCustomerMobileVerificationDetails);

		}
		
		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(savedQuickRegisterEntity.getCustomerId(),savedQuickRegisterEntity.getCustomerType()),
				savedQuickRegisterEntity.getEmail(), savedQuickRegisterEntity.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), quickRegisterEntity.getUpdatedBy());
		
		
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
			CustomerDetails customerDetails=new CustomerDetails(quickRegisterEntity.getCustomerId(), quickRegisterEntity.getFirstName(),
					quickRegisterEntity.getLastName(), null, null, quickRegisterEntity.getMobile(), quickRegisterEntity.getIsMobileVerified(),
					quickRegisterEntity.getEmail(), quickRegisterEntity.getIsEmailVerified(), null, null, null, null, null, false, null, new Date(), new Date(), quickRegisterEntity.getUpdatedBy());
			
						
			CustomerDetails savedCustomerDetails=customerDetailsRepository.save(customerDetails);
			
			quickRegisterRepository.delete(quickRegisterEntity.getCustomerId());
			
			customerOrVendorDetailsDTO.setCustomerDetails(savedCustomerDetails);
		}
		else if(quickRegisterEntity.getCustomerType().equals(ENTITY_TYPE_VENDOR))
		{
			
			VendorDetails vendorDetails=new VendorDetails(quickRegisterEntity.getCustomerId(), quickRegisterEntity.getFirstName(),
					quickRegisterEntity.getLastName(), null, null, quickRegisterEntity.getMobile(), quickRegisterEntity.getIsMobileVerified(),
					quickRegisterEntity.getEmail(),quickRegisterEntity.getIsEmailVerified(), null, new Date(), new Date(),quickRegisterEntity.getUpdatedBy());
			
			VendorDetails savedVendorDetails=vendorDetailsRepository.save(vendorDetails);
			
			quickRegisterRepository.delete(quickRegisterEntity.getCustomerId());
			
			customerOrVendorDetailsDTO.setVendorDetails(savedVendorDetails);
			
		}
		
		return customerOrVendorDetailsDTO;
	}
}

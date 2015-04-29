package com.projectx.data.repository.completeregister;

import java.io.Serializable;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;


@Repository
@Profile(value={"Test","Dev","Prod"})
public class CustomerDetailsCustomRepository {
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
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
	public CustomerDetails save(CustomerDetails customerDetails)
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
								customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),customerDetails.getUpdatedBy(),customerDetails.getUpdatedById());
					}
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails
								(new MobileVerificationKey(customerDetails.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
										customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
										customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getEmail()!=null && !customerDetails.getEmail().equals(oldEntity.getEmail()))
				{
					if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(customerDetails.getCustomerId(),
							ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY))!=null)
						emailVerificationDetailsRepository.updateEmail(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER,
								ENTITY_TYPE_PRIMARY, customerDetails.getEmail(), null, new Date(), ZERO_COUNT,new Date(),customerDetails.getUpdatedBy(),customerDetails.getUpdatedById());
					else
					{
						EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
								ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(),
								customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
						emailVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getSecondaryMobile()!=null && !customerDetails.getSecondaryMobile()
						.equals(oldEntity.getSecondaryMobile()))
				{
					if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), 
							ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY))!=null)
						mobileVerificationDetailsRepository.updateMobile(customerDetails.getCustomerId(),
								ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY, customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),
								customerDetails.getUpdatedBy(),customerDetails.getUpdatedById());
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails
								(new MobileVerificationKey(customerDetails.getCustomerId(),ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY), customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
										customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
						
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
				
				if(customerDetails.getHomeAddressId()!=null && oldEntity.getHomeAddressId()!=null)
					customerDetails.getHomeAddressId().setAddressId(oldEntity.getHomeAddressId().getAddressId());
				
				if(customerDetails.getFirmAddressId()!=null && oldEntity.getFirmAddressId()!=null)
					customerDetails.getFirmAddressId().setAddressId(oldEntity.getFirmAddressId().getAddressId());
				
				customerDetails.setMobile(oldEntity.getMobile());
				customerDetails.setEmail(oldEntity.getEmail());
				customerDetails.setSecondaryMobile(oldEntity.getSecondaryMobile());	
			
			
			return customerDetailsRepository.save(customerDetails);
		}
		else
		{
			if(customerDetails.getMobile()!=null)
			{
					MobileVerificationDetails mobileVerificationDetails=mobileVerificationDetailsRepository
							.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY));
					
					if(mobileVerificationDetails==null || (mobileVerificationDetails!=null && !mobileVerificationDetails.getMobile().equals(customerDetails.getMobile())))
					{	
						MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
								ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
								customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
				
			}
			
			if(customerDetails.getEmail()!=null )
			{
				
				EmailVerificationDetails  emailVerificationDetails=emailVerificationDetailsRepository
						.findOne(new EmailVerificationKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY));
				
				if(emailVerificationDetails==null || (emailVerificationDetails!=null && !emailVerificationDetails.getEmail().equals(customerDetails.getEmail())))
				{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
							ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY), customerDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(),
							customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
					
					emailVerificationDetailsRepository.save(newRecord);
				}
			}
			
			if(customerDetails.getSecondaryMobile()!=null)
			{
				
				MobileVerificationDetails mobileVerificationDetailsSec=mobileVerificationDetailsRepository
						.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY));
			
				if(mobileVerificationDetailsSec==null || (mobileVerificationDetailsSec!=null && !mobileVerificationDetailsSec.getMobile().equals(customerDetails.getSecondaryMobile())))
				{
				
				MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
						ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY), customerDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
					
			}
		
			AuthenticationDetails details=authenticationDetailsRepository.findOne(new AuthenticationDetailsKey(customerDetails.getCustomerId(),
					ENTITY_TYPE_CUSTOMER));
			
			if(details==null ||(details!=null && !details.getEmail().equals(customerDetails.getEmail()) && !details.getMobile().equals(customerDetails.getMobile())))
			{
				AuthenticationDetails authenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(customerDetails.getCustomerId(), ENTITY_TYPE_CUSTOMER),
						customerDetails.getEmail(), customerDetails.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						customerDetails.getInsertedBy(),customerDetails.getInsertedBy(),customerDetails.getInsertedById(),customerDetails.getInsertedById());
				
				authenticationDetailsRepository.save(authenticationDetails);
			}
			if(customerDetails.getIsMobileVerified()==null)
				customerDetails.setIsMobileVerified(false);
			
			if(customerDetails.getIsEmailVerified()==null)
				customerDetails.setIsEmailVerified(false);
			
			if(customerDetails.getIsSecondaryMobileVerified()==null)
				customerDetails.setIsSecondaryMobileVerified(false);
			
			return customerDetailsRepository.save(customerDetails);
			
		}

	}
	
	public CustomerDetails findOne(Long customerId)
	{
		return customerDetailsRepository.findOne(customerId);
	}
	
	
	public Integer updateMobileAndVerificationStatusInMainEntity(Long customerId,Long mobile,Boolean status,Integer updatedBy,Long updatedById)
	{
		return customerDetailsRepository.updateMobileAndMobileVerificationStatus(customerId, mobile, status,new Date(),updatedBy,updatedById);
	}
	
	public Integer updateSecondaryMobileAndVerificationStatusInMainEntity(Long customerId,Long mobile,Boolean status,Integer updatedBy,Long updatedById)
	{
		return customerDetailsRepository.updateSecMobileAndSecMobileVerificationStatus(customerId, mobile, status,new Date(),updatedBy,updatedById);
	}
	
	public Integer updateEmailAndVerificationStatusInMainEntity(Long customerId,String email,Boolean status,Integer updatedBy,Long updatedById)
	{
		return customerDetailsRepository.updateEmailAndMEmailVerificationStatus(customerId, email, status,new Date(),updatedBy,updatedById);
	}
	
	public Long count()
	{
		return customerDetailsRepository.count();
	}

	public void deleteAll()
	{
		customerDetailsRepository.deleteAll();
	}
	
	
}

package com.projectx.data.repository.completeregister;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

@Component
@Profile(value={"Prod","Test"}) 
public class TransactionalUpdatesRepository {
	
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
	
	private static Integer UPDATE_SUCESS=new Integer(1);
	
	@Transactional
	//Will update all changes or none.
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails)
	{
		
		CustomerDetails oldEntity=customerDetailsRepository.findOne(customerDetails.getCustomerId());
		
		if(oldEntity!=null)
		{	
				if(customerDetails.getMobile()!=null && !customerDetails.getMobile().equals(oldEntity.getMobile()))
				{
					
					if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), 1, 1))!=null)
					{	
						mobileVerificationDetailsRepository.updateMobile(customerDetails.getCustomerId(),1, 1,
								customerDetails.getMobile(), null, 0, 0);
					}
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
								1, 1), customerDetails.getMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getEmail()!=null && !customerDetails.getEmail().equals(oldEntity.getEmail()))
				{
					if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(customerDetails.getCustomerId(), 1,1))!=null)
						emailVerificationDetailsRepository.updateEmail(customerDetails.getCustomerId(), 1,
							1, customerDetails.getEmail(), null, new Date(), 0);
					else
					{
						EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
								1, 1), customerDetails.getEmail(), null, null, 0, new Date(), new Date(), "CUST_ONLINE");
						emailVerificationDetailsRepository.save(newRecord);
					}
				}
				
				if(customerDetails.getSecondaryMobile()!=null && !customerDetails.getSecondaryMobile().equals(oldEntity.getSecondaryMobile()))
				{
					if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(customerDetails.getCustomerId(), 1, 2))!=null)
						mobileVerificationDetailsRepository.updateMobile(customerDetails.getCustomerId(),1, 2, customerDetails.getSecondaryMobile(), null, 0, 0);
					else
					{
						MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
								1, 2), customerDetails.getSecondaryMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
						
						mobileVerificationDetailsRepository.save(newRecord);
					}
						
				}
	
			
				//Will update changed value when verification is successful
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
							1, 1), customerDetails.getMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
					
					mobileVerificationDetailsRepository.save(newRecord);
				
			}
			
			if(customerDetails.getEmail()!=null )
			{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(customerDetails.getCustomerId(),
							1, 1), customerDetails.getEmail(), null, null, 0, new Date(), new Date(), "CUST_ONLINE");
					emailVerificationDetailsRepository.save(newRecord);
				
			}
			
			if(customerDetails.getSecondaryMobile()!=null)
			{
				MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(customerDetails.getCustomerId(), 
							1, 2), customerDetails.getSecondaryMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
					
					mobileVerificationDetailsRepository.save(newRecord);
					
			}
		
			return customerDetailsRepository.save(customerDetails);
			
		}
	}
	
	@Transactional
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails)
	{
		VendorDetails oldEntity=vendorDetailsRepository.findOne(vendorDetails.getVendorId());
		
		if(oldEntity!=null)
		{
		
			if(vendorDetails.getMobile()!=null && !vendorDetails.getMobile().equals(oldEntity.getMobile()))
			{
				if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), 2, 1))!=null)
				{	
					mobileVerificationDetailsRepository.updateMobile(vendorDetails.getVendorId(),2, 1,
							vendorDetails.getMobile(), null, 0, 0);
				}
				else
				{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							2, 1), vendorDetails.getMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
			if(vendorDetails.getEmail()!=null && !vendorDetails.getEmail().equals(oldEntity.getEmail()))
			{
				
				if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(vendorDetails.getVendorId(), 2,1))!=null)
					emailVerificationDetailsRepository.updateEmail(vendorDetails.getVendorId(), 2,
							1, vendorDetails.getEmail(), null, new Date(), 0);
				else
				{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							2, 1), vendorDetails.getEmail(), null, null, 0, new Date(), new Date(), "CUST_ONLINE");
					emailVerificationDetailsRepository.save(newRecord);
				}
				
				
			}
	
			//Will update changed value when verification is sucessful
			vendorDetails.setMobile(oldEntity.getMobile());
			vendorDetails.setEmail(oldEntity.getEmail());
			
			return vendorDetailsRepository.save(vendorDetails);
		
		}
		else
		{
			if(vendorDetails.getMobile()!=null )
			{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							2, 1), vendorDetails.getMobile(), null, 0, 0, new Date(), new Date(), "CUST_ONLINE");
					
					mobileVerificationDetailsRepository.save(newRecord);
			}
			
			if(vendorDetails.getEmail()!=null )
			{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							2, 1), vendorDetails.getEmail(), null, null, 0, new Date(), new Date(), "CUST_ONLINE");
					emailVerificationDetailsRepository.save(newRecord);			
				
			}
			
			return vendorDetailsRepository.save(vendorDetails);

		}
	}

	@Transactional
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType)
	{
		
		MobileVerificationDetails mobileVerificationEntity=mobileVerificationDetailsRepository
				.findOne(new MobileVerificationKey(entityId, entityType, mobileType));
		
		if(mobileVerificationEntity!=null)
		{	
			Integer detailsTableUpdateStatus=UPDATE_SUCESS;
			Integer authenticationTableUpdateStatus=UPDATE_SUCESS;
			
			if(entityType.equals(new Integer(1)))
			{
				if(mobileType.equals(new Integer(1)))
				{
					detailsTableUpdateStatus=customerDetailsRepository.updateMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true);
					authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile());
				}
				else if(mobileType.equals(new Integer(2)))
				{
					detailsTableUpdateStatus=customerDetailsRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(entityId,mobileVerificationEntity.getMobile(), true);
				}
			}
			else if(entityType.equals(new Integer(2)))
			{
				detailsTableUpdateStatus=vendorDetailsRepository.updateMobileAndVerificationStatus(entityId,mobileVerificationEntity.getMobile(), true);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateMobile(entityId, entityType, mobileVerificationEntity.getMobile());
				
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
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType)
	{
		EmailVerificationDetails emailVerificationEntity=emailVerificationDetailsRepository
				.findOne(new EmailVerificationKey(entityId, entityType, emailType));
		
		if(emailVerificationEntity!=null)
		{
			Integer detailsTableUpdateStatus=UPDATE_SUCESS;
			Integer authenticationTableUpdateStatus=UPDATE_SUCESS;
			
			if(entityType.equals(new Integer(1)))
			{
				
				detailsTableUpdateStatus=customerDetailsRepository.updateEmailAndVerificationStatusInMainEntity(entityId, emailVerificationEntity.getEmail(), true);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail());
				
			}
			else if(entityType.equals(new Integer(2)))
			{
				detailsTableUpdateStatus=vendorDetailsRepository.updateEmailAndVerificationStatus(entityId, emailVerificationEntity.getEmail(),true);
				authenticationTableUpdateStatus=authenticationDetailsRepository.updateEmail(entityId, entityType, emailVerificationEntity.getEmail());
				
			}
				
			if(detailsTableUpdateStatus.equals(UPDATE_SUCESS) && authenticationTableUpdateStatus.equals(UPDATE_SUCESS))
				return true;
			else
				return false;
		}
		else
			return false;
	}
}

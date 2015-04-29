package com.projectx.data.repository.completeregister;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

@Component
@Profile(value={"Test","Prod","Dev"})
public class VendorDetailsCustomRepository {

	@Autowired
	VendorDetailsRepositoty vendorDetailsRepository;
	
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
	public VendorDetails save(VendorDetails vendorDetails)
	{
		VendorDetails oldEntity=vendorDetailsRepository.findOne(vendorDetails.getVendorId());
		
		Integer Entity_Type;
		
		Entity_Type=ENTITY_TYPE_VENDOR;
		
		if(oldEntity!=null && oldEntity.getVendorId()!=null)
		{
		
			if(vendorDetails.getMobile()!=null && !vendorDetails.getMobile().equals(oldEntity.getMobile()))
			{
				if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), Entity_Type, ENTITY_TYPE_PRIMARY))!=null)
				{	
					mobileVerificationDetailsRepository.updateMobile(vendorDetails.getVendorId(),Entity_Type, ENTITY_TYPE_PRIMARY,
							vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),vendorDetails.getUpdatedBy(),vendorDetails.getUpdatedById());
				}
				else
				{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							Entity_Type, ENTITY_TYPE_PRIMARY), vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
			if(vendorDetails.getEmail()!=null && !vendorDetails.getEmail().equals(oldEntity.getEmail()))
			{
				
				if(emailVerificationDetailsRepository.findOne(new EmailVerificationKey(vendorDetails.getVendorId(), Entity_Type,ENTITY_TYPE_PRIMARY))!=null)
					emailVerificationDetailsRepository.updateEmail(vendorDetails.getVendorId(), Entity_Type,
							ENTITY_TYPE_PRIMARY, vendorDetails.getEmail(), null, new Date(), ZERO_COUNT,new Date(),
							vendorDetails.getUpdatedBy(),vendorDetails.getUpdatedById());
				else
				{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							Entity_Type, ENTITY_TYPE_PRIMARY), vendorDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(),
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById())
					;
					emailVerificationDetailsRepository.save(newRecord);
				}
				
				
			}
			
			if(vendorDetails.getSecondaryMobile()!=null && !vendorDetails.getSecondaryMobile().equals(oldEntity.getSecondaryMobile()))
			{
				if(mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), Entity_Type, ENTITY_TYPE_SECONDARY))!=null)
				{	
					mobileVerificationDetailsRepository.updateMobile(vendorDetails.getVendorId(),Entity_Type, ENTITY_TYPE_SECONDARY,
							vendorDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT,new Date(),vendorDetails.getUpdatedBy(),vendorDetails.getUpdatedById());
				}
				else
				{
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							Entity_Type, ENTITY_TYPE_SECONDARY), vendorDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
	
		//	if(vendorDetails.getIsDriver())
			{
			
				//TODO
			}
			
			//Will update changed value when verification is sucessful
			vendorDetails.setMobile(oldEntity.getMobile());
			vendorDetails.setEmail(oldEntity.getEmail());
			
			if(vendorDetails.getIsEmailVerified()==null)
				vendorDetails.setIsEmailVerified(oldEntity.getIsEmailVerified());
			
			if(vendorDetails.getIsMobileVerified()==null)
				vendorDetails.setIsMobileVerified(oldEntity.getIsMobileVerified());
			
			if(vendorDetails.getIsSecondaryMobileVerified()==null)
				vendorDetails.setIsSecondaryMobileVerified(oldEntity.getIsSecondaryMobileVerified());
			
			if(vendorDetails.getHomeAddress()!=null && oldEntity.getHomeAddress()!=null)
				vendorDetails.getHomeAddress().setAddressId(oldEntity.getHomeAddress().getAddressId());
			
			if(vendorDetails.getFirmAddress()!=null && oldEntity.getFirmAddress()!=null)
				vendorDetails.getFirmAddress().setAddressId(oldEntity.getFirmAddress().getAddressId());
			
			return vendorDetailsRepository.save(vendorDetails);
		
		}
		else
		{
			if(vendorDetails.getMobile()!=null )
			{
				
				MobileVerificationDetails mobileVerificationDetails=mobileVerificationDetailsRepository
						.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY));
				
				if(mobileVerificationDetails==null || (mobileVerificationDetails!=null && !mobileVerificationDetails.getMobile().equals(vendorDetails.getMobile())))
				{
				
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							Entity_Type, ENTITY_TYPE_PRIMARY), vendorDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
			}
			
			if(vendorDetails.getEmail()!=null )
			{
				EmailVerificationDetails  emailVerificationDetails=emailVerificationDetailsRepository
						.findOne(new EmailVerificationKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY));
				
				if(emailVerificationDetails==null || (emailVerificationDetails!=null && !emailVerificationDetails.getEmail().equals(vendorDetails.getEmail())))
				{
					EmailVerificationDetails newRecord=new EmailVerificationDetails(new EmailVerificationKey(vendorDetails.getVendorId(),
							Entity_Type, ENTITY_TYPE_PRIMARY), vendorDetails.getEmail(), null, null, ZERO_COUNT, new Date(), new Date(), 
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
					emailVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
			if(vendorDetails.getSecondaryMobile()!=null )
			{
				MobileVerificationDetails mobileVerificationDetails=mobileVerificationDetailsRepository
						.findOne(new MobileVerificationKey(vendorDetails.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_SECONDARY));
				
				if(mobileVerificationDetails==null || (mobileVerificationDetails!=null && !mobileVerificationDetails.getMobile().equals(vendorDetails.getSecondaryMobile())))
				{
				
					MobileVerificationDetails newRecord=new MobileVerificationDetails(new MobileVerificationKey(vendorDetails.getVendorId(), 
							Entity_Type, ENTITY_TYPE_SECONDARY), vendorDetails.getSecondaryMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
							vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
					
					mobileVerificationDetailsRepository.save(newRecord);
				}
				
			}
			
			AuthenticationDetails details=authenticationDetailsRepository.findOne(new AuthenticationDetailsKey(vendorDetails.getVendorId(),
					ENTITY_TYPE_VENDOR));
			
			if(details==null ||(details!=null && !details.getEmail().equals(vendorDetails.getEmail()) && !details.getMobile().equals(vendorDetails.getMobile())))
			{
				AuthenticationDetails authenticationDetails=new AuthenticationDetails(new AuthenticationDetailsKey(vendorDetails.getVendorId(), Entity_Type),
						vendorDetails.getEmail(), vendorDetails.getMobile(), null, PASSWORD_TYPE_DEFAULT, null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						vendorDetails.getInsertedBy(),vendorDetails.getInsertedBy(),vendorDetails.getInsertedById(),vendorDetails.getInsertedById());
				
				authenticationDetailsRepository.save(authenticationDetails);
			}
			
			return vendorDetailsRepository.save(vendorDetails);

		}
	
		
	}
	
	public VendorDetails findOne(Long vendorId)
	{
		
		return vendorDetailsRepository.findOne(vendorId);
	}
	
	public Integer updateEmailAndVerificationStatus(Long vendorId,String email,Boolean status,Integer updatedBy,Long updatedById)
	{
		Integer updateStatus=vendorDetailsRepository.updateIsEmailVerified(vendorId,email, status,new Date(),updatedBy,updatedById);
		
		return updateStatus;
	}
	
	public Integer updateMobileAndVerificationStatus(Long vendorId,Long mobile,Boolean status,Integer updatedBy,Long updatedById)
	{
		Integer updateStatus=vendorDetailsRepository.updateIsMobileVerified(vendorId,mobile, status,new Date(),updatedBy,updatedById);
		
		return updateStatus;
	}
	
	public Integer updateSecondaryMobileAndVerificationStatus(Long vendorId,Long mobile,Boolean status,Integer updatedBy,Long updatedById)
	{
		Integer updateStatus=vendorDetailsRepository.updateIsSecondaryMobileVerified(vendorId,mobile, status,new Date(),updatedBy,updatedById);
		
		return updateStatus;
	}
	
	public Integer count()
	{
		Integer count=(int)vendorDetailsRepository.count();
		
		return count;
	}
	
	public Boolean deleteAll()
	{
		vendorDetailsRepository.deleteAll();
		
		return true;
	}
}

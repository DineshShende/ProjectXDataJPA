package com.projectx.data.repository.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.VendorDetails;

@Component
@Profile(value={"Test","Prod"})
public class VendorDetailsCustomRepository {

	@Autowired
	VendorDetailsRepositoty vendorDetailsRepository;
	
	
	public VendorDetails save(VendorDetails vendorDetails)
	{
		VendorDetails savedEntity=vendorDetailsRepository.save(vendorDetails);
		
		return savedEntity;
	}
	
	public VendorDetails update(VendorDetails vendorDetails)
	{
		VendorDetails oldEntity=vendorDetailsRepository.findOne(vendorDetails.getVendorId());
		
		if(oldEntity!=null)
		{
			if((oldEntity!=null && vendorDetails.getFirstName()!=null &&!oldEntity.getFirstName().equals(vendorDetails.getFirstName())))
				oldEntity.setFirstName(vendorDetails.getFirstName());
			
			if((oldEntity!=null && vendorDetails.getLastName()!=null &&!oldEntity.getLastName().equals(vendorDetails.getLastName())))
				oldEntity.setLastName(vendorDetails.getLastName());
	
			
			if((oldEntity!=null && vendorDetails.getDateOfBirth()!=null &&!oldEntity.getDateOfBirth().equals(vendorDetails.getDateOfBirth())))
				oldEntity.setDateOfBirth(vendorDetails.getDateOfBirth());
			
			if((oldEntity!=null && vendorDetails.getFirmAddress()!=null &&!oldEntity.getFirmAddress().equals(vendorDetails.getFirmAddress())))
				oldEntity.setFirmAddress(vendorDetails.getFirmAddress());
			
/*
			if((oldEntity!=null && vendorDetails.getMobile()!=null &&!oldEntity.getMobile().equals(vendorDetails.getMobile())))
				oldEntity.setMobile(vendorDetails.getMobile());
			
			if((oldEntity!=null && vendorDetails.getEmail()!=null &&!oldEntity.getEmail().equals(vendorDetails.getEmail())))
				oldEntity.setEmail(vendorDetails.getEmail());
	
*/			
			if((oldEntity!=null && vendorDetails.getLaguage()!=null &&!oldEntity.getLaguage().equals(vendorDetails.getLaguage())))
				oldEntity.setLaguage(vendorDetails.getLaguage());
			
			
			
			VendorDetails savedEntity=vendorDetailsRepository.save(oldEntity);
			
			return savedEntity;
		}
		else
		{
			return new VendorDetails();
		}
		
		
	}
	
	public VendorDetails findOne(Long vendorId)
	{
		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		if(fetchedEntity==null)
			return new VendorDetails();
		
		return fetchedEntity;
	}
	
	public Integer updateEmailVerificationStatus(Long vendorId,Boolean status)
	{
		Integer updateStatus=vendorDetailsRepository.updateIsEmailVerified(vendorId, status);
		
		return updateStatus;
	}
	
	public Integer updateMobileVerificationStatus(Long vendorId,Boolean status)
	{
		Integer updateStatus=vendorDetailsRepository.updateIsMobileVerified(vendorId, status);
		
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

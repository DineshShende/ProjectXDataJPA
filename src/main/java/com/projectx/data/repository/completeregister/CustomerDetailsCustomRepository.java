package com.projectx.data.repository.completeregister;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateAddressDTO;

@Repository
@Profile(value="Prod")
public class CustomerDetailsCustomRepository {
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	AddressRepository addressRepository;
		
	public CustomerDetails save(CustomerDetails customerDetails)
	{
		return customerDetailsRepository.save(customerDetails);
	}
	
	public CustomerDetails findOne(Long customerId)
	{
		return customerDetailsRepository.findOne(customerId);
	}
	
	public CustomerDetails updateHomeAddress(UpdateAddressDTO addressDTO)
	{
		CustomerDetails fetchedEntity=customerDetailsRepository.findOne(addressDTO.getCustomerId());
		
		if(fetchedEntity!=null)
		{
			Address oldHomeAddress=fetchedEntity.getHomeAddressId();
		
			fetchedEntity.setHomeAddressId(addressDTO.getAddress());
		
			CustomerDetails updatedEntity=customerDetailsRepository.save(fetchedEntity);
		
			addressRepository.delete(oldHomeAddress.getAddressId());
		
			return updatedEntity;
		}
		else
		{
			return new CustomerDetails();
		}
	}
	

	public CustomerDetails updateFirmAddress(UpdateAddressDTO addressDTO)
	{
		CustomerDetails fetchedEntity=customerDetailsRepository.findOne(addressDTO.getCustomerId());
		
		if(fetchedEntity!=null)
		{	
			Address oldFirmAddress=fetchedEntity.getFirmAddressId();
		
			fetchedEntity.setFirmAddressId(addressDTO.getAddress());
		
			CustomerDetails updatedEntity=customerDetailsRepository.save(fetchedEntity);
		
			addressRepository.delete(oldFirmAddress.getAddressId());
		
			return updatedEntity;
		}
		else
		{
			return new CustomerDetails();
		}
	}
	
	
	public Integer updateMobileVerificationStatus(Long customerId,Boolean status)
	{
		return customerDetailsRepository.updateIsMobileVerified(customerId, status);
	}
	
	public Integer updateSecondaryMobileVerificationStatus(Long customerId,Boolean status)
	{
		return customerDetailsRepository.updateIsSecondaryMobileVerified(customerId, status);
	}
	
	public Integer updateEmailVerificationStatus(Long customerId,Boolean status)
	{
		return customerDetailsRepository.updateIsEmailVerified(customerId, status);
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

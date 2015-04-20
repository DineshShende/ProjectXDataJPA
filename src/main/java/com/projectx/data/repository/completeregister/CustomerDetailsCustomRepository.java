package com.projectx.data.repository.completeregister;

import java.io.Serializable;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
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
	
	
		
	public CustomerDetails save(CustomerDetails customerDetails)
	{
		return customerDetailsRepository.save(customerDetails);
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

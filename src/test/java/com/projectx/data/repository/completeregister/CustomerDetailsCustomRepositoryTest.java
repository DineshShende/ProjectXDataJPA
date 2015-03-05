package com.projectx.data.repository.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.standardAddress;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class CustomerDetailsCustomRepositoryTest {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository; 
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
		
	@Before
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		emailVerificationDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
	}

	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), savedEntity);
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}
	
	@Test
	public void saveAndGetCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}

	
	@Test
	public void copiedDataFromQuickRegisterEntityAndSaveAndUpdateAfterWards()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), 
				customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails newEntityToMerge=savedEntity;
		
		newEntityToMerge.setDateOfBirth(CUST_DATE);
		newEntityToMerge.setHomeAddressId(standardCustomerDetails().getHomeAddressId());
		newEntityToMerge.setFirmAddressId(standardCustomerDetails().getFirmAddressId());
		newEntityToMerge.setLanguage(CUST_LANG);
		newEntityToMerge.setBusinessDomain(CUST_BUSINESS_DOMAIN);
		newEntityToMerge.setNameOfFirm(CUST_NAME_OF_FIRM);
		newEntityToMerge.setFirmAddressId(standardAddress());
		newEntityToMerge.setSecondaryMobile(CUST_SEC_MOBILE);
		newEntityToMerge.setSecondaryEmail(CUST_SEC_EMAIL);
		
		
		CustomerDetails mergeResult=customerDetailsCustomRepository.save(newEntityToMerge);
		
		assertEquals(newEntityToMerge, mergeResult);
	}
	
	
	@Test
	public void updateMobileVerifiedStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateMobileAndVerificationStatusInMainEntity(savedEntity.getCustomerId(),standardCustomerDetails().getMobile(), true,CUST_UPDATED_BY).intValue());
		
	}
	
	
	
	@Test
	public void updateSecondaryMobileVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(savedEntity.getCustomerId(),savedEntity.getSecondaryMobile(), true,CUST_UPDATED_BY).intValue());
		
	}
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateEmailAndVerificationStatusInMainEntity(savedEntity.getCustomerId(), savedEntity.getEmail(),true,CUST_UPDATED_BY).intValue());
		
	}

	
}

	/*
	@Test
	public void updateHomeAddress()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		Address updatedHomeAddress=customerDetailsCustomRepository.updateHomeAddress(standardUpdateAddressDTO()).getHomeAddressId();
		
		assertEquals(standardUpdateAddressDTO().getAddress().getAddressLine(),updatedHomeAddress.getAddressLine() );
		assertEquals(standardUpdateAddressDTO().getAddress().getCity(),updatedHomeAddress.getCity());
		assertEquals(standardUpdateAddressDTO().getAddress().getDistrict(),updatedHomeAddress.getDistrict() );
		
		
	}
	
	
	@Test
	public void updateFirmAddress()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		Address updatedFirmAddress=customerDetailsCustomRepository.updateFirmAddress(standardUpdateAddressDTO()).getFirmAddressId();
		
		assertEquals(standardUpdateAddressDTO().getAddress().getAddressLine(),updatedFirmAddress.getAddressLine() );
		assertEquals(standardUpdateAddressDTO().getAddress().getCity(),updatedFirmAddress.getCity());
		assertEquals(standardUpdateAddressDTO().getAddress().getDistrict(),updatedFirmAddress.getDistrict() );
	
		
		
	}
	*/

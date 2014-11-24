package com.projectx.data.repository;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.CustomerAuthenticationDetails;

import static com.projectx.data.fixtures.CustomerAuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class CustomerAuthenticationDetailsRepositoryTest {

	@Autowired
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	
	@Before
	public void clearData()
	{
		customerAuthenticationDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	//@Rollback(value=false)
	public void addNewCustomerDetailsWithEmailMobileCustomer()
	{
		
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), savedEntity);
		
				
	}

	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertNull(customerAuthenticationDetailsRepository.findByCustomerId(CUST_ID));
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.findOne(savedEntity.getCustomerId()));
		
	}

	@Test
	public void findByEmailMobileWithEmailMobileCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertNull( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void findByEmailWithEmailCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertNull( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailAuthenticationDetails());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void findByEmailWithMobileCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertNull( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerMobileAuthenticationDetails());
		
		assertNull(customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void updatePasswordAndPasswordType()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.
				updatePasswordAndPasswordTypeAndCounts(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),
						standardUpdatePasswordAndPasswordTypeDTO().getPassword(),standardUpdatePasswordAndPasswordTypeDTO().getPasswordType(),
						CUST_COUNT_ZERO,CUST_COUNT_ZERO).intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.
				updatePasswordAndPasswordTypeAndCounts(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),
						standardUpdatePasswordAndPasswordTypeDTO().getPassword(),standardUpdatePasswordAndPasswordTypeDTO().getPasswordType(),
						CUST_COUNT_ZERO,CUST_COUNT_ZERO).intValue());
		
	}
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCounts()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.updateEmailPasswordAndPasswordTypeAndCounts(standardUpdateEmailPassword().getCustomerId(),
				standardUpdateEmailPassword().getEmailPassword(), CUST_PASSWORD_TYPE_DEFAULT, CUST_COUNT_ZERO, CUST_COUNT_ZERO).intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updateEmailPasswordAndPasswordTypeAndCounts(standardUpdateEmailPassword().getCustomerId(),
				standardUpdateEmailPassword().getEmailPassword(), CUST_PASSWORD_TYPE_DEFAULT, CUST_COUNT_ZERO, CUST_COUNT_ZERO).intValue());
		
	}
	
	
	@Test
	public void incrementResendCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerId().getCustomerId()).intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerId().getCustomerId()).intValue());
		
	}
	
	
	@Test
	public void incrementLastUnsucessfullAttempts()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerId().getCustomerId()).intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerId().getCustomerId()).intValue());
		
	}
	
	
	
	@Test
	public void clearLoginDetailsForTesting()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		customerAuthenticationDetailsRepository.clearTestData();
		
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
	}
	


	
	/*
	 
	@Test
	public void loginVerificationWithEmailMobileCustomer()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithEmail().getEmail(),
				standardLoginVerificationWithEmail().getMobile(), standardLoginVerificationWithEmail().getPassword()));
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithMobile().getEmail(),
				standardLoginVerificationWithMobile().getMobile(), standardLoginVerificationWithMobile().getPassword()));
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithMobileNewPassword().getEmail(),
				standardLoginVerificationWithMobileNewPassword().getMobile(), standardLoginVerificationWithMobileNewPassword().getPassword()));
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithEmailNewPassword().getEmail(),
				standardLoginVerificationWithEmailNewPassword().getMobile(), standardLoginVerificationWithEmailNewPassword().getPassword()));
	}
	 
	 */
	 
}

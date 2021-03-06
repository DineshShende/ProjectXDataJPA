package com.projectx.data.repository.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_COUNT_ZERO;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_PASSWORD_TYPE_DEFAULT;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardCustomerId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.quickregister.AuthenticationDetails;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
@Transactional
public class AuthenticationDetailsRepositoryTest {

	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	
	@Before
	public void clearData()
	{
		customerAuthenticationDetailsRepository.deleteAll();
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
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), savedEntity);
		
				
	}

	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertNull(customerAuthenticationDetailsRepository.findOne(standardAuthenticationDetailsKey()));
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.findOne(standardAuthenticationDetailsKey()));
		
	}

	@Test
	public void findByEmailMobileWithEmailMobileCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertEquals(Optional.empty(), customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(Optional.empty(), customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
		//assertNull( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL));
		
		//assertNull( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE));
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL).get());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE).get());
		
	}
	
	@Test
	public void findByEmailWithEmailCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertFalse( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL).isPresent());
		
		assertFalse( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE).isPresent());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailAuthenticationDetails());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL).get());
		
		assertFalse( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE).isPresent());
		
	}
	
	@Test
	public void findByEmailWithMobileCustomer()
	{
		assertEquals(0, customerAuthenticationDetailsRepository.count());
		
		assertFalse( customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL).isPresent());
		
		assertFalse( customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE).isPresent());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerMobileAuthenticationDetails());
		
		assertFalse(customerAuthenticationDetailsRepository.findByEmail(CUST_EMAIL).isPresent());
		
		assertEquals(savedEntity, customerAuthenticationDetailsRepository.findByMobile(CUST_MOBILE).get());
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void updatePasswordAndPasswordTypeForPassword()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.
				updatePasswordEmailPasswordAndPasswordTypeAndCounts(standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerId(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerType(),
						standardUpdatePasswordEmailPasswordTypeWithEmailPass().getPassword(),null,standardUpdatePasswordEmailPasswordTypeWithEmailPass().getPasswordType(),
						CUST_COUNT_ZERO,CUST_COUNT_ZERO,new Date(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedBy(),
						standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedById()).intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.
				updatePasswordEmailPasswordAndPasswordTypeAndCounts(standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerId(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerType(),
						standardUpdatePasswordEmailPasswordTypeWithEmailPass().getPassword(),null,standardUpdatePasswordEmailPasswordTypeWithEmailPass().getPasswordType(),
						CUST_COUNT_ZERO,CUST_COUNT_ZERO,new Date(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedBy(),
						standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedById()).intValue());
		
	}
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCountsForEmailPassword()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerId(),
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerType(),null,
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getEmailPassword(), CUST_PASSWORD_TYPE_DEFAULT, CUST_COUNT_ZERO, CUST_COUNT_ZERO,
				new Date(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedBy(),
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedById()).intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(savedEntity.getKey().getCustomerId(),
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getCustomerType(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getPassword(),
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getEmailPassword(), CUST_PASSWORD_TYPE_DEFAULT, CUST_COUNT_ZERO, CUST_COUNT_ZERO
				,new Date(),standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedBy(),
				standardUpdatePasswordEmailPasswordTypeWithEmailPass().getUpdatedById()).intValue());
	}
	
	
	@Test
	public void incrementResendCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerIdTypeDTO().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType()
				,new Date(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerIdTypeDTO().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType(),
				new Date(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
	}
	
	
	@Test
	public void incrementLastUnsucessfullAttempts()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		assertEquals(0, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerId().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType(),
				new Date(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerId().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType(),
				new Date(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
	}
	
	
	@Test
	public void updateEmail()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1,customerAuthenticationDetailsRepository
				.updateEmail(savedEntity.getKey().getCustomerId(), savedEntity.getKey().getCustomerType(), "other@gmail.com",
						new Date(),CUST_UPDATED_BY,savedEntity.getKey().getCustomerId()).intValue());
		
	}
	
	@Test
	public void updateMobile()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		assertEquals(1,customerAuthenticationDetailsRepository
				.updateMobile(savedEntity.getKey().getCustomerId(), savedEntity.getKey().getCustomerType(),9999999999L,
						new Date(),CUST_UPDATED_BY,savedEntity.getKey().getCustomerId()).intValue());
		
	}
	
	@Test
	public void clearLoginDetailsForTesting()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count());
		
		customerAuthenticationDetailsRepository.deleteAll();
		
		assertEquals(0,customerAuthenticationDetailsRepository.count());
		
	}
	

	 
}

package com.projectx.data.repository;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.CustomerMobileVerificationDetails;

import static com.projectx.data.fixtures.CustomerMobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class CustomerMobileVerificationDetailsRepositoryTest {

	@Autowired
	CustomerMobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	@Before
	public void setUp()
	{
		customerMobileVerificationDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void saveCustomerMobileVerificationDetails()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		CustomerMobileVerificationDetails mobileVerification=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(standardCustomerMobileVerificationDetails(), mobileVerification);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
	
	}
	
	@Test
	public void findByCustomerIdAndMobile()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.
				findByCustomerIdAndMobile(standardCustomerIdMobileDTO().getCustomerId(), standardCustomerIdMobileDTO().getMobile()));
	}
	
	@Test
	public void updateMobilePinAndMobileVerificationAttemptsAndResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.
				updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.
				updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
	}
	
	
	@Test
	public void incrementMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(0, customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getMobile()).intValue());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getMobile()).intValue());
	}
	
	
	@Test
	public void incrementResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(), standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		//Insert Not reflected until we read.
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(), standardCustomerIdMobileDTO().getMobile()));
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(), standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		//Insert Not reflected until we read.
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		customerMobileVerificationDetailsRepository.clearTestData();
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
	}
	
}

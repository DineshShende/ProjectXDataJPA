package com.projectx.data.repository.quickregister;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class MobileVerificationDetailsRepositoryTest {

	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	@Before
	public void setUp()
	{
		customerMobileVerificationDetailsRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
	}

	
	@Test
	public void saveCustomerMobileVerificationDetails()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		MobileVerificationDetails mobileVerification=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(standardCustomerMobileVerificationDetails(), mobileVerification);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
	
		
	}
	

	
	@Test
	public void saveCustomerMobileVerificationDetailsDuplicateKey()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		MobileVerificationDetails mobileVerification=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(standardCustomerMobileVerificationDetails(), mobileVerification);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		
		try{
		mobileVerification=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetailsOther());
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		//assertEquals(standardCustomerMobileVerificationDetailsOther(), mobileVerification);
		
	}
	
	
	@Test
	public void findByCustomerIdAndMobile()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.findOne(standardMobileVerificationKey()));
	}
	
	
	@Test
	public void updateMobilePinAndMobileVerificationAttemptsAndResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.
				updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.
				updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
						standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
	}
	
	
	@Test
	public void incrementMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(0, customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(),standardCustomerIdMobileDTO().getMobile()).intValue());
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(),standardCustomerIdMobileDTO().getMobile()).intValue());
	}
	
	
	@Test
	public void incrementResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(), standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		//Insert Not reflected until we read.
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(), standardCustomerIdMobileDTO().getMobile()));
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(), standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(standardCustomerMobileVerificationDetails(), customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails()));
		//Insert Not reflected until we read.
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
		
		customerMobileVerificationDetailsRepository.deleteAll();
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
	}
	
	
	@Test
	public void deleteByKey()
	{
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
		MobileVerificationDetails mobileVerification=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count());
	
		customerMobileVerificationDetailsRepository.delete(mobileVerification.getKey());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count());
		
	}
	
}
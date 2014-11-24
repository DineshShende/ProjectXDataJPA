package com.projectx.data.repository;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.CustomerEmailVerificationDetails;

import static com.projectx.data.fixtures.CustomerEmailVerificationDetailsDataFixtures.*;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class CustomerEmailVerificationRepositoryTest {

	@Autowired
	CustomerEmailVerificationDetailsRepository customerEmailVericationDetailsRepository; 

	@Before
	public void clearData()
	{
		customerEmailVericationDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	//@Rollback(value=false)
	public void saveCustomerEmailVerificationDetails()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailVerificationDetails(), customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails()));
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		
	}
	
	
	@Test
	public void findByCustomerIdAndEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailVerificationDetails(), customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails()));
		
		assertEquals(standardCustomerEmailVerificationDetails(), customerEmailVericationDetailsRepository
				.findByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(), standardCustomerIdEmailDTO().getEmail()));
		
		
		
	}
	
	
	@Test
	public void resetEmailHashAndEmailHashSentTime()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(0, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(),standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), emailVerificationDetails);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		assertEquals(1, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(emailVerificationDetails.getCustomerId(),
				emailVerificationDetails.getEmail(),standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void incrementResendCountByCustomerIdAndEmail()
	{
		
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(0, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getEmail()).intValue());
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), emailVerificationDetails);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		assertEquals(1, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(emailVerificationDetails.getCustomerId(),
				emailVerificationDetails.getEmail()).intValue());
		
		
	}
	
	

}

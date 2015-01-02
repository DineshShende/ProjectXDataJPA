package com.projectx.data.repository.quickregister;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;

import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class EmailVerificationRepositoryTest {

	@Autowired
	EmailVerificationDetailsRepository customerEmailVericationDetailsRepository; 

	@Before
	public void clearData()
	{
		customerEmailVericationDetailsRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	//@Rollback(value=false)
	public void saveCustomerEmailVerificationDetailsDuplicateKey()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(standardCustomerEmailVerificationDetails(), customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails()));
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		try{
		customerEmailVericationDetailsRepository.save(new EmailVerificationDetails(standardEmailVerificationKey(), 
				2, "skjfhkfhakjfh78658746884r", new Date(), 0, new Date(), new Date(), "CUST_TEST"));
		}catch(DataIntegrityViolationException e)
		{
			
		}
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
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
				.findOne(standardEmailVerificationKey()));
		
		
		
	}
	
	
	@Test
	public void resetEmailHashAndEmailHashSentTime()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(0, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(),standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), emailVerificationDetails);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		assertEquals(1, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(),standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void incrementResendCountByCustomerIdAndEmail()
	{
		
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		assertEquals(0, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdTypeEmailDTO().getCustomerId(),
				standardCustomerIdTypeEmailDTO().getCustomerType(),standardCustomerIdTypeEmailDTO().getEmail()).intValue());
		
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), emailVerificationDetails);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		assertEquals(1, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdTypeEmailDTO().getCustomerId(),
				standardCustomerIdTypeEmailDTO().getCustomerType(),standardCustomerIdTypeEmailDTO().getEmail()).intValue());
		
		
	}
	
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
		EmailVerificationDetails savedEntity= customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count());
		
		customerEmailVericationDetailsRepository.delete(savedEntity.getKey());
		
		assertEquals(0, customerEmailVericationDetailsRepository.count());
		
	}

}
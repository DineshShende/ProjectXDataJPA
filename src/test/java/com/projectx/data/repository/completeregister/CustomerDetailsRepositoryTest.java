package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.CustomerDetails;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class CustomerDetailsRepositoryTest {

	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository; 
	
	
	@Before
	public void setUp()
	{
		customerDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void saveCustomerDetails()
	{
		assertEquals(0,customerDetailsRepository.count());
		
		CustomerDetails savedEntity=customerDetailsRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), savedEntity);
		
		assertEquals(1,customerDetailsRepository.count());
	}
	
	@Test
	public void saveAndGetCustomerDetails()
	{
		assertEquals(0,customerDetailsRepository.count());
		
		CustomerDetails savedEntity=customerDetailsRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), customerDetailsRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsRepository.count());
	}

	
	@Test
	public void copiedDataFromQuickRegisterEntityAndSave()
	{
		assertEquals(0,customerDetailsRepository.count());
		
		CustomerDetails savedEntity=customerDetailsRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), 
				customerDetailsRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsRepository.count());
		
		
		
	}
	
	@Test
	public void SaveWithCustomerDetailsFirstPart()
	{
		assertEquals(0,customerDetailsRepository.count());
		
		CustomerDetails savedEntity=customerDetailsRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		assertEquals(standardCustomerDetailsFirstPart(), savedEntity=customerDetailsRepository.save(standardCustomerDetailsFirstPart()));
		
		assertEquals(standardCustomerDetailsFirstPart(), 
				customerDetailsRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsRepository.count());
		
		
	}
	
	@Test
	public void SaveWithCustomerDetailsSecondPart()
	{
		assertEquals(0,customerDetailsRepository.count());
		
		CustomerDetails savedEntity=customerDetailsRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		assertEquals(standardCustomerDetailsSecondPart(), savedEntity=customerDetailsRepository.save(standardCustomerDetailsSecondPart()));
		
		assertEquals(standardCustomerDetailsSecondPart(), 
				customerDetailsRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsRepository.count());
		
	}
	
}

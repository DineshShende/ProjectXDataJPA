package com.projectx.data.repository.handshake;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.handshake.DealDetails;

import static com.projectx.data.fixtures.handshake.DealDetaisDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DealDetailsRepositoryTest {

	
	@Autowired
	DealDetailsRepository dealDetailsRepository;
	
	
	@Before
	public void setUp()
	{
		dealDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
	
		
	}
	
	@Test
	public void saveAndGetByDealId()
	{
		assertEquals(0, dealDetailsRepository.count());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.findOne(savedEntity.getDealId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count());
		
	}
	
	@Test
	public void saveAndGetByCustomerRequestId()
	{
		assertEquals(0, dealDetailsRepository.count());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.findByFreightRequestByCustomerId(savedEntity.getFreightRequestByCustomerId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count());
		
	}
	
	@Test
	public void saveAndGetByVendorRequestId()
	{
		assertEquals(0, dealDetailsRepository.count());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.findByFreightRequestByVendorId(savedEntity.getFreightRequestByVendorId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count());
		
	}

}

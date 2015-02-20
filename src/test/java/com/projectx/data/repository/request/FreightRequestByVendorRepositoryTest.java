package com.projectx.data.repository.request;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.domain.request.FreightRequestByCustomer;

import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;

import static com.projectx.data.fixtures.request.FreightRequestByVendorDataFixtures.*;
import static com.projectx.data.fixtures.request.FreightRequestByCustomerDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class FreightRequestByVendorRepositoryTest {

	@Autowired
	FreightRequestByVendorRepository testRequestRepository;
	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	
	@Before
	@After
	public void cleanUp()
	{
		testRequestRepository.deleteAll();
		vehicleDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, testRequestRepository.count());
		
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(savedEntity.getRequestId(), testRequestRepository.findOne(savedEntity.getRequestId()).getRequestId());
		
		assertEquals(1, testRequestRepository.count());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, testRequestRepository.count());
		
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		FreightRequestByVendor updatedEntity=testRequestRepository.save(savedEntity);
		
		assertEquals(savedEntity.getSource(), testRequestRepository.findOne(savedEntity.getRequestId()).getSource());
		assertEquals(savedEntity.getPickupRangeInKm(), testRequestRepository.findOne(savedEntity.getRequestId()).getPickupRangeInKm());
		
		assertEquals(1, testRequestRepository.count());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, testRequestRepository.count());
		
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(1, testRequestRepository.count());
		
		testRequestRepository.delete(savedEntity.getRequestId());
		
		assertEquals(0, testRequestRepository.count());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, testRequestRepository.count());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, testRequestRepository.count());
		
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(1, testRequestRepository.count());
		
		testRequestRepository.deleteAll();
		
		assertEquals(0, testRequestRepository.count());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, testRequestRepository.count());
		
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		List<FreightRequestByVendor> requestList=testRequestRepository.findByVendorId(savedEntity.getVendorId());
		
		assertEquals(savedEntity.getRequestId(), requestList.get(0).getRequestId());
		
		assertEquals(1, requestList.size());
	}
	

	@Test
	public void getMatchingVendorRequestFullTruckLoad()
	{
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		testRequestRepository.save(standardTestRequestOpen());
		
		testRequestRepository.save(standardTestRequestClosed());
		
		testRequestRepository.save(standardTestRequestFlexible());
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		List<FreightRequestByVendor> matchList=testRequestRepository.getMatchingVendorRequest(freightRequestByCustomer);
		
		//assertEquals(1, matchList.size());
	}
	
	@Test
	public void getMatchingVendorRequestLessThanTruckLoad()
	{
		FreightRequestByVendor savedEntity=testRequestRepository.save(standardTestRequest());
		
		testRequestRepository.save(standardTestRequestOpen());
		
		testRequestRepository.save(standardTestRequestClosed());
		
		testRequestRepository.save(standardTestRequestFlexible());
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoad());
		
		List<FreightRequestByVendor> matchList=testRequestRepository.getMatchingVendorRequest(freightRequestByCustomer);
		
		//assertEquals(2, matchList.size());
	}
	

}

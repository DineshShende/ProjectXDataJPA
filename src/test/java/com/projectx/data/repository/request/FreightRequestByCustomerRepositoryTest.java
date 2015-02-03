package com.projectx.data.repository.request;

import static org.junit.Assert.*;
import static com.projectx.data.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.data.fixtures.request.FreightRequestByVendorDataFixture.*;
import static com.projectx.data.fixtures.completeregister.VehicleDetailsDataFixtures.*;



import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class FreightRequestByCustomerRepositoryTest {
 
	@Autowired
	FreightRequestByCustomerRepository  freightRequestByCustomerRepository;
	
	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;

	@Before
	public void clearData()
	{
		freightRequestByCustomerRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.findOne(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count());
	}
	
	
	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.findOne(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
		freightRequestByCustomerRepository.delete(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerRepository.count());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
	}

	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
		freightRequestByCustomerRepository.deleteAll();
		
		assertEquals(0, freightRequestByCustomerRepository.count());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	/*
	@Test
	public void getMatchingCustomerRequest ()
	{
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerRepository.getMatchingCustomerRequest(vendorRequest);
		
		System.out.println(list);
	}
	*/
}

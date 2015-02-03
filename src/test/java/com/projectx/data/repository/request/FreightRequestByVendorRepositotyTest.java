package com.projectx.data.repository.request;

import static org.junit.Assert.*;
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
import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class FreightRequestByVendorRepositotyTest {

	@Autowired
	FreightRequestByVendorRepository  freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;

	@Before
	public void clearData()
	{
		freightRequestByVendorRepository.deleteAll();
		
		vehicleDetailsRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
		
		//VehicleDetailsDTO savedVehicle=vehicleDetailsRepository.save(standardVehicleDetails());
		
		//FreightRequestByVendor newEntity=standardFreightRequestByVendor();
		
		//newEntity.setVehicleDetailsId(savedVehicle);
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(savedEntity, freightRequestByVendorRepository.findOne(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorRepository.count());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
		
//		VehicleDetailsDTO savedVehicle=vehicleDetailsRepository.save(standardVehicleDetails());
//		
//		FreightRequestByVendor newEntity=standardFreightRequestByVendor();
//		
//		newEntity.setVehicleDetailsId(savedVehicle);
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		FreightRequestByVendor updatedEntity=freightRequestByVendorRepository.save(savedEntity);
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, freightRequestByVendorRepository.count());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
		
//		VehicleDetailsDTO savedVehicle=vehicleDetailsRepository.save(standardVehicleDetails());
//		
//		FreightRequestByVendor newEntity=standardFreightRequestByVendor();
//		
//		newEntity.setVehicleDetailsId(savedVehicle);
//		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count());
		
		freightRequestByVendorRepository.delete(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByVendorRepository.count());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
		
//		VehicleDetailsDTO savedVehicle=vehicleDetailsRepository.save(standardVehicleDetails());
//		
//		FreightRequestByVendor newEntity=standardFreightRequestByVendor();
//		
//		newEntity.setVehicleDetailsId(savedVehicle);
//		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count());
		
		freightRequestByVendorRepository.deleteAll();
		
		assertEquals(0, freightRequestByVendorRepository.count());
	}
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorRepository.count());
		
//		VehicleDetailsDTO savedVehicle=vehicleDetailsRepository.save(standardVehicleDetails());
//		
//		FreightRequestByVendor newEntity=standardFreightRequestByVendor();
//		
//		newEntity.setVehicleDetailsId(savedVehicle);
//		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendor> requestList=freightRequestByVendorRepository.findByVendorId(savedEntity.getVendorId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	
	/*
	@Test
	public void getMatchingVendorRequest()
	{
		List<FreightRequestByVendor> requestList=freightRequestByVendorRepository.getMatchingVendorRequest(new FreightRequestByCustomer());
		
		System.out.println(requestList);
	}
	*/
}

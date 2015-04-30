package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;
import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.VehicleDetailsDataFixtures.*;

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
import com.projectx.data.domain.completeregister.VehicleDetails;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
@Transactional
public class VehicleDetailsRepositoryTest {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Before
	@After
	public  void setUp()
	{
		vehicleDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetails fetchedEntity=vehicleDetailsRepository.findOne(savedEntity.getVehicleId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, vehicleDetailsRepository.count());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetails fetchedEntity=vehicleDetailsRepository.findOne(savedEntity.getVehicleId());
		
		assertEquals(savedEntity, fetchedEntity);
		
		savedEntity.setPermitType(VEHICLE_PERMIT_TYPE_STATE);
		savedEntity.setVehicleBodyType(VEHICLE_BODY_TYPE_CLOSED);
		
		vehicleDetailsRepository.save(savedEntity);
		
		fetchedEntity=vehicleDetailsRepository.findOne(savedEntity.getVehicleId());
		
		assertEquals(savedEntity, fetchedEntity);
		
		assertEquals(1, vehicleDetailsRepository.count());
	}

	@Test
	public void delete()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(1, vehicleDetailsRepository.count());
		
		vehicleDetailsRepository.deleteAll();
		
		assertEquals(0, vehicleDetailsRepository.count());
	}
	
	@Test
	public void getVehiclesByVendorId()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetails savedEntityOther=vehicleDetailsRepository.save(standardVehicleDetailsOther());
		
		List<VehicleDetails> vehicleList=vehicleDetailsRepository.getVehiclesByVendorId(standardVehicleDetails().getVendorId());
		
		assertEquals(2, vehicleList.size());
		
	}

	@Test
	public void findByRegistrationNumber()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(savedEntity, vehicleDetailsRepository.findByRegistrationNumber(savedEntity.getRegistrationNumber()));
		
		assertEquals(1, vehicleDetailsRepository.count());
	}
	
	@Test
	public void findByChassisNumber()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(savedEntity, vehicleDetailsRepository.findByChassisNumber(standardVehicleDetails().getChassisNumber()));
		
		assertEquals(1, vehicleDetailsRepository.count());
	}

}

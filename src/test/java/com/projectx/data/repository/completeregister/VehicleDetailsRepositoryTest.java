package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;
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

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class VehicleDetailsRepositoryTest {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Before
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
		
		VehicleDetailsDTO savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetailsDTO fetchedEntity=vehicleDetailsRepository.findOne(savedEntity.getVehicleId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, vehicleDetailsRepository.count());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetailsDTO savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetailsDTO fetchedEntity=vehicleDetailsRepository.findOne(savedEntity.getVehicleId());
		
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
		
		VehicleDetailsDTO savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(1, vehicleDetailsRepository.count());
		
		vehicleDetailsRepository.deleteAll();
		
		assertEquals(0, vehicleDetailsRepository.count());
	}
	
	@Test
	public void getVehiclesByVendorId()
	{
		assertEquals(0, vehicleDetailsRepository.count());
		
		VehicleDetailsDTO savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetailsDTO savedEntityOther=vehicleDetailsRepository.save(standardVehicleDetailsOther());
		
		List<VehicleDetailsDTO> vehicleList=vehicleDetailsRepository.getVehiclesByVendorId(standardVehicleDetails().getVendorId());
		
		assertEquals(2, vehicleList.size());
		
	}

	@Test
	public void joinTest()
	{
		
	}
}

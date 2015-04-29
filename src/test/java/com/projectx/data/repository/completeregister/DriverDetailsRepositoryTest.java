package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;
import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.DriverDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
//@Transactional
public class DriverDetailsRepositoryTest {

	@Autowired
	DriverDetailsCustomRepository driverDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Before
	public void clearTestData()
	{
		driverDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest()
	{
		
	}

	
	
	@Test
	public void saveWithFailure()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		mobileVerificationDetailsRepository
		.save(standardCustomerMobileVerificationDetails(standardDriverDetails().getMobile()));
	
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		DriverDetails savedEntity=null;
		
		try
		{
			savedEntity=driverDetailsRepository.save(standardDriverDetails());
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertNull(savedEntity);
			
			//assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
			
			assertEquals(0, driverDetailsRepository.count().intValue());
		}
	}

	

	@Test
	public void saveAndGetById()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
	}
	
	
	@Test
	public void getDriverByVendorId()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		DriverDetails savedEntityOther=driverDetailsRepository.save(standardDriverDetailsOther());
		
		List<DriverDetails> driverList=driverDetailsRepository.getDriverListByVendorId(standardDriverDetails().getVendorId());
		
		assertEquals(2, driverList.size());
		
	}
	
	
	@Test
	public void updateAddress()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		Address address=savedEntity.getHomeAddress();
		
		address.setAddressLine("Updated");
		address.setCity("New City");
		
		savedEntity.setHomeAddress(address);
		
		//savedEntity.setFirstName("ABC");
		
		savedEntity=driverDetailsRepository.save(savedEntity);
		
		Address address1=savedEntity.getHomeAddress();
		
		address1.setCity("Pune");
		address1.setPincode(432345);
		
		savedEntity.setHomeAddress(address1);
		
		savedEntity=driverDetailsRepository.save(savedEntity);
		
		//assertEquals(address, savedEntity.getHomeAddress());
		
		assertEquals(1, driverDetailsRepository.count().intValue());
	}
	
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		driverDetailsRepository.deleteAll();
		
		assertEquals(0, driverDetailsRepository.count().intValue());
	}
	
	@Test
	public void updateMobileAndMobileVerificationStatus()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, driverDetailsRepository.updateMobileAndMobileVerificationStatus(savedEntity.getDriverId(), 988776655443L, 
				true,DRIVER_UPDATED_BY,savedEntity.getDriverId()).intValue());
		
		
	}


	
}

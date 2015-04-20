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

	/*
	@Test
	public void updateDriverMobile()
	{
		
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetails().getMobile()));
		
		assertNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
		
		savedEntity=driverDetailsRepository.update(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
		
		assertNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetails().getMobile()));
		
		assertEquals(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getFirstName(), driverDetailsRepository.findOne(savedEntity.getDriverId()).getFirstName());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
	}
	
	
	@Test
	public void updateDriverMobileFailure()
	{
		
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		
		mobileVerificationDetailsRepository
			.save(new MobileVerificationDetails(new MobileVerificationKey(234L, 1, 1), DRIVER_MOBILE_UPDATED, null, 0,0, new Date(), new Date(), "CUST_ONLINE"));
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetails().getMobile()));
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
		
		try{
		savedEntity=driverDetailsRepository.update(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetails().getMobile()));
			
			assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
			
			assertEquals(standardDriverDetails().getFirstName(), driverDetailsRepository.findOne(savedEntity.getDriverId()).getFirstName());
			
			assertEquals(2, mobileVerificationDetailsRepository.count());
		}
	}
	
*/
	
}

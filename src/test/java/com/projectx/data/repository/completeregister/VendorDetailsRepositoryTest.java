package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;



import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.VendorDetails;

import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(value="Prod")
@Transactional
public class VendorDetailsRepositoryTest {

	@Autowired
	VendorDetailsCustomRepository vendorDetailsRepository; 
	
	
	@Before
	public void setUp()
	{
		vendorDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndFindOne()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(savedEntity, vendorDetailsRepository.findOne(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
	}
	
	
	@Test
	public void count()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
	}
	
	
	@Test
	public void deleteAll()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertTrue(vendorDetailsRepository.deleteAll());
		
		assertEquals(0,vendorDetailsRepository.count().intValue());
	}
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(1,vendorDetailsRepository.updateEmailAndVerificationStatus(savedEntity.getVendorId(),savedEntity.getEmail(), true,savedEntity.getUpdatedBy()).intValue());
	
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		//assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsEmailVerified());
	}
	
	@Test
	public void updateMobileVerificationStatus()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(1,vendorDetailsRepository.updateMobileAndVerificationStatus(savedEntity.getVendorId(),savedEntity.getMobile(), true,savedEntity.getUpdatedBy()).intValue());
	
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		//assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsEmailVerified());
	}
	
	
	@Test
	public void updateAddress()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		savedEntity.getFirmAddress().setAddressLine(standardAddressUpdated().getAddressLine());
		savedEntity.getFirmAddress().setCity(standardAddressUpdated().getCity());
		savedEntity.getFirmAddress().setDistrict(standardAddressUpdated().getDistrict());
		savedEntity.getFirmAddress().setState(standardAddressUpdated().getState());
		savedEntity.getFirmAddress().setPincode(standardAddressUpdated().getPincode());
		
		VendorDetails updatedEntity=vendorDetailsRepository.update(savedEntity);
		
		assertEquals(savedEntity.getFirmAddress().getAddressId(), updatedEntity.getFirmAddress().getAddressId());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(standardAddressUpdated(), updatedEntity.getFirmAddress());
	}

}

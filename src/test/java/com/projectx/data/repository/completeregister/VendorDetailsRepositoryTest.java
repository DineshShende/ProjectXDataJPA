package com.projectx.data.repository.completeregister;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.VendorDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(value={"Prod"})
public class VendorDetailsRepositoryTest {

	@Autowired
	VendorDetailsRepository vendorDetailsRepository; 
	
	
	@Before
	public void setUp()
	{
		vendorDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveNewAddress()
	{
		Address firmAddress=new Address(1, "dfdf", "ddf", "dfdr", "tgtg", 342134, new Date(), new Date(), "dfdf");
		
		vendorDetailsRepository.save(new VendorDetails(4L, "Utkarsh", "Borkar", new Date(), firmAddress, 9766460156L, "utkarshborkar@gmail.com", "MARATHI"));
		
		vendorDetailsRepository.save(new VendorDetails(2L, "Shailesh", "Karle", new Date(), firmAddress, 9096654324L, "shaileshkarle@gmail.com", "MARATHI"));
	}

}

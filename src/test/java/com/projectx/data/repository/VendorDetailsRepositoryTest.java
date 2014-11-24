package com.projectx.data.repository;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.Address;
import com.projectx.data.domain.VendorDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(value={"Prod"})
public class VendorDetailsRepositoryTest {

	@Autowired
	VendorDetailsRepository vendorDetailsRepository; 
	
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveNewAddress()
	{
		Address firmAddress=new Address(4L,"Balewadi", "Pune", "Maharashtra", 41105, new Date(), new Date(), "CUST_ONLINE");
		
		vendorDetailsRepository.save(new VendorDetails(4L, "Utkarsh", "Borkar", new Date(), firmAddress, 9766460156L, "utkarshborkar@gmail.com", "MARATHI"));
		
		//vendorDetailsRepository.save(new VendorDetails(2L, "Shailesh", "Karle", new Date(), firmAddress, 9096654324L, "shaileshkarle@gmail.com", "MARATHI"));
	}

}

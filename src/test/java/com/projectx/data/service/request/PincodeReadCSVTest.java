package com.projectx.data.service.request;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class PincodeReadCSVTest {
	
	@Autowired
	PincodeReadCSV pincodeReadCSV;
	
	
	
	@Test
	public void environmentTest()
	{
		
	}
	
	
	/*
	@Test
	public void read()
	{
		
		pincodeReadCSV.run();
		
	}
	*/

}

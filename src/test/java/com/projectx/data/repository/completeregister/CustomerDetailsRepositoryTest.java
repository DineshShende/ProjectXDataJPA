package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.CustomerDetails;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class CustomerDetailsRepositoryTest {

	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository; 
	
	
	@Before
	public void setUp()
	{
		customerDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	

	
}

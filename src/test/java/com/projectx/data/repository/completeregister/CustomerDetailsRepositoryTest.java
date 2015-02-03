package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysema.query.types.expr.BooleanExpression;
import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.QCustomerDetails;

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
	
	@Test
	public void firstQueryDSLTest()
	{
		
		customerDetailsRepository.save(standardCustomerDetails());
		
		QCustomerDetails customerDetails=QCustomerDetails.customerDetails;
		
		BooleanExpression emailStartWithdi=customerDetails.email.startsWith("di");
		BooleanExpression emailEndWithcom=customerDetails.email.endsWith("com");
		
		BooleanExpression expression=customerDetails.mobile.eq(CUST_MOBILE_NEW);
		//BooleanExpression 
		
		List<CustomerDetails> resultList=(List<CustomerDetails>) customerDetailsRepository.findAll(emailStartWithdi.and(emailEndWithcom).and(expression));
		
		assertEquals(0, resultList.size());
		
	}

	
}

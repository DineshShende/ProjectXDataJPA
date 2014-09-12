package com.projectx.data.repository;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_FIRSTNAME;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_LASTNAME;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_PIN;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_STATUS_EMAILMOBILE;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailCustomer;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailCustomerKey;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailMobileCustomerKey;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardMobileCustomer;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardMobileCustomerKey;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.Application;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Test")
@Transactional
public class CustomerQuickRegisterRespositoryIntegrationTest {

	@Autowired
	CustomerQuickRegisterRepositoryImpl  customerQuickRegisterRepository;
	
	
	
	@Test
	public void addEmailMobileCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		
	}
	
	@Test
	public void fetchEmailMobileCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findOne(standardEmailMobileCustomerKey()));
		
		
	}
	
	@Test
	public void addMobileCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		
	}
	
	
		@Test
	public void fetchMobileCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findOne(standardMobileCustomerKey()));
		
		
	}


	
	@Test
	public void addEmailCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		
	}
	


	@Test
	public void fetchEmailCustomer() {
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertNotNull(customerQuickRegisterRepository.findOne(standardEmailCustomerKey()));
		
		
	}
	
	
	@Test
	public void checkEmailExist()
	{
		
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(true,customerQuickRegisterRepository.exists(standardEmailCustomerKey()));
		
	}
	
	
	@Test
	public void findAll()
	{
		assertFalse( customerQuickRegisterRepository.findAll().iterator().hasNext());
		
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		Iterable<CustomerQuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		//assertThat(listOfCustomer.size(), is(1));
        assertThat(listOfCustomer, contains(
                allOf(
                        hasProperty("firstName", is(CUST_FIRSTNAME)),
                        hasProperty("lastName", is(CUST_LASTNAME)),
                        hasProperty("key", is(standardEmailMobileCustomerKey())),
                        hasProperty("pin", is(CUST_PIN)),
                        hasProperty("mobilePin", is(CUST_MOBILEPIN)),
                        hasProperty("emailHash", is(CUST_EMAILHASH)),
                        hasProperty("status", is(CUST_STATUS_EMAILMOBILE))
                        
                )
        ));
		
	}
	
}
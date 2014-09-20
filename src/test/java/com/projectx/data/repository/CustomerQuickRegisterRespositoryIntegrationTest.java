package com.projectx.data.repository;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_FIRSTNAME;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_LASTNAME;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_PIN;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_STATUS_EMAIL;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_STATUS_EMAILMOBILE;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.CUST_STATUS_MOBILE;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.STATUS_EMAIL_VERFIED;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.STATUS_MOBILE_VERFIED;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailCustomer;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.standardMobileCustomer;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;

import com.projectx.data.Application;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class CustomerQuickRegisterRespositoryIntegrationTest {

	@Autowired
	CustomerQuickRegisterRepository  customerQuickRegisterRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	
	@Test
	public void addEmailMobileCustomer() {
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		
	}
	
	@Test
	public void findAllWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		
		List<CustomerQuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                        hasProperty("firstName", is(CUST_FIRSTNAME)),
                        hasProperty("lastName", is(CUST_LASTNAME)),     
                        hasProperty("email", is(CUST_EMAIL)),
                        hasProperty("mobile", is(CUST_MOBILE)),
                        hasProperty("pin", is(CUST_PIN)),
                        hasProperty("mobilePin", is(CUST_MOBILEPIN)),
                        hasProperty("emailHash", is(CUST_EMAILHASH)),
                        hasProperty("status", is(CUST_STATUS_EMAILMOBILE))
                        
                )
        ));
		
	}
	
	@Test
	public void addEmailCustomer() {
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		//customerQuickRegisterRepository.save(new CustomerQuickRegisterEntity("ad", "fr", "fr", null, 41, "fr", null, 85L));
		
		//customerQuickRegisterRepository.save(new CustomerQuickRegisterEntity("ad", "fr", "fr1", null, 41, "fr", null, 85L));
		
		//assertEquals(3,customerQuickRegisterRepository.countByMobile(null));
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
	}
	
	@Test
	public void findAllWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		
		List<CustomerQuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                        hasProperty("firstName", is(CUST_FIRSTNAME)),
                        hasProperty("lastName", is(CUST_LASTNAME)),     
                        hasProperty("email", is(CUST_EMAIL)),
                        hasProperty("mobile",nullValue() ),
                        hasProperty("pin", is(CUST_PIN)),
                        hasProperty("mobilePin",nullValue()),
                        hasProperty("emailHash",is(CUST_EMAILHASH)),
                        hasProperty("status", is(CUST_STATUS_EMAIL))
                        
                )
        ));
		
	}
	
	@Test
	public void addMobileCustomer() {
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
	}
	
	@Test
	public void findAllWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		
		List<CustomerQuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                        hasProperty("firstName", is(CUST_FIRSTNAME)),
                        hasProperty("lastName", is(CUST_LASTNAME)),     
                        hasProperty("email", nullValue() ),
                        hasProperty("mobile",is(CUST_MOBILE)),
                        hasProperty("pin", is(CUST_PIN)),
                        hasProperty("mobilePin", is(CUST_MOBILEPIN)),
                        hasProperty("emailHash", nullValue()),
                        hasProperty("status", is(CUST_STATUS_MOBILE))
                        
                )
        ));
		
	}
	
	@Test
	public void countByEmailMobileWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void countByEmailMobileWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(1,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void countByEmailMobileWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(1,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void fetchStatusByMobileWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.fetchStatusByMobile(CUST_MOBILE));
		
		
	}
	
	@Test
	public void fetchStatusByMobileWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(CUST_STATUS_EMAILMOBILE,customerQuickRegisterRepository.fetchStatusByMobile(CUST_MOBILE));
		
		
	}
	
	@Test
	public void fetchStatusByEmailWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_STATUS_EMAIL,customerQuickRegisterRepository.fetchStatusByEmail(CUST_EMAIL));
		
		
	}
	
	@Test
	public void fetchStatusByEmailWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(CUST_STATUS_EMAILMOBILE,customerQuickRegisterRepository.fetchStatusByEmail(CUST_EMAIL));
		
		
	}
	
	
	
	@Test
	//@Transactional(value = TxType.REQUIRES_NEW)
	//@Rollback(value=false)
	public void updateStatusByMobileWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.findByMobile(CUST_MOBILE).getStatus());
		
		assertEquals(1,customerQuickRegisterRepository.updateStatusByMobile(CUST_MOBILE,STATUS_MOBILE_VERFIED ).intValue()); 
		
		//entityManager.flush();
		
		//entityManager.getTransaction().commit();
		
		//assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterRepository.findByMobile(CUST_MOBILE).getStatus());
	}
	
	@Test
	public void updateStatusWithEmailAndMobileFailingCase()
	{
		assertEquals(0,customerQuickRegisterRepository.updateStatusByMobile(CUST_MOBILE,STATUS_MOBILE_VERFIED ).intValue());
		
		assertEquals(0,customerQuickRegisterRepository.updateStatusByEmail(CUST_EMAIL,STATUS_EMAIL_VERFIED).intValue());
	}
	
	@Test
	//@Rollback(value=false)
	public void updateStatusByEmailWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_STATUS_EMAIL,customerQuickRegisterRepository.findByEmail(CUST_EMAIL).getStatus());
		
		assertEquals(1,customerQuickRegisterRepository.updateStatusByEmail(CUST_EMAIL,STATUS_EMAIL_VERFIED ).intValue()); 
		
		//entityManager.flush();
			
		//assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterRepository.findByEmail(CUST_EMAIL).getStatus());
	}
	
	/*
	@Test
	public void getStatusByEmail(String email)
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_STATUS_EMAIL,customerQuickRegisterRepository.fetchStatusByEmail(CUST_EMAIL));
		
	}
	
		
	@Test
	public void deleteByEmailWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		customerQuickRegisterRepository.deleteByEmail(CUST_EMAIL);
		
		assertEquals(0,customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
	}
	
	@Test
	public void deleteByMobileWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.deleteByMobile(CUST_MOBILE);
		
		assertEquals(0,customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	*/
}
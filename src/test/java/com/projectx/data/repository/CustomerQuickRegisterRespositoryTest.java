package com.projectx.data.repository;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.Application;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class CustomerQuickRegisterRespositoryTest {

	@Autowired
	CustomerQuickRegisterRepository  customerQuickRegisterRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	//@Autowired
	//SessionFactory sessionFactory;
	
	
	
	@Test
	//@Rollback(value=false)
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
                		//hasProperty("customerId",is(CUST_ID)),
                        hasProperty("firstName", is(CUST_FIRSTNAME)),
                        hasProperty("lastName", is(CUST_LASTNAME)),     
                        hasProperty("email", is(CUST_EMAIL)),
                        hasProperty("mobile", is(CUST_MOBILE)),
                        hasProperty("pin", is(CUST_PIN)),
                        hasProperty("mobilePin", is(CUST_MOBILEPIN)),
                        hasProperty("emailHash", is(CUST_EMAILHASH)),
                        hasProperty("status", is(CUST_STATUS_EMAILMOBILE)),
                        hasProperty("mobileVerificationAttempts",is( CUST_MOBILEPIN_VERIFICATION_ATTEMPTS)),
                        hasProperty("mobilePinSentTime", is(CUST_MOBILE_PIN_SENT_TIME)),
                        hasProperty("emailHashSentTime", is(CUST_EMAIL_HASH_SENT_TIME))
                )
        ));
		
	}
	
	
	@Test
	public void addEmailCustomer() {
		
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
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
                        hasProperty("status", is(CUST_STATUS_EMAIL)),
                        hasProperty("mobileVerificationAttempts",is( CUST_MOBILEPIN_VERIFICATION_ATTEMPTS)),
                       // hasProperty("mobilePinSentTime", is(CUST_MOBILE_PIN_SENT_TIME)),
                        hasProperty("emailHashSentTime", is(CUST_EMAIL_HASH_SENT_TIME))
                        
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
                        hasProperty("status", is(CUST_STATUS_MOBILE)),
                    	hasProperty("mobileVerificationAttempts",is( CUST_MOBILEPIN_VERIFICATION_ATTEMPTS)),
                        hasProperty("mobilePinSentTime", is(CUST_MOBILE_PIN_SENT_TIME)),
                      //  hasProperty("emailHashSentTime", is(CUST_EMAIL_HASH_SENT_TIME)),
                        hasProperty("lastStatusChangedTime",is(CUST_LAST_STATUS_CHANGED_TIME))
                        
                )
        ));
		
	}
	
	
	
	@Test
	public void findByCustomerIdWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		assertEquals(savedCustomer.getCustomerId(),customer.getCustomerId());
		assertEquals(CUST_FIRSTNAME,customer.getFirstName());
		assertEquals(CUST_LASTNAME,customer.getLastName());
		assertEquals(CUST_MOBILE,customer.getMobile());
		assertEquals(CUST_EMAIL,customer.getEmail());
		assertEquals(CUST_STATUS_EMAILMOBILE,customer.getStatus());
		assertEquals(CUST_PIN,customer.getPin());
		assertEquals(CUST_MOBILEPIN,customer.getMobilePin());
		assertEquals(CUST_EMAILHASH,customer.getEmailHash());
		assertEquals(CUST_MOBILEPIN_VERIFICATION_ATTEMPTS, customer.getMobileVerificationAttempts());
		assertEquals(CUST_MOBILE_PIN_SENT_TIME, customer.getMobilePinSentTime());
		assertEquals(CUST_EMAIL_HASH_SENT_TIME, customer.getEmailHashSentTime());
		assertEquals(CUST_LAST_STATUS_CHANGED_TIME, customer.getLastStatusChangedTime());
		
		
	}
	
	
	
	
	@Test
	public void countByEmailMobileWithEmailCustomer()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void countByEmailMobileWithMobileCustomer()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	@Test
	public void countByEmailMobileWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
	}
	
	
	@Test
	public void fetchStatusByCustomerIdWithMobileCustomer()
	{
	
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.fetchStatusByCustomerId(savedCustomer.getCustomerId()));
		
		
	}
	
	@Test
	public void fetchStatusByCustomerIdWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(CUST_STATUS_EMAILMOBILE,customerQuickRegisterRepository.fetchStatusByCustomerId(savedCustomer.getCustomerId()));
		
		
	}
	
	
	
	@Test
	public void fetchStatusByCustomerIdWithEmailCustomer()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_STATUS_EMAIL,customerQuickRegisterRepository.fetchStatusByCustomerId(savedCustomer.getCustomerId()));
		
		
	}
	
	
	
	@Test
	public void countByCustomerIdAndMobilePin()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByCustomerIdAndMobilePin(CUST_ID, CUST_MOBILEPIN));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByCustomerIdAndMobilePin(savedCustomer.getCustomerId(), CUST_MOBILEPIN));
		
		
	}
	
	
	
	@Test
	public void countByCustomerIdAndEmailHash()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByCustomerIdAndEmailHash(CUST_ID, CUST_EMAILHASH));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByCustomerIdAndEmailHash(savedCustomer.getCustomerId(), CUST_EMAILHASH));
		
		
	}
	
	
	
	@Test
	public void updateStatusWithEmailAndMobileFailingCase()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.updateStatusByCustomerId(CUST_ID, CUST_STATUS_EMAIL,new Date()));
	}
	 
	@Test
	public void updateMobilePinFailingCase()
	{
		assertEquals(0, customerQuickRegisterRepository.updateMobilePin(CUST_ID, CUST_MOBILEPIN, CUST_MOBILE_PIN_SENT_TIME).intValue());
	}
	
	@Test
	public void updateEmailHashFailingCase()
	{
		assertEquals(0, customerQuickRegisterRepository.updateEmailHash(CUST_ID, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME).intValue());
	}
	
	@Test
	public void incrementMobileVerificationAttemptsFailingTest()
	{
		assertEquals(0, customerQuickRegisterRepository.incrementMobileVerificationAttempts(CUST_ID).intValue());
	}
	
	
	
	@Test
	//@Rollback(value=false)
	public void updateStatusByCustomerIdWithMobileCustomer() throws InterruptedException
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
	//	Thread.sleep(10000);
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getStatus());
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.updateStatusByCustomerId(savedCustomer.getCustomerId(), STATUS_MOBILE_VERFIED,new Date())); 
		
		//sessionFactory.getCurrentSession().flush();
				
		//Session session = entityManager.unwrap(Session.class);
		
		//session.flush();
		
		//entityManager.getTransaction().commit();
		
		//assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterRepository.findByMobile(CUST_MOBILE).getStatus());
	}
	
	
	@Test 
	//@Rollback(value=false)
	public void updateMobilePinWithMobileCustomer() throws InterruptedException
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
	//	Thread.sleep(10000);
		
		assertEquals(CUST_MOBILEPIN, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getMobilePin());
		
		assertEquals(1,customerQuickRegisterRepository.updateMobilePin(savedCustomer.getCustomerId(), CUST_MOBILEPIN_UPDATED,new Date()).intValue());
	}
	
	
	
	@Test 
	//@Rollback(value=false)
	public void updateEmailHashWithEmailMobileCustomer() throws InterruptedException
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		//Thread.sleep(10000);
		
		assertEquals(CUST_EMAILHASH, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getEmailHash());
		
		assertEquals(1,customerQuickRegisterRepository.updateEmailHash(savedCustomer.getCustomerId(), CUST_EMAILHASH_UPDATED,new Date()).intValue());
		
	}
	
	@Test
	public void getMobileVerificationAttempts()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(0, customerQuickRegisterRepository.getMobileVerificationAttempts(savedCustomer.getCustomerId()).intValue());
	}
	
	
	@Test
	//@Rollback(value=false)
	public void incrementMobileVerificationAttempts()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.incrementMobileVerificationAttempts(savedCustomer.getCustomerId()).intValue());
	}
	
	
	@Test
	public void clearTestingData()
	{
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.clearTestData();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
	}
	
}
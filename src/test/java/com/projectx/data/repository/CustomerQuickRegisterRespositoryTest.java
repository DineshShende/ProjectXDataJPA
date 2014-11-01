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
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;





import com.projectx.data.config.Application;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@Profile("Prod")
@Transactional
public class CustomerQuickRegisterRespositoryTest {

	@Autowired
	CustomerQuickRegisterRepository  customerQuickRegisterRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	//@Autowired
	//SessionFactory sessionFactory;
	
	/*
	@Test
	public void addSameCustomerTwice()
	{
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
	}
	*/
	
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
                		hasProperty("firstName", is(standardEmailMobileCustomer().getFirstName())),
                        hasProperty("lastName", is(standardEmailMobileCustomer().getLastName())),     
                        hasProperty("email", is(standardEmailMobileCustomer().getEmail()) ),
                        hasProperty("mobile",is(standardEmailMobileCustomer().getMobile())),
                        hasProperty("pin", is(standardEmailMobileCustomer().getPin())),
                        hasProperty("mobilePin", is(standardEmailMobileCustomer().getMobilePin())),
                        hasProperty("emailHash", is(standardEmailMobileCustomer().getEmailHash())),
                        hasProperty("status", is(standardEmailMobileCustomer().getStatus())),
                    	hasProperty("mobileVerificationAttempts",is(standardEmailMobileCustomer().getMobileVerificationAttempts())),
                        hasProperty("mobilePinSentTime", nullValue()),
                        hasProperty("emailHashSentTime", nullValue()),
                        hasProperty("lastStatusChangedTime",is(standardEmailMobileCustomer().getLastStatusChangedTime())),
                        hasProperty("password", is(standardEmailMobileCustomer().getPassword())),
                        hasProperty("passwordType",is(standardEmailMobileCustomer().getPasswordType()))
                        
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
                		hasProperty("firstName", is(standardEmailCustomer().getFirstName())),
                        hasProperty("lastName", is(standardEmailCustomer().getLastName())),     
                        hasProperty("email", is(standardEmailCustomer().getEmail()) ),
                        hasProperty("mobile",nullValue()),
                        hasProperty("pin", is(standardEmailCustomer().getPin())),
                        hasProperty("mobilePin", nullValue()),
                        hasProperty("emailHash", is(standardEmailCustomer().getEmailHash())),
                        hasProperty("status", is(standardEmailCustomer().getStatus())),
                    	hasProperty("mobileVerificationAttempts",is(standardEmailCustomer().getMobileVerificationAttempts())),
                        hasProperty("mobilePinSentTime", nullValue()),
                        hasProperty("emailHashSentTime", nullValue()),
                        hasProperty("lastStatusChangedTime",is(standardEmailCustomer().getLastStatusChangedTime())),
                        hasProperty("password", nullValue()),
                        hasProperty("passwordType",nullValue())
                        
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
                        hasProperty("firstName", is(standardMobileCustomer().getFirstName())),
                        hasProperty("lastName", is(standardMobileCustomer().getLastName())),     
                        hasProperty("email", nullValue() ),
                        hasProperty("mobile",is(standardMobileCustomer().getMobile())),
                        hasProperty("pin", is(standardMobileCustomer().getPin())),
                        hasProperty("mobilePin", is(standardMobileCustomer().getMobilePin())),
                        hasProperty("emailHash", nullValue()),
                        hasProperty("status", is(standardMobileCustomer().getStatus())),
                    	hasProperty("mobileVerificationAttempts",is(standardMobileCustomer().getMobileVerificationAttempts())),
                        hasProperty("mobilePinSentTime", nullValue()),
                        hasProperty("emailHashSentTime", nullValue()),
                        hasProperty("lastStatusChangedTime",is(standardMobileCustomer().getLastStatusChangedTime())),
                        hasProperty("password", is(standardMobileCustomer().getPassword())),
                        hasProperty("passwordType",is(standardMobileCustomer().getPasswordType()))
                        
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
		assertEquals(standardEmailMobileCustomer().getFirstName(),customer.getFirstName());
		assertEquals(standardEmailMobileCustomer().getLastName(),customer.getLastName());
		assertEquals(standardEmailMobileCustomer().getMobile(),customer.getMobile());
		assertEquals(standardEmailMobileCustomer().getEmail(),customer.getEmail());
		assertEquals(standardEmailMobileCustomer().getStatus(),customer.getStatus());
		assertEquals(standardEmailMobileCustomer().getPin(),customer.getPin());
		assertEquals(standardEmailMobileCustomer().getMobilePin(),customer.getMobilePin());
		assertEquals(standardEmailMobileCustomer().getEmailHash(),customer.getEmailHash());
		assertEquals(standardEmailMobileCustomer().getMobileVerificationAttempts(), customer.getMobileVerificationAttempts());
		assertEquals(standardEmailMobileCustomer().getMobilePinSentTime(), customer.getMobilePinSentTime());
		assertEquals(standardEmailMobileCustomer().getEmailHashSentTime(), customer.getEmailHashSentTime());
		assertEquals(standardEmailMobileCustomer().getLastStatusChangedTime(), customer.getLastStatusChangedTime());
		assertEquals(standardEmailMobileCustomer().getPassword(), customer.getPassword());
		assertEquals(standardEmailMobileCustomer().getPasswordType(), customer.getPasswordType());
		
		
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
	public void updateStatusAndMobileVerificationAttemptsWithEmailAndMobileFailingCase()
	{
		assertEquals(new Integer(0),customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(CUST_ID, CUST_STATUS_EMAIL,new Date(),CUST_MOBILEPIN_VERIFICATION_ATTEMPTS));
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
	//@Rollback(value=false)
	public void updateStatusAndMobileVerificationAttemptsByCustomerIdWithMobileCustomer() throws InterruptedException
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
	//	Thread.sleep(10000);
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getStatus());
		
		assertEquals(new Integer(1),customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(savedCustomer.getCustomerId(), STATUS_MOBILE_VERFIED,new Date(),CUST_MOBILEPIN_VERIFICATION_ATTEMPTS+1)); 
		
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
	//@Rollback(value=false)
	public void updatePasswordWithEmailMobileCustomer()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomerWithPassword());
		
		assertEquals(CUST_PASSWORD_DEFAULT,savedCustomer.getPassword());
		
		assertEquals(1, customerQuickRegisterRepository.updatePassword(savedCustomer.getCustomerId(), CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED).intValue());
		
		
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void updateEmailHashAndMobileSentTime()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
	
		assertEquals(1, customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(savedCustomer.getCustomerId(), new Date(), new Date()).intValue());
	}
	

	
	
	@Test
	public void clearTestingData()
	{
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.clearTestData();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
	}

	
	/*
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
	*/

	
//	@Test
//	public void incrementMobileVerificationAttemptsFailingTest()
//	{
//		assertEquals(0, customerQuickRegisterRepository.incrementMobileVerificationAttempts(CUST_ID).intValue());
//	}
//	

	
	
//	@Test
//	public void getMobileVerificationAttempts()
//	{
//		assertEquals(0, customerQuickRegisterRepository.findAll().size());
//		
//		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
//		
//		assertEquals(0, customerQuickRegisterRepository.getMobileVerificationAttempts(savedCustomer.getCustomerId()).intValue());
//	}
//	
//	
//	@Test
//	//@Rollback(value=false)
//	public void incrementMobileVerificationAttempts()
//	{
//		assertEquals(0, customerQuickRegisterRepository.findAll().size());
//		
//		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
//		
//		assertEquals(1, customerQuickRegisterRepository.incrementMobileVerificationAttempts(savedCustomer.getCustomerId()).intValue());
//	}

	
}
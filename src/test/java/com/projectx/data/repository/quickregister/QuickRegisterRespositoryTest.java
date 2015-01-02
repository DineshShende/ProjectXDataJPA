package com.projectx.data.repository.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;











import com.projectx.data.config.Application;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@Profile("Prod")
@Transactional
public class QuickRegisterRespositoryTest {

	@Autowired
	QuickRegisterRepository  customerQuickRegisterRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	//@Autowired
	//SessionFactory sessionFactory;
	
	@Before
	public void clearTestDate()
	{
		customerQuickRegisterRepository.deleteAll();
	}
	
	
	
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
		
		
		List<QuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                		//hasProperty("customerId",is(CUST_ID)),
                		hasProperty("firstName", is(standardEmailMobileCustomer().getFirstName())),
                        hasProperty("lastName", is(standardEmailMobileCustomer().getLastName())),     
                        hasProperty("email", is(standardEmailMobileCustomer().getEmail()) ),
                        hasProperty("mobile",is(standardEmailMobileCustomer().getMobile())),
                        hasProperty("pincode",is(standardEmailMobileCustomer().getPincode())),
                        hasProperty("isEmailVerified",is(standardEmailMobileCustomer().getIsEmailVerified())),
                        hasProperty("isMobileVerified",is(standardEmailMobileCustomer().getIsMobileVerified())),
                        hasProperty("insertTime",is(standardEmailMobileCustomer().getInsertTime())),
                        hasProperty("updateTime",is(standardEmailMobileCustomer().getUpdateTime())),
                        hasProperty("updatedBy",is(standardEmailMobileCustomer().getUpdatedBy()))
                        
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
		
		
		List<QuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                		hasProperty("firstName", is(standardEmailMobileCustomer().getFirstName())),
                        hasProperty("lastName", is(standardEmailMobileCustomer().getLastName())),     
                        hasProperty("email", is(standardEmailMobileCustomer().getEmail()) ),
                        hasProperty("mobile",nullValue()),
                        hasProperty("pincode",is(standardEmailMobileCustomer().getPincode())),
                        hasProperty("isEmailVerified",is(standardEmailMobileCustomer().getIsEmailVerified())),
                        hasProperty("isMobileVerified",nullValue()),
                        hasProperty("insertTime",is(standardEmailMobileCustomer().getInsertTime())),
                        hasProperty("updateTime",is(standardEmailMobileCustomer().getUpdateTime())),
                        hasProperty("updatedBy",is(standardEmailMobileCustomer().getUpdatedBy()))
                        
                        
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
		
		
		List<QuickRegisterEntity> listOfCustomer= customerQuickRegisterRepository.findAll();
		
		assertThat(listOfCustomer.size(), is(1));
      
		assertThat(listOfCustomer, contains(
                allOf(
                		hasProperty("firstName", is(standardEmailMobileCustomer().getFirstName())),
                        hasProperty("lastName", is(standardEmailMobileCustomer().getLastName())),     
                        hasProperty("email", nullValue() ),
                        hasProperty("mobile",is(standardEmailMobileCustomer().getMobile())),
                        hasProperty("pincode",is(standardEmailMobileCustomer().getPincode())),
                        hasProperty("isEmailVerified",nullValue()),
                        hasProperty("isMobileVerified",is(standardEmailMobileCustomer().getIsMobileVerified())),
                        hasProperty("insertTime",is(standardEmailMobileCustomer().getInsertTime())),
                        hasProperty("updateTime",is(standardEmailMobileCustomer().getUpdateTime())),
                        hasProperty("updatedBy",is(standardEmailMobileCustomer().getUpdatedBy()))
                        
                )
        ));
		
	}
	
	
	
	@Test
	public void findByCustomerIdWithEmailMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(CUST_ID);
		
		assertFalse(customerOption.isPresent());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
		
		assertEquals(savedCustomer.getCustomerId(),customer.getCustomerId());
		assertEquals(standardEmailMobileCustomer().getFirstName(),customer.getFirstName());
		assertEquals(standardEmailMobileCustomer().getLastName(),customer.getLastName());
		assertEquals(standardEmailMobileCustomer().getMobile(),customer.getMobile());
		assertEquals(standardEmailMobileCustomer().getEmail(),customer.getEmail());
		assertEquals(standardEmailMobileCustomer().getPincode(), customer.getPincode());
		assertEquals(standardEmailMobileCustomer().getIsEmailVerified(), customer.getIsEmailVerified());
		assertEquals(standardEmailMobileCustomer().getIsMobileVerified(), customer.getIsMobileVerified());
		assertNotNull( customer.getInsertTime());
		assertNotNull( customer.getUpdateTime());
		assertEquals(standardEmailMobileCustomer().getUpdatedBy(), customer.getUpdatedBy());
		
	}
	
	@Test
	public void findByEmailWithEmailCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
				
		assertEquals(savedCustomer.getCustomerId(),customer.getCustomerId());
		assertEquals(standardEmailCustomer().getFirstName(),customer.getFirstName());
		assertEquals(standardEmailCustomer().getLastName(),customer.getLastName());
		assertEquals(standardEmailCustomer().getMobile(),customer.getMobile());
		assertEquals(standardEmailCustomer().getEmail(),customer.getEmail());
		assertEquals(standardEmailCustomer().getPincode(), customer.getPincode());
		assertEquals(standardEmailCustomer().getIsEmailVerified(), customer.getIsEmailVerified());
		assertEquals(standardEmailCustomer().getIsMobileVerified(), customer.getIsMobileVerified());
		assertNotNull( customer.getInsertTime());
		assertNotNull( customer.getUpdateTime());
		assertEquals(standardEmailCustomer().getUpdatedBy(), customer.getUpdatedBy());
		
	}
	

	@Test
	public void findByMobileWithMobileCustomer()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
				
		assertEquals(savedCustomer.getCustomerId(),customer.getCustomerId());
		assertEquals(standardMobileCustomer().getFirstName(),customer.getFirstName());
		assertEquals(standardMobileCustomer().getLastName(),customer.getLastName());
		assertEquals(standardMobileCustomer().getMobile(),customer.getMobile());
		assertEquals(standardMobileCustomer().getEmail(),customer.getEmail());
		assertEquals(standardMobileCustomer().getPincode(), customer.getPincode());
		assertEquals(standardMobileCustomer().getIsEmailVerified(), customer.getIsEmailVerified());
		assertEquals(standardMobileCustomer().getIsMobileVerified(), customer.getIsMobileVerified());
		assertNotNull( customer.getInsertTime());
		assertNotNull( customer.getUpdateTime());
		assertEquals(standardMobileCustomer().getUpdatedBy(), customer.getUpdatedBy());
		
	}
	
	
	@Test
	//@Rollback(value=false)
	public void updateMobileVerificationStatus()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(0, customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
		
		
		assertFalse(customer.getIsMobileVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
	//	customer=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
	//	assertTrue(customer.getIsMobileVerified());
	}
	
	@Test
	//@Rollback(value=false)
	public void updateEmailVerificationStatus()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(0, customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
			
		assertFalse(customer.getIsEmailVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
	//	customer=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
	//	assertTrue(customer.getIsMobileVerified());
	}
	
	@Test
	//@Rollback(value=false)
	public void updateEmailMobileVerificationStatus()
	{
		assertEquals(0,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(0, customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
		assertEquals(0, customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
		
		
		assertFalse(customer.getIsEmailVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
		assertEquals(1, customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()).intValue());
		
	//	customer=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
	//	assertTrue(customer.getIsMobileVerified());
	}
	

	
	@Test
	public void clearTestingData()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.deleteAll();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
	}

	
	/*
	
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
	public void updateEmailHashAndMobileSentTime()
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
	
		assertEquals(1, customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(savedCustomer.getCustomerId(), new Date(), new Date()).intValue());
	}
	

	



	

	*/
}
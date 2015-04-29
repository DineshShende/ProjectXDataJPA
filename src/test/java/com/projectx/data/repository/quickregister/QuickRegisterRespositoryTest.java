package com.projectx.data.repository.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.projectx.data.config.Application;
import com.projectx.data.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
@Transactional
public class QuickRegisterRespositoryTest {

	@Autowired
	QuickRegisterRepository  customerQuickRegisterRepository;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	//@Autowired
	//JdbcTemplateDAOImpl jdbcTemplateDAOImpl;
	
	
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
	public void environmentTest()
	{
		
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
                        hasProperty("isMobileVerified",is(standardEmailMobileCustomer().getIsMobileVerified())),
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
                        hasProperty("isEmailVerified",is(standardEmailMobileCustomer().getIsEmailVerified())),
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
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
		
		
		assertFalse(customer.getIsMobileVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),savedCustomer.getCustomerId()).intValue());
		
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
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
			
		assertFalse(customer.getIsEmailVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
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
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
		assertEquals(0, customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		Optional<QuickRegisterEntity> customerOption=customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId());
		
		QuickRegisterEntity customer=customerOption.get();
		
		
		assertFalse(customer.getIsEmailVerified());
		
		assertEquals(1,customerQuickRegisterRepository.findAll().size());
		
		assertEquals(1, customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
		assertEquals(1, customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(), 
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()).intValue());
		
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

	
	
	

}
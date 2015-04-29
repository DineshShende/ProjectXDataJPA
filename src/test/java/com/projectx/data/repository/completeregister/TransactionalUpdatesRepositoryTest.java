package com.projectx.data.repository.completeregister;


import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.CUST_MOBILE_NEW;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.CUST_MOBILE_SEC_NEW;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.CUST_SEC_MOBILE;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetails;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.VENDOR_ID;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.standardVendor;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetails;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetailsVendor;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_UPDATED_BY;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAIL_OTHER;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_PASSWORD_CHANGED;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_TYPE_CUSTOMER;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_TYPE_VENDER;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardEmailMobileVendor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class TransactionalUpdatesRepositoryTest {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository; 
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsCustomRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Value("${ZERO_COUNT}")
	private Integer ZERO_COUNT;
	
	@Value("${ENTITY_TYPE_CUSTOMER}")
	private Integer ENTITY_TYPE_CUSTOMER;

	@Value("${ENTITY_TYPE_VENDOR}")
	private Integer ENTITY_TYPE_VENDOR;

	@Value("${ENTITY_TYPE_DRIVER}")
	private Integer ENTITY_TYPE_DRIVER;
	
	@Value("${ENTITY_TYPE_PRIMARY}")
	private Integer ENTITY_TYPE_PRIMARY;
	
	@Value("${ENTITY_TYPE_SECONDARY}")
	private Integer ENTITY_TYPE_SECONDARY;	
	
	
	@Value("${ACTOR_ENTITY_SELF_WEB}")
	private Integer ACTOR_ENTITY_SELF_WEB;
	

		
	@Before
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		emailVerificationDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
		vendorDetailsCustomRepository.deleteAll();
		authenticationDetailsRepository.deleteAll();
		quickRegisterRepository.deleteAll();
	}

	@Test
	public void environmentTest() {
		
	}

	
	

	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithCustomerMobile()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedCustomer.getCustomerId(),savedCustomer.getCustomerId());
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertFalse(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedCustomer.getCustomerId()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		assertEquals(CUST_MOBILE_NEW,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertFalse(authenticationDetailsRepository.findByMobile(savedCustomer.getMobile()).isPresent());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithCustomerMobileWithFailure()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedCustomer.getCustomerId(),savedCustomer.getCustomerId());
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_CUSTOMER), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertTrue(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(),
					ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedCustomer.getCustomerId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			
			assertEquals(2, mobileVerificationDetailsRepository.count());
			
			assertEquals(2, authenticationDetailsRepository.count());
			
			assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		}
		
	}
	
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorMobile()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertFalse(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(),
				ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		assertEquals(CUST_MOBILE_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertFalse(authenticationDetailsRepository.findByMobile(savedVendor.getMobile()).isPresent());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorMobileWithFailure()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_PRIMARY), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,214L,214L);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertTrue(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(),
					ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(2, mobileVerificationDetailsRepository.count());
			
			assertEquals(2, authenticationDetailsRepository.count());
			
			assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		}
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorSecondaryMobile()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_SECONDARY),
						CUST_MOBILE_SEC_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getSecondaryMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getSecondaryMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertFalse(authenticationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(),
				ENTITY_TYPE_VENDOR, ENTITY_TYPE_SECONDARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		assertEquals(CUST_MOBILE_SEC_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getSecondaryMobile());
		
		assertFalse(authenticationDetailsRepository.findByMobile(savedVendor.getMobile()).isPresent());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorSecondaryMobileWithFailure()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_SECONDARY),
						CUST_MOBILE_SEC_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_PRIMARY), null, CUST_MOBILE_SEC_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,214L,214L);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getSecondaryMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getSecondaryMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertTrue(authenticationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(),
					ENTITY_TYPE_VENDOR, ENTITY_TYPE_SECONDARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(2, mobileVerificationDetailsRepository.count());
			
			assertEquals(2, authenticationDetailsRepository.count());
			
			assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		}
		
	}
	
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithCustomerEmail()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedCustomer.getCustomerId(),savedCustomer.getCustomerId());
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertFalse(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedCustomer.getCustomerId()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		assertEquals(CUST_EMAIL_OTHER,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertFalse(authenticationDetailsRepository.findByEmail(savedCustomer.getEmail()).isPresent());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
	}
	
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithCustomerEmailWithFailure()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedCustomer.getCustomerId(),savedCustomer.getCustomerId());
		
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_CUSTOMER), CUST_EMAIL_OTHER, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,214L,214L);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertTrue(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(),
					ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY ,CUST_UPDATED_BY,savedCustomer.getCustomerId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertEquals(2, authenticationDetailsRepository.count());
			
			assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		}
		
	}

	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithVendorEmail()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertFalse(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(),
				ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		assertEquals(CUST_EMAIL_OTHER,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertFalse(authenticationDetailsRepository.findByEmail(savedVendor.getEmail()).isPresent());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
	}
	
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithVendorEmailWithFailure()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,savedVendor.getVendorId(),savedVendor.getVendorId());
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), CUST_EMAIL_OTHER, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertTrue(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 
					ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedVendor.getVendorId()));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertEquals(2, authenticationDetailsRepository.count());
			
			assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		}
		
	}


	@Test
	public void saveNewQuickRegisterEntity()
	{
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0,mobileVerificationDetailsRepository.count());
		
		assertEquals(0,emailVerificationDetailsRepository.count());
		
		assertEquals(0,authenticationDetailsRepository.count());
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		
		//assertEquals(standardEmailMobileCustomer(), savedEntity.getCustomerQuickRegisterEntity());
		
		//assertEquals(standardCustomerMobileVerificationDetails(), savedEntity.getCustomerMobileVerificationDetails());
		
		//assertEquals(standardCustomerEmailVerificationDetails(), savedEntity.getCustomerEmailVerificationDetails());
		
		
		
		assertEquals(1,quickRegisterRepository.count());
		
		assertEquals(1,mobileVerificationDetailsRepository.count());
		
		assertEquals(1,emailVerificationDetailsRepository.count());
		
		assertEquals(1,authenticationDetailsRepository.count());
		
	}
	
	@Test
	public void saveNewQuickRegisterEntityFailedCase()
	{
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0,mobileVerificationDetailsRepository.count());
		
		assertEquals(0,emailVerificationDetailsRepository.count());
		
		assertEquals(0,authenticationDetailsRepository.count());
		
		authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=null;
		
		try{
		savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		//assertEquals(standardEmailMobileCustomer(), savedEntity.getCustomerQuickRegisterEntity());
		
		//assertEquals(standardCustomerMobileVerificationDetails(), savedEntity.getCustomerMobileVerificationDetails());
		
		//assertEquals(standardCustomerEmailVerificationDetails(), savedEntity.getCustomerEmailVerificationDetails());
		finally
		{
		
		System.out.println(savedEntity);
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0,mobileVerificationDetailsRepository.count());
		
		assertEquals(0,emailVerificationDetailsRepository.count());
		
		assertEquals(1,authenticationDetailsRepository.count());
		}
		
	}

	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntity()
	{
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1,quickRegisterRepository.count());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
		
		assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNotNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
	}

	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntityWithFailure()
	{
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerDetailsCustomRepository.save(new CustomerDetails(215L, "ABX","A.", "ASD", null, null, CUST_MOBILE,null,null, CUST_EMAIL, null, 
				null, null, null, null, null, null, null, new Date(), new Date(), 
				ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID));
		
		
		assertEquals(1,quickRegisterRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try
		{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertNull(customerOrVendorDetailsDTO);
		
			//assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
			assertEquals(1,quickRegisterRepository.count());
		
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
		}
	}
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntity()
	{
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		assertEquals(1,quickRegisterRepository.count());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
		
		assertNotNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
	}

	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntityWithFailure()
	{
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		
		vendorDetailsCustomRepository.save(new VendorDetails(215L, "ASD","A.", "AES",null,null,null, null, CUST_MOBILE,null, false, CUST_EMAIL, false, 
				null, null, null, new Date(),new Date(),ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID));
		
		
		assertEquals(1,quickRegisterRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try
		{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(quickRegisterEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertNull(customerOrVendorDetailsDTO);
		
			//assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
			assertEquals(1,quickRegisterRepository.count());
		
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		}
	}
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntityWithQuickRec()
	{
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		//QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(0,quickRegisterRepository.count());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetailsEntity(standardEmailMobileCustomer());
		
		assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
	}
	
	
}

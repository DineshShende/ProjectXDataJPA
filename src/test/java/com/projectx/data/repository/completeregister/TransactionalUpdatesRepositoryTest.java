package com.projectx.data.repository.completeregister;


import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;
import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles={"Prod","Test"})
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
	public void updateCustomerDetailsWithNewMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_TYPE_CUSTOMER),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}

	
	@Test
	public void updateCustomerDetailsWithNewEmail()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(CUST_ID, 1, 1),
						standardCustomerDetails().getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		assertNull( emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}
	

	@Test
	public void updateCustomerWithNewSecondaryMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setSecondaryMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}

	
	
	@Test
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmail()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsSec);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_MOBILE_SEC_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		assertNull(mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}
	
	@Test
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmailWithNoRecordInRespectiveTables()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_MOBILE_SEC_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(0, mobileVerificationDetailsRepository.count());
		assertEquals(0, emailVerificationDetailsRepository.count());
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNull(mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}
	
	
	
	@Test
	//@Transactional
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmailFailWithDuplicateMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationKey(213L, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsSec);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_MOBILE_SEC_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(3, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
		try
		{
			CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertEquals(3, mobileVerificationDetailsRepository.count());
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
			assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
			assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
			
			assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
			assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
			assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
			
			
			assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
		
		}
	}
	
	@Test
	public void updateVendorDetailsWithNewMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(VENDOR_ID,CUST_TYPE_VENDER ,1),
						standardVendor().getMobile(), CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(VENDOR_ID));
	}

	
	@Test
	public void updateVendorDetailsWithNewEmail()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(standardVendor().getVendorId(), CUST_TYPE_VENDER, 1),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		assertNull( emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
	}
	
	@Test
	public void updateVendorDetailsWithNewEmailAndMobileWithNoRecordsInRespectiveTable()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		
		
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		assertNull( emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
	}

	
	@Test
	public void updateVendorWithNewMobileNewEmailFailWithDuplicateMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(standardVendor().getVendorId(), CUST_TYPE_VENDER, 1),
						standardVendor().getMobile(), CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationKey(213L, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setFirstName("RRRR");
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
		try
		{
			VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertEquals(2, mobileVerificationDetailsRepository.count());
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
			assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
			
			assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
			assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
			
			
			assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
		
		}
	}
	

	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithCustomerMobile()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertFalse(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
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
				new MobileVerificationDetails(new MobileVerificationKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertTrue(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			
			assertEquals(1, mobileVerificationDetailsRepository.count());
			
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
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), 2, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertFalse(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
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
				new MobileVerificationDetails(new MobileVerificationKey(savedVendor.getVendorId(), 2, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.findByMobile(savedMobileDetails.getMobile()));
		
		assertTrue(authenticationDetailsRepository.findByMobile(CUST_MOBILE_NEW).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		
		finally
		{
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(1, mobileVerificationDetailsRepository.count());
			
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
				new EmailVerificationDetails(new EmailVerificationKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertFalse(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
		
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
				new EmailVerificationDetails(new EmailVerificationKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), CUST_EMAIL_OTHER, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertTrue(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
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
				new EmailVerificationDetails(new EmailVerificationKey(savedVendor.getVendorId(), 2, 1),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertFalse(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		
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
				new EmailVerificationDetails(new EmailVerificationKey(savedVendor.getVendorId(), 2, 1),
						CUST_EMAIL_OTHER, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), CUST_EMAIL_OTHER, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.findByEmail(savedEmailDetails.getEmail()));
		
		assertTrue(authenticationDetailsRepository.findByEmail(CUST_EMAIL_OTHER).isPresent());
		
		try
		{
			assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
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
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
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
		
		customerDetailsCustomRepository.save(new CustomerDetails(215L, "ABX", "ASD", null, null, CUST_MOBILE,null, CUST_EMAIL, null, 
				null, null, null, null, null, null, null, null, null, null));
		
		
		assertEquals(1,quickRegisterRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try
		{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
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
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
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
		
		vendorDetailsCustomRepository.save(new VendorDetails(215L, "ASD", "AES",null, null, CUST_MOBILE, null, CUST_EMAIL, null, 
				null, null, null, null));
		
		
		assertEquals(1,quickRegisterRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try
		{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
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
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(standardEmailMobileCustomer());
		
		assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.count());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
	}
	
	@Test
	public void updateVendorWithoutQuickEntity()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count());
		
		assertEquals(0, emailVerificationDetailsRepository.count());
		
		assertEquals(0, authenticationDetailsRepository.count());
		
		
		transactionalUpdatesRepository.updateVendorDetails(standardVendor());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertEquals(1, authenticationDetailsRepository.count());
		
		
	}
}

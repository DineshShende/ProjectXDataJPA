package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

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
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.CUST_MOBILE_NEW;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_EMAIL_OTHER;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_TYPE_CUSTOMER;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_TYPE_VENDER;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
//@Transactional
public class VendorDetailsRepositoryTest {

	@Autowired
	VendorDetailsCustomRepository vendorDetailsCustomRepository; 
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
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
	public void setUp()
	{
		
		emailVerificationDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
		vendorDetailsCustomRepository.deleteAll();
		authenticationDetailsRepository.deleteAll();
		
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndFindOne()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		assertEquals(savedEntity, vendorDetailsCustomRepository.findOne(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
	}
	
	@Test
	public void updateVendorDetailsWithNewMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
				
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		VendorDetails updatedEntity=vendorDetailsCustomRepository.save(savedEntity);
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(VENDOR_ID));
	}

	
	@Test
	public void updateVendorDetailsWithNewEmail()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		VendorDetails updatedEntity=vendorDetailsCustomRepository.save(savedEntity);
		
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
		
		assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		VendorDetails updatedEntity=vendorDetailsCustomRepository.save(savedEntity);
		
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
		
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationKey(213L, CUST_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
				
		savedEntity.setFirstName("RRRR");
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		assertEquals(3, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
		try
		{
			VendorDetails updatedEntity=vendorDetailsCustomRepository.save(savedEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertEquals(3, mobileVerificationDetailsRepository.count());
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertNotNull(mobileVerificationDetailsRepository.findByMobile(standardVendor().getMobile()));
			assertNotNull(emailVerificationDetailsRepository.findByEmail(standardVendor().getEmail()));
			
			assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
			assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
			
			
			assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
		
		}
	}
		
		@Test
		public void updateVendorWithoutQuickEntityCheck()
		{
			assertEquals(0, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(0, mobileVerificationDetailsRepository.count());
			
			assertEquals(0, emailVerificationDetailsRepository.count());
			
			assertEquals(0, authenticationDetailsRepository.count());
			
			
			vendorDetailsCustomRepository.save(standardVendor());
			
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(2, mobileVerificationDetailsRepository.count());
			
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertEquals(1, authenticationDetailsRepository.count());
			
			
		}
	

	
	
	@Test
	public void count()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
	}
	
	
	@Test
	public void deleteAll()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		assertTrue(vendorDetailsCustomRepository.deleteAll());
		
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
	}
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1,vendorDetailsCustomRepository.updateEmailAndVerificationStatus(savedEntity.getVendorId(),savedEntity.getEmail(), true,
				savedEntity.getUpdatedBy(),savedEntity.getVendorId()).intValue());
	
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		//assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsEmailVerified());
	}
	
	@Test
	public void updateMobileVerificationStatus()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1,vendorDetailsCustomRepository.updateMobileAndVerificationStatus(savedEntity.getVendorId(),savedEntity.getMobile(), 
				true,savedEntity.getUpdatedBy(),savedEntity.getVendorId()).intValue());
	
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		//assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsEmailVerified());
	}
	
	
	@Test
	public void updateAddress()
	{
		assertEquals(0,vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		savedEntity.getFirmAddress().setAddressLine(standardAddressUpdated().getAddressLine());
		savedEntity.getFirmAddress().setCity(standardAddressUpdated().getCity());
		savedEntity.getFirmAddress().setDistrict(standardAddressUpdated().getDistrict());
		savedEntity.getFirmAddress().setState(standardAddressUpdated().getState());
		savedEntity.getFirmAddress().setPincode(standardAddressUpdated().getPincode());
		
		VendorDetails updatedEntity=vendorDetailsCustomRepository.save(savedEntity);
		
		assertEquals(savedEntity.getFirmAddress().getAddressId(), updatedEntity.getFirmAddress().getAddressId());
		
		assertEquals(1,vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(standardAddressUpdated(), updatedEntity.getFirmAddress());
	}

}

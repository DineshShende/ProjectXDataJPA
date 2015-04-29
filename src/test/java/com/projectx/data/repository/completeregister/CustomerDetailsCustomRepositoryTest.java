package com.projectx.data.repository.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.standardAddress;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

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
import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class CustomerDetailsCustomRepositoryTest {

	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository; 
	
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
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		emailVerificationDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
		authenticationDetailsRepository.deleteAll();
		

	}

	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), savedEntity);
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}
	
	@Test
	public void saveAndGetCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(standardCustomerDetails(), customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}

	
	@Test
	public void copiedDataFromQuickRegisterEntityAndSaveAndUpdateAfterWards()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), 
				customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails newEntityToMerge=savedEntity;
		
		newEntityToMerge.setDateOfBirth(CUST_DATE);
		newEntityToMerge.setHomeAddressId(standardCustomerDetails().getHomeAddressId());
		newEntityToMerge.setFirmAddressId(standardCustomerDetails().getFirmAddressId());
		newEntityToMerge.setLanguage(CUST_LANG);
		newEntityToMerge.setBusinessDomain(CUST_BUSINESS_DOMAIN);
		newEntityToMerge.setNameOfFirm(CUST_NAME_OF_FIRM);
		newEntityToMerge.setFirmAddressId(standardAddress());
		newEntityToMerge.setSecondaryMobile(CUST_SEC_MOBILE);
		newEntityToMerge.setSecondaryEmail(CUST_SEC_EMAIL);
		
		
		CustomerDetails mergeResult=customerDetailsCustomRepository.save(newEntityToMerge);
		
		assertEquals(newEntityToMerge, mergeResult);
	}
	
	

	@Test
	public void updateCustomerDetailsWithNewMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_TYPE_CUSTOMER),
						CUST_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID );
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
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
				new EmailVerificationDetails(new EmailVerificationKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						standardCustomerDetails().getEmail(), CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		
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
		
		savedEntity.setSecondaryMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		
		assertEquals(2, mobileVerificationDetailsRepository.count());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}

	
	
	@Test
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmail()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(standardCustomerDetails().getCustomerId());
		
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_MOBILE_SEC_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		
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
		assertEquals(2, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		
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
		
				
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationKey(213L, CUST_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
				
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_MOBILE_SEC_NEW);
		savedEntity.setEmail(CUST_EMAIL_OTHER);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(3, mobileVerificationDetailsRepository.count());
		assertEquals(1, emailVerificationDetailsRepository.count());
		
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
		assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
		
		assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
		assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
		assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
		
		
		try
		{
			CustomerDetails updatedEntity=customerDetailsCustomRepository.save(savedEntity);
		}
		catch(DataIntegrityViolationException e)
		{
			
		}
		finally
		{
			assertEquals(3, mobileVerificationDetailsRepository.count());
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count());
			
			assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE));
			assertNotNull(mobileVerificationDetailsRepository.findByMobile(CUST_SEC_MOBILE));
			assertNotNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL));
			
			assertNotNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_NEW));
			assertNull( mobileVerificationDetailsRepository.findByMobile(CUST_MOBILE_SEC_NEW));
			assertNull(emailVerificationDetailsRepository.findByEmail(CUST_EMAIL_OTHER));
			
			
			assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
		
		}
	}

	
	@Test
	public void updateMobileVerifiedStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateMobileAndVerificationStatusInMainEntity(savedEntity.getCustomerId(),standardCustomerDetails().getMobile(),
				true,CUST_UPDATED_BY,savedEntity.getCustomerId()).intValue());
		
	}
	
	
	
	@Test
	public void updateSecondaryMobileVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateSecondaryMobileAndVerificationStatusInMainEntity(savedEntity.getCustomerId(),
				savedEntity.getSecondaryMobile(), true,CUST_UPDATED_BY,savedEntity.getCustomerId()).intValue());
		
	}
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.updateEmailAndVerificationStatusInMainEntity(savedEntity.getCustomerId(), savedEntity.getEmail(),
				true,CUST_UPDATED_BY,savedEntity.getCustomerId()).intValue());
		
	}

	
}



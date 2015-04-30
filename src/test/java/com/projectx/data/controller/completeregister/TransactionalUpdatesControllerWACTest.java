package com.projectx.data.controller.completeregister;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.standardCustomerEmailVerificationDetails;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.standardEmailVerificationKey;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.standardCustomerMobileVerificationDetails;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class TransactionalUpdatesControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsCustomRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Before
	@After
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		vendorDetailsCustomRepository.deleteAll();
		authenticationDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
		emailVerificationDetailsRepository.deleteAll();
		quickRegisterRepository.deleteAll();
		
	}


	@Test
	public void environmentTest() {
		
	}


	/*
		

	*/
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetails() throws Exception
	{
		this.mockMvc.perform(
	            post("/transactional/updateMobileInDetailsEnityAndAuthenticationDetails")
	                    .content(standardJsonCustomerIdTypeMobileUpdatedByTypeDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(false))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	            
	    
	}
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetails() throws Exception
	{
		this.mockMvc.perform(
	            post("/transactional/updateEmailInDetailsEnityAndAuthenticationDetails")
	                    .content(standardJsonCustomerIdTypeMobileUpdatedByTypeDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(false))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	            
	            
	    
	}


	@Test
	public void saveNewQuickRegisterEntity() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/transactional/saveNewQuickRegisterEntity")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.result.customerQuickRegisterEntity.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.customerQuickRegisterEntity.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.customerQuickRegisterEntity.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.customerQuickRegisterEntity.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.customerQuickRegisterEntity.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.result.customerQuickRegisterEntity.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.result.customerQuickRegisterEntity.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.result.customerQuickRegisterEntity.insertTime").exists())
				.andExpect(jsonPath("$.result.customerQuickRegisterEntity.updateTime").exists())
				.andExpect(jsonPath("$.result.customerQuickRegisterEntity.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.key.customerType").value(standardCustomerMobileVerificationDetails().getKey().getCustomerType()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.key.mobileType").value(standardCustomerMobileVerificationDetails().getKey().getMobileType()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.mobilePin").doesNotExist())
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()))
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.insertTime").exists())
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.updateTime").exists())
				 .andExpect(jsonPath("$.result.customerMobileVerificationDetails.updatedBy").value(standardCustomerMobileVerificationDetails().getUpdatedBy()))
				 .andExpect(jsonPath("$.result.customerEmailVerificationDetails.key.customerType").value(standardEmailVerificationKey().getCustomerType()))
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.key.emailType").value(standardEmailVerificationKey().getEmailType()))
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.email").value(standardCustomerEmailVerificationDetails().getEmail()))
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.emailHash").doesNotExist())
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.emailHashSentTime").exists())
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.insertTime").exists())
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.updateTime").exists())
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.result.customerEmailVerificationDetails.updatedBy").value(standardCustomerEmailVerificationDetails().getUpdatedBy()))
				.andExpect(jsonPath("$.errorMessage").value(""));
	         
				
		
	    
	}
	
	@Test
	public void saveNewQuickRegisterEntityWithFailure() throws Exception
	{
		
		authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		
		
		this.mockMvc.perform(
	            post("/transactional/saveNewQuickRegisterEntity")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
		
	}
	
	
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntity() throws Exception
	{
	
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
	            post("/transactional/deleteQuickRegisterEntityCreateDetails")
	                    .content(standardJsonQuickRegisterCustomer(quickRegisterEntity))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.customerDetails.firstName").value(standardCustomerDetails().getFirstName()))
	            .andExpect(jsonPath("$.result.customerDetails.lastName").value(standardCustomerDetails().getLastName()))
	            .andExpect(jsonPath("$.result.customerDetails.homeAddressId").exists())
	            .andExpect(jsonPath("$.result.customerDetails.mobile").value(standardCustomerDetails().getMobile()))
	            .andExpect(jsonPath("$.result.customerDetails.isMobileVerified").value(standardCustomerDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.customerDetails.email").value(standardCustomerDetails().getEmail()))
	            .andExpect(jsonPath("$.result.customerDetails.isEmailVerified").value(standardCustomerDetails().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.customerDetails.language").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.firmAddressId").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.businessDomain").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.nameOfFirm").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.updatedBy").value(standardCustomerDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.customerDetails.secondaryMobile").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.isSecondaryMobileVerified").value(false))
	            .andExpect(jsonPath("$.result.customerDetails.secondaryEmail").doesNotExist())
	            .andExpect(jsonPath("$.result.customerDetails.insertTime").exists())
	            .andExpect(jsonPath("$.result.customerDetails.updateTime").exists())
	            .andExpect(jsonPath("$.result.vendorDetails").doesNotExist())
	            .andExpect(jsonPath("$.errorMessage").value(""));		

	    
		
	}
	
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntityFailure() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerDetailsCustomRepository.save(new CustomerDetails(215L, "ABX","A.", "ASD", null, null, CUST_MOBILE,null,false, CUST_EMAIL, false, 
				null, null, null, null, null, null, null, new Date(), new Date(), 
				ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID));
		
		
		this.mockMvc.perform(
	            post("/transactional/deleteQuickRegisterEntityCreateDetails")
	                    .content(standardJsonQuickRegisterCustomer(quickRegisterEntity))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
		/*
                .andExpect(jsonPath("$.vendorDetails").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails").doesNotExist());
	            	
		 */
	    
		
	}
	

	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntity() throws Exception
	{
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		this.mockMvc.perform(
	            post("/transactional/deleteQuickRegisterEntityCreateDetails")
	                    .content(standardJsonQuickRegisterCustomer(quickRegisterEntity))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.vendorDetails.firstName").value(standardEmailMobileVendor().getFirstName()))
		 		.andExpect(jsonPath("$.result.vendorDetails.lastName").value(standardEmailMobileVendor().getLastName()))
		 		.andExpect(jsonPath("$.result.vendorDetails.firmAddress").doesNotExist())
	            .andExpect(jsonPath("$.result.vendorDetails.mobile").value(standardEmailMobileVendor().getMobile()))
	            .andExpect(jsonPath("$.result.vendorDetails.isMobileVerified").value(standardEmailMobileVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.vendorDetails.email").value(standardEmailMobileVendor().getEmail()))
	            .andExpect(jsonPath("$.result.vendorDetails.isEmailVerified").value(standardEmailMobileVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.vendorDetails.laguage").doesNotExist())
	            .andExpect(jsonPath("$.result.vendorDetails.updatedBy").value(standardEmailMobileVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.vendorDetails.insertTime").exists())
	            .andExpect(jsonPath("$.result.vendorDetails.updateTime").exists())
	            .andExpect(jsonPath("$.result.customerDetails").doesNotExist())
	            .andExpect(jsonPath("$.errorMessage").value(""));
	            		
		
	    
		
	}

	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntityFailure() throws Exception
	{
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		vendorDetailsCustomRepository.save(new VendorDetails(215L, "ASD","A.", "AES",null,null,null, null, CUST_MOBILE,null, false, CUST_EMAIL, false, 
				null, null, null,new Date(),new Date(), ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID));
		
		
		
		this.mockMvc.perform(
	            post("/transactional/deleteQuickRegisterEntityCreateDetails")
	                    .content(standardJsonQuickRegisterCustomer(quickRegisterEntity))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
		/*
                .andExpect(jsonPath("$.vendorDetails").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails").doesNotExist());
	    */        
	    
		
	}
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntityWithOutQuickRec() throws Exception
	{
		
		//QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		this.mockMvc.perform(
	            post("/transactional/deleteQuickRegisterEntityCreateDetails")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendorDetails").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails").doesNotExist());
	            		
		
	    
		
	}

}

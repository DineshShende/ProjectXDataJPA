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
	            .andExpect(content().string("false"));
	            
	    
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
	            .andExpect(content().string("false"));
	            
	    
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
	            .andExpect(jsonPath("$.customerQuickRegisterEntity.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.customerQuickRegisterEntity.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.customerQuickRegisterEntity.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.customerQuickRegisterEntity.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.customerQuickRegisterEntity.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.customerQuickRegisterEntity.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.customerQuickRegisterEntity.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.customerQuickRegisterEntity.insertTime").exists())
				.andExpect(jsonPath("$.customerQuickRegisterEntity.updateTime").exists())
				.andExpect(jsonPath("$.customerQuickRegisterEntity.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.key.customerType").value(standardCustomerMobileVerificationDetails().getKey().getCustomerType()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.key.mobileType").value(standardCustomerMobileVerificationDetails().getKey().getMobileType()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.mobilePin").doesNotExist())
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()))
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.insertTime").exists())
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.updateTime").exists())
				 .andExpect(jsonPath("$.customerMobileVerificationDetails.updatedBy").value(standardCustomerMobileVerificationDetails().getUpdatedBy()))
				 .andExpect(jsonPath("$.customerEmailVerificationDetails.key.customerType").value(standardEmailVerificationKey().getCustomerType()))
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.key.emailType").value(standardEmailVerificationKey().getEmailType()))
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.email").value(standardCustomerEmailVerificationDetails().getEmail()))
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.emailHash").doesNotExist())
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.emailHashSentTime").exists())
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.insertTime").exists())
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.updateTime").exists())
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.customerEmailVerificationDetails.updatedBy").value(standardCustomerEmailVerificationDetails().getUpdatedBy()));
	         
				
		
	    
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
                .andExpect(jsonPath("$.customerDetails.firstName").value(standardCustomerDetails().getFirstName()))
	            .andExpect(jsonPath("$.customerDetails.lastName").value(standardCustomerDetails().getLastName()))
	            .andExpect(jsonPath("$.customerDetails.homeAddressId").exists())
	            .andExpect(jsonPath("$.customerDetails.mobile").value(standardCustomerDetails().getMobile()))
	            .andExpect(jsonPath("$.customerDetails.isMobileVerified").value(standardCustomerDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.customerDetails.email").value(standardCustomerDetails().getEmail()))
	            .andExpect(jsonPath("$.customerDetails.isEmailVerified").value(standardCustomerDetails().getIsEmailVerified()))
	            .andExpect(jsonPath("$.customerDetails.language").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.firmAddressId").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.businessDomain").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.nameOfFirm").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.updatedBy").value(standardCustomerDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.customerDetails.secondaryMobile").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.isSecondaryMobileVerified").value(false))
	            .andExpect(jsonPath("$.customerDetails.secondaryEmail").doesNotExist())
	            .andExpect(jsonPath("$.customerDetails.insertTime").exists())
	            .andExpect(jsonPath("$.customerDetails.updateTime").exists())
	            .andExpect(jsonPath("$.vendorDetails").doesNotExist());		

	    
		
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
                .andExpect(jsonPath("$.vendorDetails.firstName").value(standardEmailMobileVendor().getFirstName()))
		 		.andExpect(jsonPath("$.vendorDetails.lastName").value(standardEmailMobileVendor().getLastName()))
		 		.andExpect(jsonPath("$.vendorDetails.firmAddress").doesNotExist())
	            .andExpect(jsonPath("$.vendorDetails.mobile").value(standardEmailMobileVendor().getMobile()))
	            .andExpect(jsonPath("$.vendorDetails.isMobileVerified").value(standardEmailMobileVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.vendorDetails.email").value(standardEmailMobileVendor().getEmail()))
	            .andExpect(jsonPath("$.vendorDetails.isEmailVerified").value(standardEmailMobileVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.vendorDetails.laguage").doesNotExist())
	            .andExpect(jsonPath("$.vendorDetails.updatedBy").value(standardEmailMobileVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.vendorDetails.insertTime").exists())
	            .andExpect(jsonPath("$.vendorDetails.updateTime").exists())
	            .andExpect(jsonPath("$.customerDetails").doesNotExist());
	            		
		
	    
		
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

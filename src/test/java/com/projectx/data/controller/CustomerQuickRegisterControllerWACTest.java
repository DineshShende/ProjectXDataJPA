package com.projectx.data.controller;

import static org.junit.Assert.*;

import javax.net.ssl.SSLEngineResult.Status;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;
import static com.projectx.data.fixtures.CustomerAuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.CustomerEmailVerificationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.CustomerMobileVericationDetailsFixtures.*;

import com.projectx.data.config.Application;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Prod")
public class CustomerQuickRegisterControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
	}
	
	
	@Test
	public void saveNewEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister/responseEntity")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
		/*
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
			
				*/
				 
	}
	
	@Test
	public void saveNewEmailMobileCustomerWithErrors() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	}
	            


	/*
	@Test
	public void getCustomerByCustomerId() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/getEntityByCustomerId")
	                    .content(standardJsonCustomerId(CUST_ID))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").doesNotExist())
	            .andExpect(jsonPath("$.lastName").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.pincode").doesNotExist())
				.andExpect(jsonPath("$.isEmailVerified").doesNotExist())
				.andExpect(jsonPath("$.isMobileVerified").doesNotExist())
				.andExpect(jsonPath("$.insertTime").doesNotExist())
				.andExpect(jsonPath("$.updateTime").doesNotExist())
				.andExpect(jsonPath("$.updatedBy").doesNotExist());

	}
	
	
	@Test
	public void saveNewEmailMobileCustomerWithErrors() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomerWithErrors()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").doesNotExist())
	            .andExpect(jsonPath("$.lastName").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.pincode").doesNotExist())
				.andExpect(jsonPath("$.isEmailVerified").doesNotExist())
				.andExpect(jsonPath("$.isMobileVerified").doesNotExist())
				.andExpect(jsonPath("$.insertTime").doesNotExist())
				.andExpect(jsonPath("$.updateTime").doesNotExist())
				.andExpect(jsonPath("$.updatedBy").doesNotExist());
				 
	}

	
	@Test
	public void saveNewEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
				 
	}

	
	
	
	@Test
	public void saveNewEmailCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailCustomer().getUpdatedBy()));			
	
	}

	@Test
	public void saveNewMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardMobileCustomer().getUpdatedBy()));
				
	}

	
	@Test
	public void getAllCustomerWithEmaiMobileAndEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomerOther()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
	
		this.mockMvc.perform(
				get("/customer/quickregister/getAll"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].['firstName']").value(standardEmailMobileCustomer().getFirstName()))
					.andExpect(jsonPath("$[0].['lastName']").value(standardEmailMobileCustomer().getLastName()))
					.andExpect(jsonPath("$[0].['mobile']").value(standardEmailMobileCustomer().getMobile()))
					.andExpect(jsonPath("$[0].['email']").value(standardEmailMobileCustomer().getEmail()))
					.andExpect(jsonPath("$[0].['pincode']").value(standardEmailMobileCustomer().getPincode()))
					.andExpect(jsonPath("$[0].['isEmailVerified']").value(standardEmailMobileCustomer().getIsEmailVerified()))
					.andExpect(jsonPath("$[0].['isMobileVerified']").value(standardEmailMobileCustomer().getIsMobileVerified()))
					.andExpect(jsonPath("$[0].['insertTime']").exists())
					.andExpect(jsonPath("$[0].['updateTime']").exists())
					.andExpect(jsonPath("$[0].['updatedBy']").value(standardEmailMobileCustomer().getUpdatedBy()))
				
					.andExpect(jsonPath("$[1].['firstName']").value(standardEmailCustomer().getFirstName()))
					.andExpect(jsonPath("$[1].['lastName']").value(standardEmailCustomer().getLastName()))
					.andExpect(jsonPath("$[1].['mobile']").doesNotExist())
					.andExpect(jsonPath("$[1].['email']").value(CUST_EMAIL_OTHER))
					.andExpect(jsonPath("$[1].['pincode']").value(standardEmailCustomer().getPincode()))
					.andExpect(jsonPath("$[1].['isEmailVerified']").value(standardEmailCustomer().getIsEmailVerified()))
					.andExpect(jsonPath("$[1].['isMobileVerified']").value(standardEmailCustomer().getIsMobileVerified()))
					.andExpect(jsonPath("$[1].['insertTime']").exists())
					.andExpect(jsonPath("$[1].['updateTime']").exists())
					.andExpect(jsonPath("$[1].['updatedBy']").value(standardEmailCustomer().getUpdatedBy()));
				
		
					
		
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobileWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
	
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailWithEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailCustomer().getUpdatedBy()));			
	
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByMobileWithMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
		
	@Test
	public void clearTestingData() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()));
		
		this.mockMvc.perform(
				get("/customer/quickregister/clearForTesting"))
					.andDo(print());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").doesNotExist());
	
	}

	
	@Test
	public void saveVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailMobileAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailMobileAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerEmailMobileAuthenticationDetails().getLastUnsucessfullAttempts()));
		
		
	}
	
	
	@Test
	public void saveVerificationDetailsWithEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailAuthenticationDetails().getMobile()))
	           // .andExpect(jsonPath("$.password").value(null))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerEmailAuthenticationDetails().getLastUnsucessfullAttempts()));
		
		
	}

	
	@Test
	public void saveVerificationDetailsWithMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerMobileAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerMobileAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerMobileAuthenticationDetails().getLastUnsucessfullAttempts()));
		
		
	}
	

	@Test
	public void getLoginDetailsByEmailWithEmailAuthenticationEntity() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailAuthenticationDetails().getMobile()))
	          //  .andExpect(jsonPath("$.password").value(standardCustomerEmailAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailAuthenticationDetails().getPasswordType()))
				.andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailAuthenticationDetails().getEmailPassword()))
		        .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailAuthenticationDetails().getResendCount()))
		        .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerEmailAuthenticationDetails().getLastUnsucessfullAttempts()));
	}
	
	@Test
	public void getLoginDetailsByMobileWithMobileAuthenticationEntity() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerMobileAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerMobileAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerMobileAuthenticationDetails().getLastUnsucessfullAttempts()));
	}
	
	@Test
	public void getLoginDetailsByEmailAndMobileWithEmailMobileAuthenticationEntity() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailMobileAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailMobileAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerEmailMobileAuthenticationDetails().getLastUnsucessfullAttempts()));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
	            .andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailMobileAuthenticationDetails().getEmailPassword()))
	            .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailMobileAuthenticationDetails().getResendCount()))
	            .andExpect(jsonPath("$.lastUnsucessfullAttempts").value(standardCustomerEmailMobileAuthenticationDetails().getLastUnsucessfullAttempts()));
	}
	
	
	@Test
	public void loginDetailsCount() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(get("/customer/quickregister/customerAuthentication/loginDetailsCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}
	
	
	@Test
	public void saveEmailVerificationEntity() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
	          .andExpect(jsonPath("$.emailType").value(standardCustomerEmailVerificationDetails().getEmailType()))
	          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
	          .andExpect(jsonPath("$.emailHashSentTime").exists())
	          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()));
	            
	}
	
	@Test
	public void getEmailVerificationDetailsByCustomerIdAndEmail() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
	          .andExpect(jsonPath("$.emailType").value(standardCustomerEmailVerificationDetails().getEmailType()))
	          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
	          .andExpect(jsonPath("$.emailHashSentTime").exists())
	          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()));

	}
	
	
	@Test
	//@Rollback(value=false)
	public void resetEmailHashAndEmailHashSentTime() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
	          .andExpect(jsonPath("$.emailType").value(standardCustomerEmailVerificationDetails().getEmailType()))
	          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
	          .andExpect(jsonPath("$.emailHashSentTime").exists())
	          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()));

		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/resetEmailHashAndEmailHashSentTime")
	                    .content(standardJsonUpdateEmailHashAndEmailHashSentTimeAndResendCountDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	

	@Test
	public void incrementResendCountByCustomerIdAndEmail() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
	          .andExpect(jsonPath("$.emailType").value(standardCustomerEmailVerificationDetails().getEmailType()))
	          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
	          .andExpect(jsonPath("$.emailHashSentTime").exists())
	          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()));

		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/incrementResendCountByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	@Test
	public void getEmailVerificationCount() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}
	
	
	@Test
	public void saveMobileVerificationEntity() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	             .andDo(print())
	            .andExpect(status().isOk())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	          .andExpect(jsonPath("$.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
	          .andExpect(jsonPath("$.mobileType").value(standardCustomerMobileVerificationDetails().getMobileType()))
	          .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
	          .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
	          .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()));
	            
	}
	
	
	@Test
	public void getMobikeVerificationDetailsByCustomerIdAndMobile() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
		          .andExpect(jsonPath("$.mobileType").value(standardCustomerMobileVerificationDetails().getMobileType()))
		          .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
		          .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
		          .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()));
		          
	}

	@Test
	public void updateMobilePinAndMobileVerificationAttemptsAndResendCount() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/updateMobilePinAndMobileVerificationAttemptsAndResendCount")
	                    .content(standardJsonUpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	@Test
	public void incrementMobileVerificationAttempts() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/incrementMobileVerificationAttempts")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	@Test
	public void incrementResendCount() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/incrementResendCount")
	                    .content(standardJsonCustomerIdMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	@Test
	public void mobileVerificationCount() throws Exception
	{

		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
	
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}
	*/
	
}

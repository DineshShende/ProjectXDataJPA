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
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	

	@Test
	public void saveNewEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pin").value(standardEmailMobileCustomer().getPin()))
				.andExpect(jsonPath("$.status").value(standardEmailMobileCustomer().getStatus()))
				.andExpect(jsonPath("$.mobilePin").value(standardEmailMobileCustomer().getMobilePin()))
			    .andExpect(jsonPath("$.emailHash").value(standardEmailMobileCustomer().getEmailHash()))
				.andExpect(jsonPath("$.mobileVerificationAttempts").value(standardEmailMobileCustomer().getMobileVerificationAttempts()))
				.andExpect(jsonPath("$.mobilePinSentTime").doesNotExist())
				.andExpect(jsonPath("$.emailHashSentTime").doesNotExist())
				.andExpect(jsonPath("$.lastStatusChangedTime").value(standardEmailMobileCustomer().getLastStatusChangedTime().getTime()))
				.andExpect(jsonPath("$.password").value(standardEmailMobileCustomer().getPassword()))
				.andExpect(jsonPath("$.passwordType").value(standardEmailMobileCustomer().getPasswordType()));
		
	}

	@Test
	public void saveNewEmailCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.pin").value(standardEmailCustomer().getPin()))
				.andExpect(jsonPath("$.status").value(standardEmailCustomer().getStatus()))
				.andExpect(jsonPath("$.mobilePin").doesNotExist())
			    .andExpect(jsonPath("$.emailHash").value(standardEmailCustomer().getEmailHash()))
				.andExpect(jsonPath("$.mobileVerificationAttempts").value(standardEmailCustomer().getMobileVerificationAttempts()))
				.andExpect(jsonPath("$.mobilePinSentTime").doesNotExist())
				.andExpect(jsonPath("$.emailHashSentTime").doesNotExist())
				.andExpect(jsonPath("$.lastStatusChangedTime").value(standardEmailCustomer().getLastStatusChangedTime().getTime()))
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.passwordType").doesNotExist());
	
	}

	@Test
	public void saveNewMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.pin").value(standardMobileCustomer().getPin()))
				.andExpect(jsonPath("$.status").value(standardMobileCustomer().getStatus()))
				.andExpect(jsonPath("$.mobilePin").value(standardMobileCustomer().getMobilePin()))
			    .andExpect(jsonPath("$.emailHash").doesNotExist())
				.andExpect(jsonPath("$.mobileVerificationAttempts").value(standardMobileCustomer().getMobileVerificationAttempts()))
				.andExpect(jsonPath("$.mobilePinSentTime").doesNotExist())
				.andExpect(jsonPath("$.emailHashSentTime").doesNotExist())
				.andExpect(jsonPath("$.lastStatusChangedTime").value(standardMobileCustomer().getLastStatusChangedTime().getTime()))
				.andExpect(jsonPath("$.password").value(standardMobileCustomer().getPassword()))
				.andExpect(jsonPath("$.passwordType").value(standardMobileCustomer().getPasswordType()));
	
	
	}

	
	@Test
	public void getAllCustomerWithEmaiMobileAndMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailCustomerOther())
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
					.andExpect(jsonPath("$[0].['pin']").value(standardEmailMobileCustomer().getPin()))
					.andExpect(jsonPath("$[0].['status']").value(standardEmailMobileCustomer().getStatus()))
					.andExpect(jsonPath("$[0].['mobilePin']").value(standardEmailMobileCustomer().getMobilePin()))
					.andExpect(jsonPath("$[0].['emailHash']").value(standardEmailMobileCustomer().getEmailHash()))
					.andExpect(jsonPath("$[0].['mobileVerificationAttempts']").value(standardEmailMobileCustomer().getMobileVerificationAttempts()))
					.andExpect(jsonPath("$[0].['mobilePinSentTime']").doesNotExist())
					.andExpect(jsonPath("$[0].['emailHashSentTime']").doesNotExist())
					.andExpect(jsonPath("$[0].['lastStatusChangedTime']").value(standardEmailMobileCustomer().getLastStatusChangedTime().getTime()))
					.andExpect(jsonPath("$[0].['password']").value(standardEmailMobileCustomer().getPassword()))
					.andExpect(jsonPath("$[0].['passwordType']").value(standardEmailMobileCustomer().getPasswordType()))
		
					.andExpect(jsonPath("$[1].['firstName']").value(standardEmailCustomer().getFirstName()))
					.andExpect(jsonPath("$[1].['lastName']").value(standardEmailCustomer().getLastName()))
					.andExpect(jsonPath("$[1].['mobile']").doesNotExist())
					.andExpect(jsonPath("$[1].['email']").value(CUST_EMAIL_OTHER))
					.andExpect(jsonPath("$[1].['pin']").value(standardEmailCustomer().getPin()))
					.andExpect(jsonPath("$[1].['status']").value(standardEmailCustomer().getStatus()))
					.andExpect(jsonPath("$[1].['mobilePin']").doesNotExist())
					.andExpect(jsonPath("$[1].['emailHash']").value(standardEmailCustomer().getEmailHash()))
					.andExpect(jsonPath("$[1].['mobileVerificationAttempts']").value(standardEmailCustomer().getMobileVerificationAttempts()))
					.andExpect(jsonPath("$[1].['mobilePinSentTime']").doesNotExist())
					.andExpect(jsonPath("$[1].['emailHashSentTime']").doesNotExist())
					.andExpect(jsonPath("$[1].['lastStatusChangedTime']").value(standardEmailCustomer().getLastStatusChangedTime().getTime()))
					.andExpect(jsonPath("$[1].['password']").doesNotExist())
					.andExpect(jsonPath("$[1].['passwordType']").doesNotExist());
		
					
		
		
	}
	
	@Test
	public void getEmailCountWithEmaiMobileCustomer() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getEmailCount")
	                    .content(standardJsonEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	
	}
	
	@Test
	public void getEmailCountWithOutAnyCustomer() throws Exception
	{

		this.mockMvc.perform(
	            post("/customer/quickregister/getEmailCount")
	                    .content(standardJsonEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(content().string("0"));
		
	}
	

	@Test
	public void getMobileCountWithEmaiMobileCustomer() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getMobileCount")
	                    .content(standardJsonMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	
	}
	
	@Test
	public void getMobileCountWithOutAnyCustomer() throws Exception
	{

		this.mockMvc.perform(
	            post("/customer/quickregister/getMobileCount")
	                    .content(standardJsonMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(content().string("0"));
		
	}

	
	@Test
	public void clearTestingData() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getEmailCount")
	                    .content(standardJsonEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
		
		this.mockMvc.perform(
				get("/customer/quickregister/clearForTesting"))
					.andDo(print());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/getEmailCount")
	                    .content(standardJsonEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
	
	}

	
	@Test
	public void saveVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		
		
	}
	
	@Test
	public void saveVerificationDetailsWithEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		
		
	}

	
	@Test
	public void saveVerificationDetailsWithMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	         //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		
		
	}
	
	
	
	@Test
	public void verifyLoginDetailsWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithEmail()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		
	    
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithMobile()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		
	}
	
	@Test
	public void verifyLoginDetailsWithEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithEmail()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
		

		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithMobile()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.password").doesNotExist())
	            .andExpect(jsonPath("$.passwordType").doesNotExist());

	}
	
	
	@Test
	public void verifyLoginDetailsWithMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithMobile()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	       //   .andExpect(jsonPath("$.customerId").value(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.password").value(standardCustomerMobileAuthenticationDetails().getPassword()))
	            .andExpect(jsonPath("$.passwordType").value(standardCustomerMobileAuthenticationDetails().getPasswordType()));
		

		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithEmail()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.password").doesNotExist())
	            .andExpect(jsonPath("$.passwordType").doesNotExist());

	}
	
	
	@Test
	public void updatePasswordWithMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithEmailNewPassword()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.password").doesNotExist())
	            .andExpect(jsonPath("$.passwordType").doesNotExist());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updatePassword")
	                    .content(standardJsonUpdatePasswordAndPasswordType())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           .andDo(print())
	           .andExpect(status().isOk())
			   .andExpect(content().string("1"));
	                    
		

		
	}	
	
	@Test
	public void loginDetailsCount() throws Exception
	{
		this.mockMvc.perform(
					get("/customer/quickregister/loginDetailsCount")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				get("/customer/quickregister/loginDetailsCount")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	
	}
	
	
	@Test
	public void clearLoginDetailsForTesting() throws Exception
	{
		this.mockMvc.perform(
							get("/customer/quickregister/loginDetailsCount")
							)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("0"));
	
		this.mockMvc.perform(
							post("/customer/quickregister/saveLoginDetails")
                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerMobileAuthenticationDetails()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
	
			
		this.mockMvc.perform(
							get("/customer/quickregister/loginDetailsCount")
							)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("1"));
	
		this.mockMvc.perform(
							get("/customer/quickregister/clearLoginDetailsForTesting")
							)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("true"));
	
		
		this.mockMvc.perform(
							get("/customer/quickregister/loginDetailsCount")
							)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("0"));

	}
	
	
	@Test
	public void getLoginDetailsByCustomerId() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
					post("/customer/quickregister/getLoginDetailsByCustomerId")
						.content(standardJsonCustomerIdForLoginDetails(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
        .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
        .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
        .andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));

	
	}

	@Test
	public void getLoginDetailsByCustomerIdFailingCase() throws Exception
	{
		
		
		this.mockMvc.perform(
					post("/customer/quickregister/getLoginDetailsByCustomerId")
						.content(standardJsonCustomerIdForLoginDetails(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.email").doesNotExist())
        .andExpect(jsonPath("$.mobile").doesNotExist())
        .andExpect(jsonPath("$.password").doesNotExist())
        .andExpect(jsonPath("$.passwordType").doesNotExist());

	
	}
	
}

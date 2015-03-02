package com.projectx.data.controller.quickregister;

import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@Transactional
@ActiveProfiles(value="Prod")
public class AuthenticationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository; 
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@Before
	
	public void clearTestData() throws Exception
	{
		authenticationDetailsRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
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
	            .andExpect(status().isCreated())
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
	public void saveVerificationDetailsWithEmailMobileCustomerWithErrors() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetailsWithError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
		
	}
	
	@Test
	public void saveVerificationDetailsWithEmailMobileCustomerWithAlreadyReported() throws Exception
	{
		
		authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());

		authenticationDetailsRepository.count();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
		
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
	            .andExpect(status().isCreated())
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
	            .andExpect(status().isCreated())
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
		authenticationDetailsRepository.deleteAll();		
		
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
	            .andExpect(status().isFound())
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
		
		authenticationDetailsRepository.deleteAll();
		
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
	            .andExpect(status().isFound())
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
		authenticationDetailsRepository.deleteAll();
		
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
	            .andExpect(status().isFound())
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
	            .andExpect(status().isFound())
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
	public void updatePasswordAndPasswordTypeAndCountsWithEmailPassword() throws Exception
	{

		authenticationDetailsRepository.deleteAll();
		
		AuthenticationDetails authenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.count();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/updatePasswordEmailPasswordAndPasswordTypeAndCounts")
	                    .content(standardJsonUpdatePasswordEmailPasswordAndPasswordType(standardUpdatePasswordEmailPasswordTypeWithEmailPass(authenticationDetails.getKey().getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCountsWithPass() throws Exception
	{
		
		authenticationDetailsRepository.deleteAll();
		
		AuthenticationDetails authenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.count();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/updatePasswordEmailPasswordAndPasswordTypeAndCounts")
	                    .content(standardJsonUpdatePasswordEmailPasswordAndPasswordType(standardUpdatePasswordEmailPasswordTypeWithPass(authenticationDetails.getKey().getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	@Test
	public void incrementResendCount() throws Exception
	{
		
		authenticationDetailsRepository.deleteAll();
		
		AuthenticationDetails authenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.count();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementResendCount")
	                    .content(standardJsonCustomerIdTypeUpdatedBy(standardCustomerIdTypeUpdatedByDTO(authenticationDetails.getKey().getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	@Test
	public void incrementLastUnsucessfullAttempts() throws Exception
	{
		authenticationDetailsRepository.deleteAll();
		
		AuthenticationDetails authenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.count();
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts")
	                    .content(standardJsonCustomerIdTypeUpdatedBy(standardCustomerIdTypeUpdatedByDTO(authenticationDetails.getKey().getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	@Test
	public void loginDetailsCount() throws Exception
	{
		authenticationDetailsRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(get("/customer/quickregister/customerAuthentication/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}
	
	@Test
	public void clearTestDataTest() throws Exception
	{
		authenticationDetailsRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/saveLoginDetails")
	                    .content(standardJsonCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(get("/customer/quickregister/customerAuthentication/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
		

		this.mockMvc.perform(get("/customer/quickregister/customerAuthentication/clearForTesting"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
		
		this.mockMvc.perform(get("/customer/quickregister/customerAuthentication/getCount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("0"));

	}
	

}

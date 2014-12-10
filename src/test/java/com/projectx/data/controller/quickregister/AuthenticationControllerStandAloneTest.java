package com.projectx.data.controller.quickregister;

import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardCustomerId;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;

public class AuthenticationControllerStandAloneTest {

	
	@InjectMocks
	AuthenticationController authenticationController;
	
	@Mock
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	
	private static final Integer ZERO_COUNT=0;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(authenticationController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}

	
	@Test
	public void getLoginDetailsByCustomerId() throws Exception
	{
		//Optional<AuthenticationDetails> optional=Optional.of(standardCustomerEmailAuthenticationDetails());
		
		when(customerAuthenticationDetailsRepository.findOne(standardAuthenticationDetailsKey())).thenReturn(standardCustomerEmailAuthenticationDetails());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByCustomerIdType")
	                    .content(standardJsonCustomerIdType(standardCustomerIdTypeDTO()))
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
	public void updatePasswordAndPasswordTypeAndCounts() throws Exception
	{
		when(customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts
				(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),standardUpdatePasswordAndPasswordTypeDTO().getCustomerType(),
						standardUpdatePasswordAndPasswordTypeDTO().getPassword(),
						standardUpdatePasswordAndPasswordTypeDTO().getPasswordType(), ZERO_COUNT, ZERO_COUNT)).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/updatePasswordAndPasswordTypeAndCounts")
	                    .content(standardJsonUpdatePasswordAndPasswordType())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCounts() throws Exception
	{
		when(customerAuthenticationDetailsRepository.updateEmailPasswordAndPasswordTypeAndCounts
				(standardUpdateEmailPassword().getCustomerId(),standardUpdateEmailPassword().getCustomerType(), standardUpdateEmailPassword().getEmailPassword(),
						"Default", ZERO_COUNT, ZERO_COUNT)).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/updateEmailPasswordAndPasswordTypeAndCounts")
	                    .content(standardJsonUpdateEmailPassword())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	@Test
	public void incrementResendCount() throws Exception
	{
		when(customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerId().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType())).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementResendCount")
	                    .content(standardJsonCustomerIdType(standardCustomerIdTypeDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	@Test
	public void incrementLastUnsucessfullAttempts() throws Exception
	{
		when(customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerIdTypeDTO().getCustomerId(),standardCustomerIdTypeDTO().getCustomerType())).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts")
	                    .content(standardJsonCustomerIdType(standardCustomerIdTypeDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
}

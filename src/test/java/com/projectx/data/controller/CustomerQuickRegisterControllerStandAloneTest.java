package com.projectx.data.controller;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;
import static com.projectx.data.fixtures.CustomerAuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.CustomerMobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.CustomerEmailVerificationDetailsDataFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;












import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.data.domain.CustomerAuthenticationDetails;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.data.repository.CustomerQuickRegisterRepository;

public class CustomerQuickRegisterControllerStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Mock
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	private static final Integer ZERO_COUNT=0;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	}

	
	@Test
	public void getCustomerByCustomerId() throws Exception
	{
		Optional<CustomerQuickRegisterEntity> customerOptional=Optional.of(standardEmailMobileCustomer());
		
				
		when(customerQuickRegisterRepository.findByCustomerId(standardCustomerId().getCustomerId())).thenReturn(customerOptional);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getEntityByCustomerId")
	                    .content(standardJsonCustomerId(CUST_ID))
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
	public void updateMobileVerificationStatus() throws Exception
	{
		when(customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy())).thenReturn(new Integer(1));
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateMobileVerificationStatus")
	                    .content(standardJsonUpdateEmailMobileVerificationStatus())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	}
	
	
	@Test
	public void updateEmailVerificationStatus() throws Exception
	{
		when(customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy())).thenReturn(new Integer(1));
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateEmailVerificationStatus")
	                    .content(standardJsonUpdateEmailMobileVerificationStatus())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	}
	
	
	@Test
	public void getLoginDetailsByCustomerId() throws Exception
	{
		when(customerAuthenticationDetailsRepository.findByCustomerId(CUST_ID)).thenReturn(standardCustomerEmailAuthenticationDetails());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/getLoginDetailsByCustomerId")
	                    .content(standardJsonCustomerId(CUST_ID))
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
				(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(), standardUpdatePasswordAndPasswordTypeDTO().getPassword(),
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
				(standardUpdateEmailPassword().getCustomerId(), standardUpdateEmailPassword().getEmailPassword(),
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
		when(customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerId().getCustomerId())).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementResendCount")
	                    .content(standardJsonCustomerId(CUST_ID))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	@Test
	public void incrementLastUnsucessfullAttempts() throws Exception
	{
		when(customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerId().getCustomerId())).thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts")
	                    .content(standardJsonCustomerId(CUST_ID))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	

}
package com.projectx.data.controller.quickregister;

import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
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

import com.projectx.data.controller.quickregister.QuickRegisterController;
import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;

public class QuickRegisterControllerStandAloneTest {

	@InjectMocks
	QuickRegisterController customerQuickRegisterController;
	
	@Mock
	QuickRegisterRepository customerQuickRegisterRepository;
	
	@Mock
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
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
		Optional<QuickRegisterEntity> customerOptional=Optional.of(standardEmailMobileCustomer());
		
				
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
	
	

	
	

}
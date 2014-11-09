package com.projectx.data.controller;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;




import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.data.repository.CustomerQuickRegisterRepository;

public class CustomerQuickRegisterControllerStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
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
		when(customerQuickRegisterRepository.findByCustomerId(standardCustomerId().getCustomerId())).thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getEntityByCustomerId")
	                    .content(standardJsonCustomerId(CUST_ID))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(CUST_FIRSTNAME))
	            .andExpect(jsonPath("$.lastName").value(CUST_LASTNAME))
	            //.andExpect(jsonPath("$.mobile").value(CUST_MOBILE))
	            .andExpect(jsonPath("$.email").value(CUST_EMAIL))
	            .andExpect(jsonPath("$.pin").value(CUST_PIN))
				.andExpect(jsonPath("$.status").value(CUST_STATUS_EMAILMOBILE))
				.andExpect(jsonPath("$.mobilePin").value(CUST_MOBILEPIN));
			  //.andExpect(jsonPath("$.emailHash").value(CUST_EMAILHASH));
	}
	

	
	@Test
	public void updateStatusByCustomerId() throws Exception
	{
		when(customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId().getCustomerId(),
				standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId().getStatus(),standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId().getStatusChangeTime(),standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId().getMobileVerificationAttempts())).thenReturn(1);
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/updateStatusAndMobileVerificationAttempts")
	                    .content(standardJsonUpdateStatusAndMobileVerficationAttemptsByCustomerIdDTO(standardUpdateStatusAndMobileVerificationAttemptsWithCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}
	
	
	@Test
	public void updateEmailHash() throws Exception
	{
		when(customerQuickRegisterRepository.updateEmailHash(standardUpdateEmailHashDTO().getCustomerId(),
					standardUpdateEmailHashDTO().getEmailHash(),standardUpdateEmailHashDTO().getUpdateTime())).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateEmailHash")
	                    .content(standardJsonUpdateEmailHashDTO(standardUpdateEmailHashDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}

	
	@Test
	public void updateMobilePin() throws Exception
	{
		when(customerQuickRegisterRepository.updateMobilePin(standardUpdateMobilePinDTO().getCustomerId(),
				standardUpdateMobilePinDTO().getMobilePin(),standardUpdateMobilePinDTO().getUpdateTime())).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateMobilePin")
	                    .content(standardJsonUpdateMobilePinDTO(standardUpdateMobilePinDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}


	@Test
	public void updateEmailHashAndMobilePinSentTime() throws Exception
	{
		when(customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(standardUpdateEmailHashAndMobilePinSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndMobilePinSentTimeDTO().getEmailSentTime(),standardUpdateEmailHashAndMobilePinSentTimeDTO().getMobilePinSentTime())).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateEmailHashAndMobilePinSentTime")
	                    .content(standardJsonUpdateEmailHashAndMobilePinSentTimeDTO(standardUpdateEmailHashAndMobilePinSentTimeDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}


	

}

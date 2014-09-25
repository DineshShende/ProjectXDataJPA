package com.projectx.data.controller;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.when;

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
	                    .content(standardJsonCustomerId())
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
	public void verifyEmailHash() throws Exception
	{
		when(customerQuickRegisterRepository.countByCustomerIdAndEmailHash(standardVerifyEmailHashDTO().getCustomerId(),
																					standardVerifyEmailHashDTO().getEmailHash()))
																						.thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyEmailHash")
	                    .content(standardJsonVerifyEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
	}


	@Test
	public void verifyMobilePin() throws Exception
	{
		when(customerQuickRegisterRepository.countByCustomerIdAndMobilePin(standardVerifyMobilePinDTO().getCustomerId(),
																					standardVerifyMobilePinDTO().getMobilePin()))
																						.thenReturn(1);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyMobilePin")
	                    .content(standardJsonVerifyMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
	}

	@Test
	public void getStatusByCustomerId() throws Exception
	{
		when(customerQuickRegisterRepository.fetchStatusByCustomerId(CUST_ID)).thenReturn(CUST_STATUS_EMAILMOBILE);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getStatusByCustomerId")
	                    .content(standardJsonCustomerId())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string(CUST_STATUS_EMAILMOBILE));
		
		
		
	}
	
	@Test
	public void updateStatusByCustomerId() throws Exception
	{
		when(customerQuickRegisterRepository.updateStatusByCustomerId(CUST_ID, STATUS_EMAIL_MOBILE_VERIFIED)).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateStatusByCustomerId")
	                    .content(standardJsonUpdateStatusByCustomerIdDTO())
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
					standardUpdateEmailHashDTO().getEmailHash())).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateEmailHash")
	                    .content(standardJsonUpdateEmailHashDTO())
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
				standardUpdateMobilePinDTO().getMobilePin())).thenReturn(1);
		

		this.mockMvc.perform(
	            post("/customer/quickregister/updateMobilePin")
	                    .content(standardJsonUpdateMobilePinDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}

}

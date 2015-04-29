package com.projectx.data.controller.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.standardJsonCustomerEmailVerificationDetails;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.standardJsonEmailKey;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

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
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class MobileVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/clearForTesting"));
		
		mobileVerificationDetailsRepository.deleteAll();
	}
	
	
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	
	@Test
	public void getMobikeVerificationDetailsByCustomerIdAndMobile() throws Exception
	{
				
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile")
	                    .content(standardJsonCustomerIdTypeMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isFound())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardCustomerMobileVerificationDetails().getKey().getCustomerType()))
		          .andExpect(jsonPath("$.key.mobileType").value(standardCustomerMobileVerificationDetails().getKey().getMobileType()))
		          .andExpect(jsonPath("$.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
		          .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
		          .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
		          .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.insertTime").exists())
		          .andExpect(jsonPath("$.updateTime").exists())
		          .andExpect(jsonPath("$.updatedBy").value(standardCustomerMobileVerificationDetails().getUpdatedBy()));
		             
	}

	
	@Test
	public void getMobikeVerificationDetailsByMobile() throws Exception
	{
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
				
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/getMobileVerificationDetailsByMobile")
	                    .content(standardJsonMobile())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isFound())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardCustomerMobileVerificationDetails().getKey().getCustomerType()))
		          .andExpect(jsonPath("$.key.mobileType").value(standardCustomerMobileVerificationDetails().getKey().getMobileType()))
		          .andExpect(jsonPath("$.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
		          .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
		          .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
		          .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.insertTime").exists())
		          .andExpect(jsonPath("$.updateTime").exists())
		          .andExpect(jsonPath("$.updatedBy").value(standardCustomerMobileVerificationDetails().getUpdatedBy()));
		            
	}

	
	@Test
	public void updateMobilePinAndMobileVerificationAttemptsAndResendCount() throws Exception
	{
		
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/saveMobileVerificationDetails")
	                    .content(standardJsonCustomerMobileVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"));
		
		
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
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"));
		
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/incrementMobileVerificationAttempts")
	                    .content(standardJsonCustomerIdTypeMobileUpdatedBy())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	@Test
	public void incrementResendCount() throws Exception
	{
		
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/incrementResendCount")
	                    .content(standardJsonCustomerIdTypeMobileUpdatedBy())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	
	@Test
	public void mobileVerificationCount() throws Exception
	{

		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
	
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}
	
	@Test
	public void deleteByKey() throws Exception
	{
	
		mobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
	
		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("1"));

		
		this.mockMvc.perform(
	            post("/customer/quickregister/mobileVerification/deleteByKey")
	                    .content(standardJsonMobileKey())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("true"));

		this.mockMvc.perform(get("/customer/quickregister/mobileVerification/getCount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
		
	}


}

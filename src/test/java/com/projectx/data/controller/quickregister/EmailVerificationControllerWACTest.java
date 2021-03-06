package com.projectx.data.controller.quickregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;
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
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class EmailVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
	
	}
	
	@Before
	public void clearTestData()
	{
		emailVerificationDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
	}

	
	
	@Test
	public void getEmailVerificationDetailsByCustomerIdAndEmail() throws Exception
	{
		
		emailVerificationDetailsRepository.deleteAll();
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
				
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdTypeEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isFound())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardEmailVerificationKey().getCustomerType()))
		          .andExpect(jsonPath("$.key.emailType").value(standardEmailVerificationKey().getEmailType()))
		          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
		          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
		          .andExpect(jsonPath("$.emailHashSentTime").exists())
		          .andExpect(jsonPath("$.insertTime").exists())
		          .andExpect(jsonPath("$.updateTime").exists())
		          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.updatedBy").value(standardCustomerEmailVerificationDetails().getUpdatedBy()));
		            

	}

	
	@Test
	public void getEmailVerificationDetailsByEmail() throws Exception
	{
		
		emailVerificationDetailsRepository.deleteAll();
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
				
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/getEmailVerificationDetailsByEmail")
	                    .content(standardJsonEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isFound())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardEmailVerificationKey().getCustomerType()))
		          .andExpect(jsonPath("$.key.emailType").value(standardEmailVerificationKey().getEmailType()))
		          .andExpect(jsonPath("$.email").value(standardCustomerEmailVerificationDetails().getEmail()))
		          .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
		          .andExpect(jsonPath("$.emailHashSentTime").exists())
		          .andExpect(jsonPath("$.insertTime").exists())
		          .andExpect(jsonPath("$.updateTime").exists())
		          .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()))
		          .andExpect(jsonPath("$.updatedBy").value(standardCustomerEmailVerificationDetails().getUpdatedBy()));
		            
	}

	
	@Test
	//@Rollback(value=false)
	public void resetEmailHashAndEmailHashSentTime() throws Exception
	{
		
		emailVerificationDetailsRepository.deleteAll();
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"));
		
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
		emailVerificationDetailsRepository.deleteAll();	
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
	
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/incrementResendCountByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdTypeEmailUpdatedBy())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("1"));

		
	}
	
	
	@Test
	public void getEmailVerificationCount() throws Exception
	{
		emailVerificationDetailsRepository.deleteAll();
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
	
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
	}

	
	@Test
	public void deleteByKey() throws Exception
	{
		emailVerificationDetailsRepository.deleteAll();
		
		emailVerificationDetailsRepository.save(standardCustomerEmailVerificationDetails());
	
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("1"));

		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/deleteByKey")
	                    .content(standardJsonEmailKey())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(content().string("true"));

		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
		
	}

}

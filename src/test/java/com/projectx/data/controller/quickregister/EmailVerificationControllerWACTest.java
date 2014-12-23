package com.projectx.data.controller.quickregister;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Prod")
public class EmailVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
	}
	
	
	@Test
	public void environmentTest() {
		
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
	          .andExpect(jsonPath("$.key.email").value(standardEmailVerificationKey().getEmail()))
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
	                    .content(standardJsonCustomerIdTypeEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
	            .andExpect(status().isOk())
	          //.andExpect(jsonPath("$.customerId").value(standardCustomerEmailVerificationDetails().getCustomerId()))
	          .andExpect(jsonPath("$.key.email").value(standardCustomerEmailVerificationDetails().getKey().getEmail()))
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
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/saveEmailVerificationDetails")
	                    .content(standardJsonCustomerEmailVerificationDetails())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(get("/customer/quickregister/emailVerification/getCount"));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/emailVerification/incrementResendCountByCustomerIdAndEmail")
	                    .content(standardJsonCustomerIdTypeEmail())
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
	public void deleteByKey() throws Exception
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

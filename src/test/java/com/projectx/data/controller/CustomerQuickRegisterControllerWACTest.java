package com.projectx.data.controller;

import static org.junit.Assert.*;

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

import com.projectx.data.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Test")
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
	public void saveEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
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
					.andExpect(jsonPath("$[0].['firstName']").value(CUST_FIRSTNAME))
					.andExpect(jsonPath("$[0].['lastName']").value(CUST_LASTNAME))
					.andExpect(jsonPath("$[0].['email']").value(CUST_EMAIL))
					.andExpect(jsonPath("$[0].['mobile']").value(CUST_MOBILE))
					.andExpect(jsonPath("$[0].['status']").value(CUST_STATUS_EMAILMOBILE))
					.andExpect(jsonPath("$[0].['pin']").value(CUST_PIN))
					.andExpect(jsonPath("$[0].['mobilePin']").value(CUST_MOBILEPIN))
					//.andExpect(jsonPath("$[0].['emailHash']").value(CUST_EMAILHASH))
		
					.andExpect(jsonPath("$[1].['firstName']").value(CUST_FIRSTNAME))
					.andExpect(jsonPath("$[1].['lastName']").value(CUST_LASTNAME))
					.andExpect(jsonPath("$[1].['email']").value(CUST_EMAIL_OTHER))
					.andExpect(jsonPath("$[1].['mobile']").doesNotExist())
					.andExpect(jsonPath("$[1].['status']").doesNotExist())		
					.andExpect(jsonPath("$[1].['pin']").value(CUST_PIN))
					.andExpect(jsonPath("$[1].['mobilePin']").doesNotExist())
					.andExpect(jsonPath("$[1].['emailHash']").doesNotExist());

		
					
		
		
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

}

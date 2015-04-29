package com.projectx.data.controller.aync;

import static com.projectx.data.fixtures.async.RetriggerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardJsonCustomerDetails;
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
import com.projectx.data.domain.async.RetriggerDetails;
import com.projectx.data.repository.async.RetriggerDetailsRepository;


import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class RetriggerDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		retriggerDetailsRepository.deleteAll();
	
	}


	@Test
	public void environmentTest() {
		
	}


	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
	            post("/retriggerDetails")
	                    .content(standardJsonRetrigger(standardRetriggerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.errorMessage").value(""))
	            .andExpect(jsonPath("$.result.service").value(standardRetriggerDetails().getService()))
	            .andExpect(jsonPath("$.result.data").value(standardRetriggerDetails().getData()));

	
	}

	@Test
	public void findAll() throws Exception
	{
		retriggerDetailsRepository.save(standardRetriggerDetails());
		
		this.mockMvc.perform(
	            get("/retriggerDetails/findAll")
	               )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.errorMessage").value(""))
	            .andExpect(jsonPath("$.result.[0].service").value(standardRetriggerDetails().getService()))
	            .andExpect(jsonPath("$.result.[0].data").value(standardRetriggerDetails().getData()));

	
	}
	
	@Test
	public void deleteById() throws Exception
	{
		RetriggerDetails retriggerDetails=retriggerDetailsRepository.save(standardRetriggerDetails());

		
		
		this.mockMvc.perform(
	            get("/retriggerDetails/deleteById/"+retriggerDetails.getRetriggerId())
	               )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.errorMessage").value(""))
	            .andExpect(jsonPath("$.result").value(true));
		
		assertEquals(0, retriggerDetailsRepository.count());
		
		
		

	
	}	
	
	
}

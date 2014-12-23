package com.projectx.data.controller.completeregister;

import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardJsonCustomerDetails;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.projectx.data.domain.completeregister.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Prod")
public class AddressControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
	}

	@Test
	public void save() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/address")
	                    .content(standardJsonAddress())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.customerType").value(standardAddress().getCustomerType()))
	            .andExpect(jsonPath("$.addressLine").value(standardAddress().getAddressLine()))
	            .andExpect(jsonPath("$.city").value(standardAddress().getCity()))
	            .andExpect(jsonPath("$.district").value(standardAddress().getDistrict()))
	            .andExpect(jsonPath("$.state").value(standardAddress().getState()))
	            .andExpect(jsonPath("$.pincode").value(standardAddress().getPincode()));
	

	}


}

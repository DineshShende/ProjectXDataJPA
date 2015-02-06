package com.projectx.data.controller.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomerWithErrors;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.standardJsonQuickRegisterCustomer;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.projectx.data.repository.quickregister.QuickRegisterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Prod")

public class TestControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	
	
	@Before
	
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	
	@Test
	public void test() throws Exception {

		this.mockMvc.perform(
	            get("/testing/save/test")
	                    )
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
   	    
		
	}

}

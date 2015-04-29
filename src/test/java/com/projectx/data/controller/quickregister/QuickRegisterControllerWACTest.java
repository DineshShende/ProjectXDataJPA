package com.projectx.data.controller.quickregister;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.After;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.EmailVerificationDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import com.projectx.data.config.Application;
import com.projectx.data.repository.quickregister.QuickRegisterRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class QuickRegisterControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Before
	
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	
	@Before
	@After
	public void clearTestData()
	{
		quickRegisterRepository.deleteAll();
		
	}
	
	@Test
	public void getCustomerByCustomerId() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
			
		this.mockMvc.perform(
	            post("/customer/quickregister/getEntityByCustomerId")
	                    .content(standardJsonCustomerId(CUST_ID))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(jsonPath("$.errorMessage").value("QUICKREG_ENTITY_NOT_FOUND_BY_MOBILE"))
	            .andExpect(jsonPath("$.result").doesNotExist()) 
	            .andExpect(status().isOk());
		
	}
	
	@Test
	public void getCount() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		quickRegisterRepository.save(standardEmailMobileCustomer());
		quickRegisterRepository.save(standardEmailCustomerOther());
		
		this.mockMvc.perform(
				get("/customer/quickregister/count"))
				//	.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("2"));
						
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobileWithEmailMobileCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		quickRegisterRepository.save(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            //.andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.result.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.result.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
	
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.result.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.result.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailWithEmailCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		quickRegisterRepository.save(standardEmailCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.mobile").value(standardEmailCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.pincode").value(standardEmailCustomer().getPincode()))
				.andExpect(jsonPath("$.result.isEmailVerified").value(standardEmailCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.result.isMobileVerified").value(standardEmailCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.updatedBy").value(standardEmailCustomer().getUpdatedBy()));			
	
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByMobileWithMobileCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		quickRegisterRepository.save(standardMobileCustomer());
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.pincode").value(standardMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.result.isEmailVerified").value(standardMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.result.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.updatedBy").value(standardMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
		
	@Test
	public void clearTestingData() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		quickRegisterRepository.save(standardEmailMobileCustomer());
			
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.firstName").value(standardMobileCustomer().getFirstName()));
		
		this.mockMvc.perform(
				get("/customer/quickregister/clearForTesting"))
					.andDo(print());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				//.andDo(print())
	            .andExpect(jsonPath("$.errorMessage").value("QUICKREG_ENTITY_NOT_FOUND_BY_MOBILE"))
	            .andExpect(jsonPath("$.result").doesNotExist())
				.andExpect(status().isOk());

	
	}
	
	
}

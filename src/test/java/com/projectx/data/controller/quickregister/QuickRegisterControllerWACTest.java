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
	public void saveNewEmailMobileCustomerWithErrorsNew() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomerWithErrors()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            //.andDo(print())
	            .andExpect(status().isNotAcceptable());
		
	}
	
	
	@Test
	public void saveNewEmailMobileCustomerWithErrorsNullFirstName() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileVendorWithErrorNullFirstName()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            //.andDo(print())
	            .andExpect(status().isNotAcceptable());
		
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
	           // .andDo(print())
	            .andExpect(status().isNoContent());
		/*
	            .andExpect(jsonPath("$.firstName").doesNotExist())
	            .andExpect(jsonPath("$.lastName").doesNotExist())
	            .andExpect(jsonPath("$.mobile").doesNotExist())
	            .andExpect(jsonPath("$.email").doesNotExist())
	            .andExpect(jsonPath("$.pincode").doesNotExist())
				.andExpect(jsonPath("$.isEmailVerified").doesNotExist())
				.andExpect(jsonPath("$.isMobileVerified").doesNotExist())
				.andExpect(jsonPath("$.insertTime").doesNotExist())
				.andExpect(jsonPath("$.updateTime").doesNotExist())
				.andExpect(jsonPath("$.updatedBy").doesNotExist());
		*/
	}
	
	
	@Test
	public void saveNewEmailMobileCustomerWithErrors() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomerWithErrors()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isNotAcceptable());
	}

	
	@Test
	public void saveNewEmailMobileCustomer() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	         //   .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
				 
	}

	
	@Test
	public void saveNewEmailCustomer() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	         //   .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailCustomer().getUpdatedBy()));			
	
	}

	@Test
	public void saveNewMobileCustomer() throws Exception {
		
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	        //    .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardMobileCustomer().getUpdatedBy()));
				
	}

	
	@Test
	public void getAllCustomerWithEmaiMobileAndEmailCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomerOther()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
	
		this.mockMvc.perform(
				get("/customer/quickregister/getAll"))
				//	.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].['firstName']").value(standardEmailMobileCustomer().getFirstName()))
					.andExpect(jsonPath("$[0].['lastName']").value(standardEmailMobileCustomer().getLastName()))
					.andExpect(jsonPath("$[0].['mobile']").value(standardEmailMobileCustomer().getMobile()))
					.andExpect(jsonPath("$[0].['email']").value(standardEmailMobileCustomer().getEmail()))
					.andExpect(jsonPath("$[0].['pincode']").value(standardEmailMobileCustomer().getPincode()))
					.andExpect(jsonPath("$[0].['isEmailVerified']").value(standardEmailMobileCustomer().getIsEmailVerified()))
					.andExpect(jsonPath("$[0].['isMobileVerified']").value(standardEmailMobileCustomer().getIsMobileVerified()))
					.andExpect(jsonPath("$[0].['insertTime']").exists())
					.andExpect(jsonPath("$[0].['updateTime']").exists())
					.andExpect(jsonPath("$[0].['updatedBy']").value(standardEmailMobileCustomer().getUpdatedBy()))
				
					.andExpect(jsonPath("$[1].['firstName']").value(standardEmailCustomer().getFirstName()))
					.andExpect(jsonPath("$[1].['lastName']").value(standardEmailCustomer().getLastName()))
					.andExpect(jsonPath("$[1].['mobile']").doesNotExist())
					.andExpect(jsonPath("$[1].['email']").value(CUST_EMAIL_OTHER))
					.andExpect(jsonPath("$[1].['pincode']").value(standardEmailCustomer().getPincode()))
					.andExpect(jsonPath("$[1].['isEmailVerified']").value(standardEmailCustomer().getIsEmailVerified()))
					.andExpect(jsonPath("$[1].['isMobileVerified']").value(standardEmailCustomer().getIsMobileVerified()))
					.andExpect(jsonPath("$[1].['insertTime']").exists())
					.andExpect(jsonPath("$[1].['updateTime']").exists())
					.andExpect(jsonPath("$[1].['updatedBy']").value(standardEmailCustomer().getUpdatedBy()));
				
		
					
		
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobileWithEmailMobileCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            //.andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
	
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByEmailWithEmailCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByEmail")
	                    .content(standardJsonEmailDTO(CUST_EMAIL))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.firstName").value(standardEmailCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardEmailCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardEmailCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardEmailCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailCustomer().getUpdatedBy()));			
	
		
	}
	
	@Test
	public void getCustomerQuickRegisterEntityByMobileWithMobileCustomer() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           // .andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.pincode").value(standardMobileCustomer().getPincode()))
				.andExpect(jsonPath("$.isEmailVerified").value(standardMobileCustomer().getIsEmailVerified()))
				.andExpect(jsonPath("$.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
				.andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardMobileCustomer().getUpdatedBy()));			
	
		
		
	}
	
		
	@Test
	public void clearTestingData() throws Exception
	{
		quickRegisterRepository.deleteAll();
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonQuickRegisterCustomer(standardEmailMobileCustomer()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isFound())
				.andExpect(jsonPath("$.firstName").value(standardMobileCustomer().getFirstName()));
		
		this.mockMvc.perform(
				get("/customer/quickregister/clearForTesting"))
					.andDo(print());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/getCustomerQuickRegisterEntityByMobile")
	                    .content(standardJsonMobileDTO(CUST_MOBILE))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
				//.andDo(print())
				.andExpect(status().isNoContent());
				//.andExpect(jsonPath("$.firstName").doesNotExist());
	
	}
	
	
}

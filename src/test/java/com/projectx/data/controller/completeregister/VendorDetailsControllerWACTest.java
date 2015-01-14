package com.projectx.data.controller.completeregister;

import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.data.fixtures.completeregister.DocumentDetailsDataFixture.*;
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

public class VendorDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
		this.mockMvc.perform(
	            get("/vendor/clearTestData")
									);

	}

	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardVendor().getFirstName()))
		 		.andExpect(jsonPath("$.lastName").value(standardVendor().getLastName()))
		 		.andExpect(jsonPath("$.firmAddress.addressLine").value(standardVendor().getFirmAddress().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddress.customerType").value(standardVendor().getFirmAddress().getCustomerType()))
	            .andExpect(jsonPath("$.firmAddress.city").value(standardVendor().getFirmAddress().getCity()))
	            .andExpect(jsonPath("$.firmAddress.district").value(standardVendor().getFirmAddress().getDistrict()))
	            .andExpect(jsonPath("$.firmAddress.state").value(standardVendor().getFirmAddress().getState()))
	            .andExpect(jsonPath("$.firmAddress.pincode").value(standardVendor().getFirmAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardVendor().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardVendor().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.laguage").value(standardVendor().getLaguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		

	}
	
	@Test
	public void update() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		this.mockMvc.perform(
	            post("/vendor/update")
	                    .content(standardJsonVendor(standardVendorUpdatedFirstLastName()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardVendorUpdatedFirstLastName().getFirstName()))
		 		.andExpect(jsonPath("$.lastName").value(standardVendorUpdatedFirstLastName().getLastName()))
		 		.andExpect(jsonPath("$.firmAddress.addressLine").value(standardVendor().getFirmAddress().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddress.customerType").value(standardVendor().getFirmAddress().getCustomerType()))
	            .andExpect(jsonPath("$.firmAddress.city").value(standardVendor().getFirmAddress().getCity()))
	            .andExpect(jsonPath("$.firmAddress.district").value(standardVendor().getFirmAddress().getDistrict()))
	            .andExpect(jsonPath("$.firmAddress.state").value(standardVendor().getFirmAddress().getState()))
	            .andExpect(jsonPath("$.firmAddress.pincode").value(standardVendor().getFirmAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardVendor().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardVendor().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.laguage").value(standardVendor().getLaguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		

	}
	
	@Test
	public void getById() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		this.mockMvc.perform(
	            get("/vendor/getById/"+standardVendor().getVendorId())
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardVendor().getFirstName()))
		 		.andExpect(jsonPath("$.lastName").value(standardVendor().getLastName()))
		 		.andExpect(jsonPath("$.firmAddress.addressLine").value(standardVendor().getFirmAddress().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddress.customerType").value(standardVendor().getFirmAddress().getCustomerType()))
	            .andExpect(jsonPath("$.firmAddress.city").value(standardVendor().getFirmAddress().getCity()))
	            .andExpect(jsonPath("$.firmAddress.district").value(standardVendor().getFirmAddress().getDistrict()))
	            .andExpect(jsonPath("$.firmAddress.state").value(standardVendor().getFirmAddress().getState()))
	            .andExpect(jsonPath("$.firmAddress.pincode").value(standardVendor().getFirmAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardVendor().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardVendor().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.laguage").value(standardVendor().getLaguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		

	}
	
	
	@Test
	public void updateEmailVerificationStatus() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		

		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		 		
	    
		this.mockMvc.perform(
	            post("/vendor/updateEmailVerificationStatus")
	                    .content(standardJsonUpdateEmailVerificationStatus(standardEmailUpdateVerificationStatusDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
	    
		
	}
	
	
	public void updateMobileVerificationStatus() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		

		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		 		
	    
		this.mockMvc.perform(
	            post("/vendor/updateMobileVerificationStatus")
	                    .content(standardJsonUpdateMobileVerificationStatus(standardUpdateMobileVerificationStatusDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
	    
		
	}
	
	
	@Test
	public void count() throws Exception
	{

		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
		 		
		
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		 		
	}
	
	@Test
	public void clearTestData() throws Exception
	{

		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
		 		
		
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		

		this.mockMvc.perform(
	            get("/vendor/clearTestData")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		this.mockMvc.perform(
	            get("/vendor/count")
									)
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
		 		
	}
	
}

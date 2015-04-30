package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
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
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.repository.completeregister.VendorDetailsRepositoty;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class VendorDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	
	@Autowired
	VendorDetailsRepositoty vendorDetailsRepositoty;
	
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
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.result.firstName").value(standardVendor().getFirstName()))
		 		.andExpect(jsonPath("$.result.lastName").value(standardVendor().getLastName()))
		 		.andExpect(jsonPath("$.result.firmAddress.addressLine").value(standardVendor().getFirmAddress().getAddressLine()))
	            .andExpect(jsonPath("$.result.firmAddress.customerType").value(standardVendor().getFirmAddress().getCustomerType()))
	            .andExpect(jsonPath("$.result.firmAddress.city").value(standardVendor().getFirmAddress().getCity()))
	            .andExpect(jsonPath("$.result.firmAddress.district").value(standardVendor().getFirmAddress().getDistrict()))
	            .andExpect(jsonPath("$.result.firmAddress.state").value(standardVendor().getFirmAddress().getState()))
	            .andExpect(jsonPath("$.result.firmAddress.pincode").value(standardVendor().getFirmAddress().getPincode()))
	            .andExpect(jsonPath("$.result.mobile").value(standardVendor().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.email").value(standardVendor().getEmail()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.laguage").value(standardVendor().getLaguage()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists())
	            .andExpect(jsonPath("$.errorMessage").exists());		

	}
	
	@Test
	public void saveWithError() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendorError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void updateVendorDetails() throws Exception
	{
		VendorDetails vendorDetails=vendorDetailsRepositoty.save(standardVendor());
		
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendorUpdatedFirstLastName()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	          //  .andExpect(jsonPath("$.customerId").value(standardCustomerDetailsFirstPart().getCustomerId()))
	            .andExpect(jsonPath("$.result.firstName").value(standardVendorUpdatedFirstLastName().getFirstName()))
		 		.andExpect(jsonPath("$.result.lastName").value(standardVendorUpdatedFirstLastName().getLastName()))
		 		.andExpect(jsonPath("$.result.firmAddress.addressLine").value(standardVendor().getFirmAddress().getAddressLine()))
	            .andExpect(jsonPath("$.result.firmAddress.customerType").value(standardVendor().getFirmAddress().getCustomerType()))
	            .andExpect(jsonPath("$.result.firmAddress.city").value(standardVendor().getFirmAddress().getCity()))
	            .andExpect(jsonPath("$.result.firmAddress.district").value(standardVendor().getFirmAddress().getDistrict()))
	            .andExpect(jsonPath("$.result.firmAddress.state").value(standardVendor().getFirmAddress().getState()))
	            .andExpect(jsonPath("$.result.firmAddress.pincode").value(standardVendor().getFirmAddress().getPincode()))
	            .andExpect(jsonPath("$.result.mobile").value(standardVendor().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.email").value(standardVendor().getEmail()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.laguage").value(standardVendor().getLaguage()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists())
	            .andExpect(jsonPath("$.errorMessage").value(""));		
		
	
	}
	
	@Test
	public void updateVendorDetailsError() throws Exception
	{
		VendorDetails vendorDetails=vendorDetailsRepositoty.save(standardVendor());
		
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendorError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	}
	
	
	@Test
	public void getById() throws Exception
	{
		this.mockMvc.perform(
	            get("/vendor/getById/"+standardVendor().getVendorId())
									)
	            .andDo(print())
	            .andExpect(status().isNoContent());
		
		this.mockMvc.perform(
	            post("/vendor/save")
	                    .content(standardJsonVendor(standardVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		this.mockMvc.perform(
	            get("/vendor/getById/"+standardVendor().getVendorId())
									)
	            .andDo(print())
	            .andExpect(status().isFound())
	            
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
	
	/*
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
	
	@Test
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
	*/
	
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

package com.projectx.data.controller.completeregister;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
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
public class CustomerDetailsControllerWACTest {

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
	public void saveCustomerDetailsCopiedFromQuickRegisterEntity() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	          //  .andExpect(jsonPath("$.customerId").value(standardCustomerDetailsFirstPart().getCustomerId()))
	            .andExpect(jsonPath("$.firstName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLastName()))
	            .andExpect(jsonPath("$.homeAddressId").doesNotExist())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsEmailVerified()))
	            .andExpect(jsonPath("$.language").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	            
	            
		
	}
	

	@Test
	public void saveCustomerDetails() throws Exception
	{
		

		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
			
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	          //  .andExpect(jsonPath("$.customerId").value(standardCustomerDetailsFirstPart().getCustomerId()))
	            .andExpect(jsonPath("$.firstName").value(standardCustomerDetails().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardCustomerDetails().getLastName()))
	            .andExpect(jsonPath("$.homeAddressId.customerType").value(standardCustomerDetails().getHomeAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardCustomerDetails().getHomeAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddressId.city").value(standardCustomerDetails().getHomeAddressId().getCity()))
	            .andExpect(jsonPath("$.homeAddressId.district").value(standardCustomerDetails().getHomeAddressId().getDistrict()))
	            .andExpect(jsonPath("$.homeAddressId.state").value(standardCustomerDetails().getHomeAddressId().getState()))
	            .andExpect(jsonPath("$.homeAddressId.pincode").value(standardCustomerDetails().getHomeAddressId().getPincode()))
	            .andExpect(jsonPath("$.homeAddressId.updatedBy").value(standardCustomerDetails().getHomeAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.homeAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.homeAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardCustomerDetails().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails().getIsEmailVerified()))
	            .andExpect(jsonPath("$.language").value(standardCustomerDetails().getLanguage()))
	            .andExpect(jsonPath("$.firmAddressId.customerType").value(standardCustomerDetails().getFirmAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardCustomerDetails().getFirmAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddressId.city").value(standardCustomerDetails().getFirmAddressId().getCity()))
	            .andExpect(jsonPath("$.firmAddressId.district").value(standardCustomerDetails().getFirmAddressId().getDistrict()))
	            .andExpect(jsonPath("$.firmAddressId.state").value(standardCustomerDetails().getFirmAddressId().getState()))
	            .andExpect(jsonPath("$.firmAddressId.pincode").value(standardCustomerDetails().getFirmAddressId().getPincode()))
	            .andExpect(jsonPath("$.firmAddressId.updatedBy").value(standardCustomerDetails().getFirmAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.firmAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.firmAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails().getBusinessDomain()))
	            .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails().getNameOfFirm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardCustomerDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails().getSecondaryMobile()))
	            .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails().getIsSecondaryMobileVerified()))
	            .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails().getSecondaryEmail()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	            
	            
		
	}
	
	@Test
	public void findOne() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	 
		
		this.mockMvc.perform(
	            get("/customer/completeregister/212"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardCustomerDetails().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardCustomerDetails().getLastName()))
	            .andExpect(jsonPath("$.homeAddressId.customerType").value(standardCustomerDetails().getHomeAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardCustomerDetails().getHomeAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddressId.city").value(standardCustomerDetails().getHomeAddressId().getCity()))
	            .andExpect(jsonPath("$.homeAddressId.district").value(standardCustomerDetails().getHomeAddressId().getDistrict()))
	            .andExpect(jsonPath("$.homeAddressId.state").value(standardCustomerDetails().getHomeAddressId().getState()))
	            .andExpect(jsonPath("$.homeAddressId.pincode").value(standardCustomerDetails().getHomeAddressId().getPincode()))
	            .andExpect(jsonPath("$.homeAddressId.updatedBy").value(standardCustomerDetails().getHomeAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.homeAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.homeAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.email").value(standardCustomerDetails().getEmail()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails().getIsEmailVerified()))
	            .andExpect(jsonPath("$.language").value(standardCustomerDetails().getLanguage()))
	            .andExpect(jsonPath("$.firmAddressId.customerType").value(standardCustomerDetails().getFirmAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardCustomerDetails().getFirmAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddressId.city").value(standardCustomerDetails().getFirmAddressId().getCity()))
	            .andExpect(jsonPath("$.firmAddressId.district").value(standardCustomerDetails().getFirmAddressId().getDistrict()))
	            .andExpect(jsonPath("$.firmAddressId.state").value(standardCustomerDetails().getFirmAddressId().getState()))
	            .andExpect(jsonPath("$.firmAddressId.pincode").value(standardCustomerDetails().getFirmAddressId().getPincode()))
	            .andExpect(jsonPath("$.firmAddressId.updatedBy").value(standardCustomerDetails().getFirmAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.firmAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.firmAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails().getBusinessDomain()))
	            .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails().getNameOfFirm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardCustomerDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails().getSecondaryMobile()))
	            .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails().getIsSecondaryMobileVerified()))
	            .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails().getSecondaryEmail()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	             
	    
		
	}
	

	@Test
	public void updateFirmAddress() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	 
	
		this.mockMvc.perform(
	            post("/customer/completeregister/updateFirmAddress")
	                    .content(standardJsonUpdateAddress())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())	            
	            .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardUpdateAddressDTO().getAddress().getAddressLine()))
	            .andExpect(jsonPath("$.firmAddressId.city").value(standardUpdateAddressDTO().getAddress().getCity()))
	            .andExpect(jsonPath("$.firmAddressId.district").value(standardUpdateAddressDTO().getAddress().getDistrict()))
	            .andExpect(jsonPath("$.firmAddressId.state").value(standardUpdateAddressDTO().getAddress().getState()));
		
	}
	
	@Test
	public void updateHomeAddress() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	 
	
		this.mockMvc.perform(
	            post("/customer/completeregister/updateHomeAddress")
	                    .content(standardJsonUpdateAddress())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())	            
	            .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardUpdateAddressDTO().getAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddressId.city").value(standardUpdateAddressDTO().getAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddressId.district").value(standardUpdateAddressDTO().getAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddressId.state").value(standardUpdateAddressDTO().getAddress().getState()));
		
	}
	
	@Test
	public void updateMobileVerificationStatus() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	 
	
		this.mockMvc.perform(
	            post("/customer/completeregister/updateMobileVerificationStatus")
	                    .content(standardJsonUpdateMobileVerificationStatus())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}
	
	@Test
	public void updateSecondaryMobileVerificationStatus() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	 
	
		this.mockMvc.perform(
	            post("/customer/completeregister/updateSecondaryMobileVerificationStatus")
	                    .content(standardJsonUpdateMobileVerificationStatus())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
	}

}

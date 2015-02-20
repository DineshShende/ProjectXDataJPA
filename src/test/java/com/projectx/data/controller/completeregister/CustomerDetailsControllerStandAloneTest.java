package com.projectx.data.controller.completeregister;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;


public class CustomerDetailsControllerStandAloneTest {
	
	@InjectMocks
	CustomerDetailsController customerDetailsController;
	
	@Mock
	CustomerDetailsCustomRepository customerDetailsCustomRepository;
	
	private MockMvc mockMvc;
	

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerDetailsController)
	    		.build();
	    
	}
	
	
	@Test
	public void environmetTest() {
		
	}
	
	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntity() throws Exception
	{
		
		when(customerDetailsCustomRepository.save(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
				.thenReturn(standardCustomerDetailsCopiedFromQuickRegisterEntity());
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
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
	public void saveCustomerDetailsSecondPart() throws Exception
	{
		
		when(customerDetailsCustomRepository.save(standardCustomerDetails())).thenReturn(standardCustomerDetails());
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
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


}

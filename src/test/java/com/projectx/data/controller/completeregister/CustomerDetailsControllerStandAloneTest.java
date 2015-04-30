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
	            .andExpect(jsonPath("$.result.firstName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLastName()))
	            .andExpect(jsonPath("$.result.homeAddressId").doesNotExist())
	            .andExpect(jsonPath("$.result.mobile").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.email").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getEmail()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.language").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLanguage()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists())
	            .andExpect(jsonPath("$.errorMessage").value(""));		
	            
	            
		
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
	            .andExpect(jsonPath("$.result.firstName").value(standardCustomerDetails().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardCustomerDetails().getLastName()))
	            .andExpect(jsonPath("$.result.homeAddressId.customerType").value(standardCustomerDetails().getHomeAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.result.homeAddressId.addressLine").value(standardCustomerDetails().getHomeAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.result.homeAddressId.city").value(standardCustomerDetails().getHomeAddressId().getCity()))
	            .andExpect(jsonPath("$.result.homeAddressId.district").value(standardCustomerDetails().getHomeAddressId().getDistrict()))
	            .andExpect(jsonPath("$.result.homeAddressId.state").value(standardCustomerDetails().getHomeAddressId().getState()))
	            .andExpect(jsonPath("$.result.homeAddressId.pincode").value(standardCustomerDetails().getHomeAddressId().getPincode()))
	            .andExpect(jsonPath("$.result.homeAddressId.updatedBy").value(standardCustomerDetails().getHomeAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.homeAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.result.homeAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.result.mobile").value(standardCustomerDetails().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardCustomerDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.email").value(standardCustomerDetails().getEmail()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardCustomerDetails().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.language").value(standardCustomerDetails().getLanguage()))
	            .andExpect(jsonPath("$.result.firmAddressId.customerType").value(standardCustomerDetails().getFirmAddressId().getCustomerType()))
	            .andExpect(jsonPath("$.result.firmAddressId.addressLine").value(standardCustomerDetails().getFirmAddressId().getAddressLine()))
	            .andExpect(jsonPath("$.result.firmAddressId.city").value(standardCustomerDetails().getFirmAddressId().getCity()))
	            .andExpect(jsonPath("$.result.firmAddressId.district").value(standardCustomerDetails().getFirmAddressId().getDistrict()))
	            .andExpect(jsonPath("$.result.firmAddressId.state").value(standardCustomerDetails().getFirmAddressId().getState()))
	            .andExpect(jsonPath("$.result.firmAddressId.pincode").value(standardCustomerDetails().getFirmAddressId().getPincode()))
	            .andExpect(jsonPath("$.result.firmAddressId.updatedBy").value(standardCustomerDetails().getFirmAddressId().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.firmAddressId.insertTime").exists())
	            .andExpect(jsonPath("$.result.firmAddressId.updateTime").exists())
	            .andExpect(jsonPath("$.result.businessDomain").value(standardCustomerDetails().getBusinessDomain()))
	            .andExpect(jsonPath("$.result.nameOfFirm").value(standardCustomerDetails().getNameOfFirm()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardCustomerDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.secondaryMobile").value(standardCustomerDetails().getSecondaryMobile()))
	            .andExpect(jsonPath("$.result.isSecondaryMobileVerified").value(standardCustomerDetails().getIsSecondaryMobileVerified()))
	            .andExpect(jsonPath("$.result.secondaryEmail").value(standardCustomerDetails().getSecondaryEmail()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists())
	            .andExpect(jsonPath("$.errorMessage").value(""));		
	            
	            
		
	}


}

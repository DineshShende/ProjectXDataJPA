package com.projectx.data.controller.completeregister;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardJsonCustomerDetails;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.web.servlet.ResultMatcher;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.repository.completeregister.AddressRepository;
import com.projectx.data.repository.completeregister.CustomerDetailsCustomRepository;

import static org.mockito.Mockito.when;

public class AddressControllerStandAloneTest {

	@InjectMocks
	AddressController addressController;
	
	@Mock
	AddressRepository addressRepository;
	
	private MockMvc mockMvc;
	

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(addressController)
	    		.build();
	    
	}
	
	
	@Test
	public void environmetTest() {
		
	}

	@Test
	public void deleteById() throws Exception
	{
		//when(addressRepository.delete(212L)).the
		
		
		//Address address=addressRepository.save(standardAddress());

		//System.out.println(address);
		
		this.mockMvc.perform(
	            delete("/address/212"))
	                    
	            .andDo(print())
	            .andExpect(status().isOk());
	            //.andExpect(content().string("true"));
	    
		
		
	}
	
	@Test
	public void getById() throws Exception
	{
		
		when(addressRepository.findOne(212L)).thenReturn(standardAddress());
		

		this.mockMvc.perform(
	            get("/address/212"))
	                    
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

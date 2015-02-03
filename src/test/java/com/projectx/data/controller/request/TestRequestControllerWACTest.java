package com.projectx.data.controller.request;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.projectx.data.domain.request.TestRequest;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;
import com.projectx.data.repository.request.FreightRequestByVendorRepository;
import com.projectx.data.repository.request.TestRequestRepository;

import static com.projectx.data.fixtures.request.TestRequestDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

@ActiveProfiles(value="Prod")
public class TestRequestControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	TestRequestRepository testRequestRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		testRequestRepository.deleteAll();
		vehicleDetailsRepository.deleteAll();
		
		
	}
	
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	
	public void save() throws Exception
	{
		
		
		
		this.mockMvc.perform(
	            post("/request/testrequest")
	                    .content(stanardJsonTestRequest(standardTestRequest()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.vehicleDetailsId").value(standardTestRequest().getVehicleDetailsId()))
	            .andExpect(jsonPath("$.source").value(standardTestRequest().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardTestRequest().getDestination()))
	            .andExpect(jsonPath("$.driverId").exists())
	            .andExpect(jsonPath("$.availableTime").value(standardTestRequest().getAvailableTime()))
	            .andExpect(jsonPath("$.pickupRangeInKm").value(standardTestRequest().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardTestRequest().getUpdatedBy()))
	            .andExpect(jsonPath("$.availableDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists())
	            ;

		
		 		
	
	}
	

	@Test
	public void getById() throws Exception
	{
		TestRequest customer=testRequestRepository.save(standardTestRequest());
		
		this.mockMvc.perform(
	            get("/request/testrequest/getById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.vehicleDetailsId.ownerFirstName").value(standardTestRequest().getVehicleDetailsId().getOwnerFirstName()))
	            .andExpect(jsonPath("$.source").value(standardTestRequest().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardTestRequest().getDestination()))
	            .andExpect(jsonPath("$.driverId").exists())
	            .andExpect(jsonPath("$.availableTime").value(standardTestRequest().getAvailableTime()))
	            .andExpect(jsonPath("$.pickupRangeInKm").value(standardTestRequest().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardTestRequest().getUpdatedBy()))
	            .andExpect(jsonPath("$.availableDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	           
	            
	    
		
	}

	/*
	@Test
	public void deleteById() throws Exception
	{
		FreightRequestByVendor customer=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightByVendor/deleteById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
	}
	
	@Test
	public void count() throws Exception
	{
		
		this.mockMvc.perform(
	            get("/request/freightByVendor/count"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
	    
	}
	
	@Test
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            get("/request/freightByVendor/clearTestData"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void findByVendor() throws Exception
	{
		FreightRequestByVendor customer=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightByVendor/findByVendorId/"+customer.getVendorId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.vehicleDetailsId").value(standardFreightRequestByVendor().getVehicleDetailsId()))
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByVendor().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByVendor().getDestination()))
	            .andExpect(jsonPath("$.[0].driverId").exists())
	            .andExpect(jsonPath("$.[0].availableTime").value(standardFreightRequestByVendor().getAvailableTime()))
	            .andExpect(jsonPath("$.[0].pickupRangeInKm").value(standardFreightRequestByVendor().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardFreightRequestByVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].availableDate").exists())
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists());
	           
	    
	}
	
*/

}

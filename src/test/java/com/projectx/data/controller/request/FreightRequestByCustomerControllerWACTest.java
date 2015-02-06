package com.projectx.data.controller.request;

import static com.projectx.data.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.data.fixtures.request.TestRequestDataFixtures.*;
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
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.TestRequest;
import com.projectx.data.repository.request.FreightRequestByCustomerRepository;
import com.projectx.data.repository.request.TestRequestRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles(value="Prod")
public class FreightRequestByCustomerControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@Autowired
	TestRequestRepository testRequestRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		freightRequestByCustomerRepository.deleteAll();
	
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
	            post("/request/freightByRequestCustomer")
	                    .content(standardJsonFreightRequestByCustomer(standardFreightRequestByCustomerFullTruckLoad()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.length").doesNotExist())
	            .andExpect(jsonPath("$.width").doesNotExist())
	            .andExpect(jsonPath("$.height").doesNotExist())
	            .andExpect(jsonPath("$.vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.pickupDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	    
	
	}
	
	
	
	@Test
	public void getById() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		this.mockMvc.perform(
	            get("/request/freightByRequestCustomer/getById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.length").doesNotExist())
	            .andExpect(jsonPath("$.width").doesNotExist())
	            .andExpect(jsonPath("$.height").doesNotExist())
	            .andExpect(jsonPath("$.vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.pickupDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	    
	            
	    
		
	}
	
	@Test
	public void deleteById() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		this.mockMvc.perform(
	            get("/request/freightByRequestCustomer/deleteById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
	}
	
	@Test
	public void count() throws Exception
	{
		
		this.mockMvc.perform(
	            get("/request/freightByRequestCustomer/count"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
	    
	}
	
	@Test
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            get("/request/freightByRequestCustomer/clearTestData"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void findByCustomerId() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		
		
		this.mockMvc.perform(
	            get("/request/freightByRequestCustomer/findByCustomer/"+customer.getCustomerId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.[0].noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.[0].isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.[0].isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.[0].bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.[0].length").doesNotExist())
	            .andExpect(jsonPath("$.[0].width").doesNotExist())
	            .andExpect(jsonPath("$.[0].height").doesNotExist())
	            .andExpect(jsonPath("$.[0].vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.[0].model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.[0].commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.[0].pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].pickupDate").exists())
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists());
	            
	    
	}

	@Test
	public void getMatchingCustReqForVendorReq() throws Exception
	{
		
		freightRequestByCustomerRepository.deleteAll();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad110());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadOpenTataReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoad15());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenTata());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel());
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		TestRequest testRequest=testRequestRepository.save(standardTestRequest());
		
		
		
		this.mockMvc.perform(
	            post("/request/freightByRequestCustomer/getMatchingCustReqForVendorReq")
	                    .content(stanardJsonFreightRequestByVendorDTO(standardFreightRequestByVendorDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());/*
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.[0].noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()));
	          //  .andExpect(jsonPath("$.[0].isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	          //  .andExpect(jsonPath("$.[0].isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.[0].bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.[0].length").doesNotExist())
	            .andExpect(jsonPath("$.[0].width").doesNotExist())
	            .andExpect(jsonPath("$.[0].height").doesNotExist())
	            .andExpect(jsonPath("$.[0].vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.[0].model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.[0].commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.[0].pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].pickupDate").exists())
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists());
	    */
	    
	}
	
}

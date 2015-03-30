package com.projectx.data.controller.handshake;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.handshake.DealDetaisDataFixtures.standardDealDetails;
import static com.projectx.data.fixtures.handshake.DealDetaisDataFixtures.standardJsonDealDetails;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.projectx.data.domain.handshake.DealDetails;
import com.projectx.data.repository.handshake.DealDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)


public class DealDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	DealDetailsRepository dealDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		dealDetailsRepository.deleteAll();
	
	}

	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void save() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/deal/save")
	                    .content(standardJsonDealDetails(standardDealDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	           .andDo(print())
	            .andExpect(status().isCreated())
	          //  .andExpect(jsonPath("$.freightRequestByCustomerId").value(standardDealDetails().getFreightRequestByCustomerId()))
	          //  .andExpect(jsonPath("$.freightRequestByVendorId").value(standardDealDetails().getFreightRequestByVendorId()))
	            .andExpect(jsonPath("$.deductionMode").value(standardDealDetails().getDeductionMode()))
	            .andExpect(jsonPath("$.amount").value(standardDealDetails().getAmount()))
	            .andExpect(jsonPath("$.insertedBy").value(standardDealDetails().getInsertedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updatedBy").value(standardDealDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.updateTime").exists());
	}
	
	@Test
	public void saveAndGetByDealId() throws Exception
	{
		DealDetails dealDetails=dealDetailsRepository.save(standardDealDetails());
		
		this.mockMvc.perform(
	            get("/deal/getByDealId/"+dealDetails.getDealId()))
	                    
	           .andDo(print())
	            .andExpect(status().isOk())
	          //  .andExpect(jsonPath("$.freightRequestByCustomerId").value(standardDealDetails().getFreightRequestByCustomerId()))
	          //  .andExpect(jsonPath("$.freightRequestByVendorId").value(standardDealDetails().getFreightRequestByVendorId()))
	            .andExpect(jsonPath("$.deductionMode").value(standardDealDetails().getDeductionMode()))
	            .andExpect(jsonPath("$.amount").value(standardDealDetails().getAmount()))
	            .andExpect(jsonPath("$.insertedBy").value(standardDealDetails().getInsertedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updatedBy").value(standardDealDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.updateTime").exists());
	}

	
	@Test
	public void saveAndGetByCustomerRequestId() throws Exception
	{
		DealDetails dealDetails=dealDetailsRepository.save(standardDealDetails());
		
		this.mockMvc.perform(
	            get("/deal/getByCustomerRequestId/"+dealDetails.getFreightRequestByCustomerId()))
	                    
	           .andDo(print())
	            .andExpect(status().isOk())
	          //  .andExpect(jsonPath("$.freightRequestByCustomerId").value(standardDealDetails().getFreightRequestByCustomerId()))
	          //  .andExpect(jsonPath("$.freightRequestByVendorId").value(standardDealDetails().getFreightRequestByVendorId()))
	            .andExpect(jsonPath("$.deductionMode").value(standardDealDetails().getDeductionMode()))
	            .andExpect(jsonPath("$.amount").value(standardDealDetails().getAmount()))
	            .andExpect(jsonPath("$.insertedBy").value(standardDealDetails().getInsertedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updatedBy").value(standardDealDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.updateTime").exists());
	}
	
	@Test
	public void saveAndGetByVendorRequestId() throws Exception
	{
		DealDetails dealDetails=dealDetailsRepository.save(standardDealDetails());
		
		this.mockMvc.perform(
	            get("/deal/getByVendorRequestId/"+dealDetails.getFreightRequestByVendorId()))
	                    
	           .andDo(print())
	            .andExpect(status().isOk())
	          //  .andExpect(jsonPath("$.freightRequestByCustomerId").value(standardDealDetails().getFreightRequestByCustomerId()))
	          //  .andExpect(jsonPath("$.freightRequestByVendorId").value(standardDealDetails().getFreightRequestByVendorId()))
	            .andExpect(jsonPath("$.deductionMode").value(standardDealDetails().getDeductionMode()))
	            .andExpect(jsonPath("$.amount").value(standardDealDetails().getAmount()))
	            .andExpect(jsonPath("$.insertedBy").value(standardDealDetails().getInsertedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updatedBy").value(standardDealDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.updateTime").exists());
	}

}

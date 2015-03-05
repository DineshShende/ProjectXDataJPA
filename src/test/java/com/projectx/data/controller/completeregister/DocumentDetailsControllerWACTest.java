package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.standardJsonCustomerDetails;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.projectx.data.fixtures.completeregister.DocumentDetailsDataFixture.*;

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
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class DocumentDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		this.mockMvc.perform(
	            get("/document/clearTestData"));
	}

	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveCustomerDocumentWithError() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocumentWithError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable())
	            ;
		
	}
	
	@Test
	public void saveCustomerDocument() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocument().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocument().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocument().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocument().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
		
	}
	
	@Test
	public void getCustomerDetailsByKey() throws Exception
	{

		this.mockMvc.perform(
	            post("/document/getCustomerDocumentByKey")
	                    .content(standardJsonDocumentKey())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNoContent());
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/getCustomerDocumentByKey")
	                    .content(standardJsonDocumentKey())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocument().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocument().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocument().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocument().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}

	@Test
	public void updateDoucment() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}

	
	@Test
	public void updateDoucmentVerificationStatusAndRemark() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocumentWithNewDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocumentWithNewDocument().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocumentWithNewDocument().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocumentWithNewDocument().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocumentWithNewDocument().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocumentWithNewDocument().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}
	
	@Test
	public void count() throws Exception
	{
		this.mockMvc.perform(
	            get("/document/count"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(
	            get("/document/count"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));

	    
	}

	@Test
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            get("/document/count"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(
	            get("/document/count"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		this.mockMvc.perform(
	            get("/document/clearTestData"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
		this.mockMvc.perform(
	            get("/document/count"))
	             .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
	    
	}
}

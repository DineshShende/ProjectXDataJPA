package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
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
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.repository.completeregister.CustomerDetailsRepository;
import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class CustomerDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVerificationDetailsRepository emailVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		customerDetailsRepository.deleteAll();
		mobileVerificationDetailsRepository.deleteAll();
		emailVerificationDetailsRepository.deleteAll();
		authenticationDetailsRepository.deleteAll();
	}

	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void updateCustomerDetails() throws Exception
	{
		CustomerDetails savedEnity=customerDetailsRepository.save(standardCustomerDetails());
		
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

	@Test
	public void updateCustomerDetailsWithError() throws Exception
	{
		CustomerDetails savedEnity=customerDetailsRepository.save(standardCustomerDetails());
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	}

	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntity() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	          //  .andDo(print())
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
	public void saveCustomerDetailsCopiedFromQuickRegisterEntityError() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntityError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	          //  .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	            
		
	}
	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntityErrorNullFirstName() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntityErrorNullFirstName()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	            
		
	}
	
	
	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntityWithInvalidAddress() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsWithInvalidAddress()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	            
		
	}
	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntityErrorAlreadyReported() throws Exception
	{
		
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	          ;
	           
		this.mockMvc.perform(
				get("/customer/completeregister/count")
				);
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntityNew()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	          .andExpect(status().isAlreadyReported());        
		
	}
	
	@Test
	public void saveCustomerDetailsCopiedFromQuickRegisterEntityErrorWithFirstNameNull() throws Exception
	{
		
		
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntityFirstNull()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	          //  .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	            
		
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
	          //  .andDo(print())
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
	            .andExpect(jsonPath("$.secondaryMobile").doesNotExist())
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
	           // .andDo(print())
	            .andExpect(status().isCreated());
	 
		
		this.mockMvc.perform(
	            get("/customer/completeregister/212"))
	            // .andDo(print())
	            .andExpect(status().isFound())
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
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/completeregister")
	                    .content(standardJsonCustomerDetails(standardCustomerDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		
		this.mockMvc.perform(
				get("/customer/completeregister/count")
				)
				.andExpect(content().string("1"));
		
		this.mockMvc.perform(
				get("/customer/completeregister/clearTestData")
				);
		
		this.mockMvc.perform(
				get("/customer/completeregister/count")
				)
				.andExpect(content().string("0"));
	 
	}

}


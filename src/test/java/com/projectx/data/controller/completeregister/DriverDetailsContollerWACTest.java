package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.DriverDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.MobileVericationDetailsFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.completeregister.DriverDetailsCustomRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@Transactional
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DriverDetailsContollerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	DriverDetailsCustomRepository driverDetailsCustomRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
		mobileVerificationDetailsRepository.deleteAll();
		driverDetailsCustomRepository.deleteAll();
		

	}

	@Test
	public void save() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.result.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.result.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.result.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.result.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.result.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.result.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.result.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.result.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.result.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.result.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.result.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.result.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.result.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.result.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.result.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists());
		
	}
	
	@Test
	public void saveInvalidAddress() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetailsInvalidAddress()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
			
	}
	

	
	
	@Test
	public void saveWithError() throws Exception
	{
		
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetailsWithError()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	}

	@Test
	public void saveWithFailure() throws Exception
	{
		mobileVerificationDetailsRepository
		.save(standardCustomerMobileVerificationDetails(standardDriverDetails().getMobile()));
	
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
	    
	}

	
	
	
	@Test
	public void getById() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		this.mockMvc.perform(
	            get("/driver/getById/"+driverDetails.getDriverId())
	                    )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
		
	}
	
	@Test
	public void getByLicenceNumber() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		this.mockMvc.perform(
	            get("/driver/getByLicenceNumber/"+driverDetails.getLicenceNumber())
	                    )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
		
	}
	
	
	@Test
	public void update() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetailsNewMobileAndFirstName(driverDetails.getDriverId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.result.firstName").value(standardDriverDetailsNewMobileAndFirstName(driverDetails.getDriverId()).getFirstName()))
		 		.andExpect(jsonPath("$.result.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.result.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.result.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.result.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.result.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.result.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.result.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.result.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.result.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.result.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.result.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.result.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.result.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.result.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists());
		
	}
	
	
	@Test
	public void updateFailure() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		mobileVerificationDetailsRepository
		.save(standardCustomerMobileVerificationDetails(DRIVER_MOBILE_UPDATED));
		
		this.mockMvc.perform(
	            post("/driver")
	                    .content(standardDriverJson(standardDriverDetailsNewMobileAndFirstName(driverDetails.getDriverId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported());
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		/*
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
		*/
	}
	
	
	@Test
	public void deleteById() throws Exception
	{
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		assertEquals(1, driverDetailsCustomRepository.count().intValue());
		
		
		this.mockMvc.perform(
	            get("/driver/deleteById/"+driverDetails.getDriverId())
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, driverDetailsCustomRepository.count().intValue());
	    
	}
	
	
	@Test
	public void clearTest() throws Exception
	{
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		assertEquals(1, driverDetailsCustomRepository.count().intValue());
		
		
		this.mockMvc.perform(
	            get("/driver/clearTestData")
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, driverDetailsCustomRepository.count().intValue());
	    
	}
	
	
	@Test
	public void count() throws Exception
	{
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		assertEquals(1, driverDetailsCustomRepository.count().intValue());
		
		
		this.mockMvc.perform(
	            get("/driver/count")
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
			    
	}

	
	@Test
	public void updateMobileAndMobileVerificationStatus() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		UpdateMobileVerificationStatusUpdatedByDTO mobileVerificationStatusDTO=new UpdateMobileVerificationStatusUpdatedByDTO(driverDetails.getDriverId(), driverDetails.getMobile(), 
				true,DRIVER_UPDATED_BY,driverDetails.getDriverId());
		
		
		this.mockMvc.perform(
	            post("/driver/updateMobileAndMobileVerificationStatus")
	                    .content(standardUpdateMobileVerificationStatusDTOJson(mobileVerificationStatusDTO))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(1))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	            
	}
	
	
	@Test
	public void getDriversByVendorId() throws Exception
	{
		DriverDetails driverDetails=driverDetailsCustomRepository.save(standardDriverDetails());
		
		DriverDetails driverDetailsOther=driverDetailsCustomRepository.save(standardDriverDetailsOther());
		
		this.mockMvc.perform(
	            get("/driver/getDriversByVendorId/"+standardDriverDetails().getVendorId())
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.[0].firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.[0].middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.[0].lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.[0].bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.[0].homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.[0].homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.[0].homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.[0].homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.[0].homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.[0].homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.[0].mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.[0].isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.[0].homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.[0].isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.[0].isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.[0].licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.[0].language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists())
	            
	            .andExpect(jsonPath("$.[1].firstName").value(standardDriverDetailsOther().getFirstName()))
		 		.andExpect(jsonPath("$.[1].middleName").value(standardDriverDetailsOther().getMiddleName()))
		 		.andExpect(jsonPath("$.[1].lastName").value(standardDriverDetailsOther().getLastName()))
		 		.andExpect(jsonPath("$.[1].bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.[1].homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.[1].homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.[1].homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.[1].homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.[1].homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.[1].homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.[1].mobile").value(standardDriverDetailsOther().getMobile()))
	            .andExpect(jsonPath("$.[1].isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.[1].homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.[1].isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.[1].isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.[1].licenceNumber").value(standardDriverDetailsOther().getLicenceNumber()))
	            .andExpect(jsonPath("$.[1].language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.[1].updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[1].insertTime").exists())
	            .andExpect(jsonPath("$.[1].updateTime").exists())
	            ;
		
		
		
	}
	
	
}

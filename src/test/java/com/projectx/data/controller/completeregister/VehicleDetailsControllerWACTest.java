package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.completeregister.VendorDetailsDataFixture.standardJsonVendor;
import static com.projectx.data.fixtures.completeregister.VehicleDetailsDataFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.After;
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
import com.projectx.data.domain.completeregister.VehicleBrandDetails;
import com.projectx.data.domain.completeregister.VehicleDetails;
import com.projectx.data.repository.completeregister.VehicleBrandDetailsRepositoty;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class VehicleDetailsControllerWACTest {

	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Autowired
	VehicleBrandDetailsRepositoty vehicleBrandDetailsRepositoty;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
		
	}

	@Before
	@After
	public void ClearTestData() {
		vehicleDetailsRepository.deleteAll();
		vehicleBrandDetailsRepositoty.deleteAll();
	}
	
	
	@Test
	public void save() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		
		this.mockMvc.perform(
	            post("/vehicle")
	                    .content((standardVehicleJson(standardVehicleDetails())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.result.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.result.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.result.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 	//	.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.result.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.result.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.result.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.result.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.result.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.result.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.result.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.result.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.result.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.result.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.result.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.result.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.result.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.result.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
	            .andExpect(jsonPath("$.result.updateTime").exists())
	            .andExpect(jsonPath("$.errorMessage").value(""));
	            
	            		
	}
	
	@Test
	public void saveWithError() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		
		this.mockMvc.perform(
	            post("/vehicle")
	                    .content((standardVehicleJson(standardVehicleDetailsError())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isNotAcceptable());
	            
	}
	
	@Test
	public void saveAlreadyReported() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		
		this.mockMvc.perform(
	            post("/vehicle")
	                    .content((standardVehicleJson(standardVehicleDetails())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	    
		
		this.mockMvc.perform(
	            post("/vehicle")
	                    .content((standardVehicleJson(standardVehicleDetails())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported())
	            .andExpect(jsonPath("$.errorMessage").value("REGISTRATION_NUMBER_CHASIS_NUMBER_ALREADY_REPORTED"))
	            .andExpect(jsonPath("$.result").doesNotExist());
	            
	}

	@Test
	public void getById() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/getById/"+vehicleDetails.getVehicleId())
	                )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		//.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	            
	            		
	}

	@Test
	public void getByRegistrationNumber() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		this.mockMvc.perform(
	            get("/vehicle/getByRegistrationNumber/"+"212")
	                )
	            .andDo(print())
	            .andExpect(status().isNoContent());
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/getByRegistrationNumber/"+vehicleDetails.getRegistrationNumber())
	                )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		//.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	            
	            		
	}


	@Test
	public void getByChassisNumber() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		this.mockMvc.perform(
	            get("/vehicle/getByChassisNumber/"+"212")
	                )
	            .andDo(print())
	            .andExpect(status().isNoContent());
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/getByChassisNumber/"+vehicleDetails.getChassisNumber())
	                )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		//.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	            
	            		
	}

	
	@Test
	public void deleteById() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/deleteById/"+vehicleDetails.getVehicleId())
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, vehicleDetailsRepository.count());
	}
	
	
	@Test
	public void clearTestData() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/clearTestData")
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, vehicleDetailsRepository.count());
	}
	
	
	@Test
	public void count() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vehicle/count")
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		
	}
	
	
	@Test
	public void getVehiclesByVendorId() throws Exception
	{
		vehicleDetailsRepository.deleteAll();
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetails vehicleDetailsOther=vehicleDetailsRepository.save(standardVehicleDetailsOther());
		
		this.mockMvc.perform(
	            get("/vehicle/getVehiclesByVendorId/"+vehicleDetails.getVendorId())
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.[0].ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.[0].ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.[0].ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		//.andExpect(jsonPath("$.[0].vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[0].vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.[0].isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.[0].registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.[0].chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.[0].loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.[0].numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.[0].permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.[0].insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.[0].insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.[0].insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists())
	            
	             .andExpect(jsonPath("$.[1].ownerFirstName").value(standardVehicleDetailsOther().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.[1].ownerMiddleName").value(standardVehicleDetailsOther().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.[1].ownerLastName").value(standardVehicleDetailsOther().getOwnerLastName()))
		 		//.andExpect(jsonPath("$.[1].vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[1].vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.[1].isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.[1].registrationNumber").value(standardVehicleDetailsOther().getRegistrationNumber()))
	            .andExpect(jsonPath("$.[1].chassisNumber").value(standardVehicleDetailsOther().getChassisNumber()))
	            .andExpect(jsonPath("$.[1].loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.[1].numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.[1].permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.[1].insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.[1].insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.[1].insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.[1].updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[1].insertTime").exists())
	            .andExpect(jsonPath("$.[1].updateTime").exists());
	
	}
}

package com.projectx.data.controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.VehicleBrandDetails;
import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.domain.completeregister.VehicleTypeDetails;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.TestRequest;
import com.projectx.data.repository.request.TestRequestRepository;
import com.projectx.data.service.request.FreightRequestByVendorService;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;


@RestController
@RequestMapping("/request/testrequest")
public class TestRquestController {

	@Autowired
	TestRequestRepository testRequestRepository;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(method=RequestMethod.POST)
	public FreightRequestByVendorDTO save(@RequestBody FreightRequestByVendorDTO freightRequestByVendor)
	{
		TestRequest savedEntity=freightRequestByVendorService.toFreightRequestByVendor(freightRequestByVendor);
		try
		{
			savedEntity=testRequestRepository.save(savedEntity);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return freightRequestByVendorService.toFreightRequestByVendorDTO(savedEntity);
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public FreightRequestByVendorDTO getById(@PathVariable Long requestId)
	{
		TestRequest savedEntity=testRequestRepository.findOne(requestId);
		
		return freightRequestByVendorService.toFreightRequestByVendorDTO(savedEntity);
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public Boolean deleteById(@PathVariable Long requestId)
	{
		testRequestRepository.delete(requestId);
		
		return true;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		testRequestRepository.deleteAll();
		
		return true;
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=(int)testRequestRepository.count();
		
		return count;
	}
	
	@RequestMapping(value="/findByVendorId/{vendorId}")
	public List<FreightRequestByVendorDTO> findByVendorId(@PathVariable Long vendorId)
	{
		List<TestRequest> requestList=testRequestRepository.findByVendorId(vendorId);
		
		List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
	
		for(int i=0;i<requestList.size();i++)
			returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return returnList;
	}
	
	
	@RequestMapping(value="/getMatchingVendorReqFromCustomerReq",method=RequestMethod.POST)
	public List<FreightRequestByVendorDTO> getMatchingVendorReqFromCustomerReq(@RequestBody FreightRequestByCustomer freightRequestByCustomer)
	{
		List<TestRequest> requestList=testRequestRepository.getMatchingVendorRequest(freightRequestByCustomer);
		
		List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
		
		for(int i=0;i<requestList.size();i++)
			returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return returnList;
	}
	
	
	/*
	@RequestMapping(value="/test")
	public TestRequest test()
	{
		TestRequest count=new TestRequest(1L, new VehicleDetailsDTO(1L, "ownerFirstName", "ownerMiddleName", "ownerLastName", 
				new VehicleTypeDetails(1L, "vehicleTypeName"), new VehicleBrandDetails(1L, new VehicleTypeDetails(1L, "vehicleTypeName"), "vehicleBrandName", "modelNumber"), "vehicleBodyType", true, "registrationNumber", "chassisNumber", 20, 100, 40, 10, 8, "permitType",
				false, "insuranceNumber", "insuranceCompany", 234L, new Date(), new Date(), "updatedBy"),
				2134, 5643, 234L, new Date(), "(9:00PM", 10, 234L, "NEW", new Date(), new Date(), "updatedBy");
		
		return count;
	}
	*/
}

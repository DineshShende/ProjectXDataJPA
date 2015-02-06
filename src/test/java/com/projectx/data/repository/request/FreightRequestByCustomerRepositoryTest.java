package com.projectx.data.repository.request;

import static org.junit.Assert.*;
import static com.projectx.data.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.data.fixtures.request.FreightRequestByVendorDataFixture.*;
import static com.projectx.data.fixtures.request.TestRequestDataFixtures.*;
import static com.projectx.data.fixtures.completeregister.VehicleDetailsDataFixtures.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.domain.request.TestRequest;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;
import com.projectx.data.repository.completeregister.VendorDetailsRepositoty;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class FreightRequestByCustomerRepositoryTest {
 
	@Autowired
	FreightRequestByCustomerRepository  freightRequestByCustomerRepository;
	
//	@Autowired
//	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VendorDetailsRepositoty vendorDetailsRepositoty;
	
	@Autowired
	TestRequestRepository testRequestRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;

	@Before
	@After
	public void clearData()
	{
		freightRequestByCustomerRepository.deleteAll();
		testRequestRepository.deleteAll();
		vendorDetailsRepositoty.deleteAll();
		vehicleDetailsRepository.deleteAll();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.findOne(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count());
	}
	
	
	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		freightRequestByCustomerRepository.save(savedEntity);
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.findOne(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
		freightRequestByCustomerRepository.delete(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerRepository.count());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
	}

	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count());
		
		freightRequestByCustomerRepository.deleteAll();
		
		assertEquals(0, freightRequestByCustomerRepository.count());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerRepository.count());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	
	@Test
	public void getMatchingCustomerRequest ()
	{
		freightRequestByCustomerRepository.deleteAll();
		
		testRequestRepository.deleteAll();
		
		vendorDetailsRepositoty.deleteAll();
		
		vehicleDetailsRepository.deleteAll();
		
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
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerRepository.getMatchingCustomerRequest(testRequest);
		
		//assertEquals(3, list.size());
	}
	
	@Test
	public void getMatchingCustomerRequestBodyTypeNotFlexible()
	{
		freightRequestByCustomerRepository.deleteAll();
		
		testRequestRepository.deleteAll();
		
		vendorDetailsRepositoty.deleteAll();
		
		vehicleDetailsRepository.deleteAll();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadOpenTataReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoad());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenTata());
		
		TestRequest testRequest=testRequestRepository.save(standardTestRequestOpen307());
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerRepository.getMatchingCustomerRequest(testRequest);
		
		//assertEquals(1, list.size());
	}
	
}

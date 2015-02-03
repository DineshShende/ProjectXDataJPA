package com.projectx.data.controller.request;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.repository.request.FreightRequestByCustomerRepository;

@RestController
@RequestMapping("/request/freightByRequestCustomer")
public class FreightRequestByCustomerController {

	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public FreightRequestByCustomer save(@RequestBody FreightRequestByCustomer freightRequestByCustomer)
	{
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(freightRequestByCustomer);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public FreightRequestByCustomer getById(@PathVariable Long requestId)
	{
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.findOne(requestId);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public Boolean deleteById(@PathVariable Long requestId)
	{
		freightRequestByCustomerRepository.delete(requestId);
		
		return true;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		freightRequestByCustomerRepository.deleteAll();
		
		return true;
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=(int)freightRequestByCustomerRepository.count();
		
		return count;
	}
	
	@RequestMapping(value="/findByCustomer/{customerId}")
	public List<FreightRequestByCustomer> findByCustomer(@PathVariable Long customerId)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(customerId);
		
		return requestList;
	}

	
	
	@RequestMapping(value="/test-get-sucess/{id}")
	public ResponseEntity<FreightRequestByCustomer> test(@PathVariable Long id) throws Exception
	{
		//return new FreightRequestByCustomer(requestId, source, destination, pickupDate, noOfVehicles, isFullTruckLoad, isLessThanTruckLoad, capacity, bodyType, grossWeight, length, width, height, vehicleBrand, model, commodity, pickupTime, insertTime, updateTime, updatedBy)
		
		FreightRequestByCustomer result=freightRequestByCustomerRepository.findOne(id);
		
		
		ResponseEntity<FreightRequestByCustomer> res;
		
		if(result.getRequestId()!=null)
		      res=new ResponseEntity<FreightRequestByCustomer>(result, HttpStatus.OK);
		else
			throw new Exception();	
		
		return res;
	}
	
}

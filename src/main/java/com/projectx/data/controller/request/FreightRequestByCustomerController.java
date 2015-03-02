package com.projectx.data.controller.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.repository.request.FreightRequestByCustomerRepository;
import com.projectx.data.service.request.FreightRequestByVendorService;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@RestController
@RequestMapping("/request/freightByRequestCustomer")
public class FreightRequestByCustomerController {

	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByCustomer> save(@Valid @RequestBody FreightRequestByCustomer freightRequestByCustomer,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<FreightRequestByCustomer> result=null;
		
		try{
			FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(freightRequestByCustomer);
			result=new ResponseEntity<FreightRequestByCustomer>(savedEntity, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public ResponseEntity<FreightRequestByCustomer> getById(@PathVariable Long requestId)
	{
		ResponseEntity<FreightRequestByCustomer> result=null;
		
		FreightRequestByCustomer fetchedEntity=freightRequestByCustomerRepository.findOne(requestId);
		
		if(fetchedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<FreightRequestByCustomer>(fetchedEntity, HttpStatus.FOUND);
		
		return result;
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long requestId)
	{
		
		try{
			freightRequestByCustomerRepository.delete(requestId);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
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
	public ResponseEntity<List<FreightRequestByCustomer>> findByCustomer(@PathVariable Long customerId)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(customerId);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(requestList, HttpStatus.OK);
	}

	@RequestMapping(value="/getMatchingCustReqForVendorReq",method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByCustomer>> getMatchingCustReqForVendorReq(@RequestBody FreightRequestByVendorDTO freightRequestByVendor)
	{
		FreightRequestByVendor testRequest=freightRequestByVendorService.toFreightRequestByVendor(freightRequestByVendor);
		
		List<FreightRequestByCustomer> resultList=freightRequestByCustomerRepository.getMatchingCustomerRequest(testRequest);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(resultList, HttpStatus.OK);
	}
	
	
	
}

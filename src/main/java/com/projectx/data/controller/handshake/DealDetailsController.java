package com.projectx.data.controller.handshake;

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

import com.projectx.data.config.Constants;
import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;
import com.projectx.data.domain.handshake.DealDetails;
import com.projectx.data.repository.handshake.DealDetailsRepository;


@RestController
@RequestMapping(value="/deal")
public class DealDetailsController {

	
	@Autowired
	DealDetailsRepository dealDetailsRepository;
	
	@Autowired
	Constants constants;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<DealDetails> save(@Valid @RequestBody DealDetails dealDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			DealDetails savedEntity=dealDetailsRepository.save(dealDetails);
			return new ResponseEntity<DealDetails>(savedEntity, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
	}
	
	
	@RequestMapping(value="/getByDealId/{dealId}",method=RequestMethod.GET)
	public ResponseEntity<DealDetails> getByDealId(@PathVariable Long dealId)
	{
		
			DealDetails fetchedEntity=dealDetailsRepository.findOne(dealId);
			
			if(fetchedEntity==null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<DealDetails>(fetchedEntity, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/getByCustomerRequestId/{customerRequestId}",method=RequestMethod.GET)
	public ResponseEntity<DealDetails> getByCustomerRequestId(@PathVariable Long customerRequestId)
	{
		
			DealDetails fetchedEntity=dealDetailsRepository.findByFreightRequestByCustomerId(customerRequestId);
			
			if(fetchedEntity==null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<DealDetails>(fetchedEntity, HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/getByVendorRequestId/{vendorRequestId}",method=RequestMethod.GET)
	public ResponseEntity<DealDetails> getByVendorRequestId(@PathVariable Long vendorRequestId)
	{
		
			DealDetails fetchedEntity=dealDetailsRepository.findByFreightRequestByVendorId(vendorRequestId);
			
			if(fetchedEntity==null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<DealDetails>(fetchedEntity, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			dealDetailsRepository.deleteAll();
		
		return true;
	}
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Integer count()
	{
		return (int)dealDetailsRepository.count();
		
	}
	
}

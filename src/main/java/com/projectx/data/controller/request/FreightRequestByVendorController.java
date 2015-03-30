package com.projectx.data.controller.request;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.config.Constants;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.exception.request.VehicleDetailsNotFoundException;
import com.projectx.data.repository.request.FreightRequestByVendorRepository;
import com.projectx.data.service.request.FreightRequestByVendorService;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.UpdateReservationStatus;


@RestController
@RequestMapping("/request/freightRequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	Constants constants;
	
	@Autowired
	FreightRequestByVendorRepository testRequestRepository;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByVendorDTO> save(@Valid @RequestBody FreightRequestByVendorDTO freightRequestByVendor,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<FreightRequestByVendorDTO> result=null;
		
		FreightRequestByVendor savedEntity=null;
		try{
			savedEntity=freightRequestByVendorService.toFreightRequestByVendor(freightRequestByVendor);
		}catch(VehicleDetailsNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try{
			savedEntity=testRequestRepository.save(savedEntity);
			result=new ResponseEntity<FreightRequestByVendorDTO>(freightRequestByVendorService.toFreightRequestByVendorDTO(savedEntity),
					HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public ResponseEntity<FreightRequestByVendorDTO> getById(@PathVariable Long requestId)
	{
		ResponseEntity<FreightRequestByVendorDTO> result=null;
		
		FreightRequestByVendor savedEntity=testRequestRepository.findOne(requestId);
		
		if(savedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<FreightRequestByVendorDTO>(freightRequestByVendorService.toFreightRequestByVendorDTO(savedEntity), HttpStatus.FOUND);
			
		return result;
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long requestId)
	{
				
		try{
			testRequestRepository.delete(requestId);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(value="/findByVendorId/{vendorId}")
	public List<FreightRequestByVendorDTO> findByVendorId(@PathVariable Long vendorId)
	{
		List<FreightRequestByVendor> requestList=testRequestRepository.findByVendorId(vendorId);
		
		List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
	
		for(int i=0;i<requestList.size();i++)
			returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return returnList;
	}
	
	
	@RequestMapping(value="/updateReservationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updatereservationStatus(@Valid @RequestBody UpdateReservationStatus updateReservationStatus,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Integer result=testRequestRepository.updateVerificationStatus(updateReservationStatus.getEntityIdTobeReserved(),
				updateReservationStatus.getOldStatus(), updateReservationStatus.getNewStatus(), updateReservationStatus.getEntityIdTobeReservedFor(),
				new Date());
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
 	
	@RequestMapping(value="/getMatchingVendorReqFromCustomerReq",method=RequestMethod.POST)
	public List<FreightRequestByVendorDTO> getMatchingVendorReqFromCustomerReq(@RequestBody FreightRequestByCustomer freightRequestByCustomer)
	{
		List<FreightRequestByVendor> requestList=testRequestRepository.getMatchingVendorRequest(freightRequestByCustomer);
		
		List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
		
		for(int i=0;i<requestList.size();i++)
			returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return returnList;
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=(int)testRequestRepository.count();
		
		return count;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			testRequestRepository.deleteAll();
		
		return true;
	}
	
}

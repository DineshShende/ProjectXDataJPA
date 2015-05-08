package com.projectx.data.controller.request;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.projectx.data.domain.commdto.ResponseDTO;
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
	
	@Value("${VEHICLE_DETAILS_NOT_FOUND_BY_REGISTRATION_NUMBER}")
	private String VEHICLE_DETAILS_NOT_FOUND_BY_REGISTRATION_NUMBER;
	
	@Value("${FREIGHT_REQUEST_BY_VENDOR_BY_ID_NOT_FOUND}")
	private String FREIGHT_REQUEST_BY_VENDOR_BY_ID_NOT_FOUND;
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<FreightRequestByVendorDTO>> save(@Valid @RequestBody FreightRequestByVendorDTO freightRequestByVendor,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<FreightRequestByVendorDTO>> result=null;
		
		FreightRequestByVendor savedEntity=null;
		try{
			savedEntity=freightRequestByVendorService.toFreightRequestByVendor(freightRequestByVendor);
		}catch(VehicleDetailsNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<FreightRequestByVendorDTO>>(new ResponseDTO<FreightRequestByVendorDTO>(VEHICLE_DETAILS_NOT_FOUND_BY_REGISTRATION_NUMBER,null),HttpStatus.OK);
		}
		
		try{
			savedEntity=testRequestRepository.save(savedEntity);
			result=new ResponseEntity<ResponseDTO<FreightRequestByVendorDTO>>(new ResponseDTO<FreightRequestByVendorDTO>("",freightRequestByVendorService.toFreightRequestByVendorDTO(savedEntity)),
					HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public ResponseEntity<FreightRequestByVendor> getById(@PathVariable Long requestId)
	{
		ResponseEntity<FreightRequestByVendor> result=null;
		
		FreightRequestByVendor savedEntity=testRequestRepository.findOne(requestId);
		
		if(savedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<FreightRequestByVendor>(savedEntity, HttpStatus.FOUND);
			
		return result;
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@PathVariable Long requestId)
	{
				
		try{
			testRequestRepository.delete(requestId);
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",true), HttpStatus.OK);
		}catch(EmptyResultDataAccessException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(FREIGHT_REQUEST_BY_VENDOR_BY_ID_NOT_FOUND,null), HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(value="/findByVendorId/{vendorId}")
	public List<FreightRequestByVendor> findByVendorId(@PathVariable Long vendorId)
	{
		List<FreightRequestByVendor> requestList=testRequestRepository.findByVendorId(vendorId);
		
		//List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
	
		//for(int i=0;i<requestList.size();i++)
		//	returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return requestList;
	}
	
	
	@RequestMapping(value="/updateReservationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updatereservationStatus(@Valid @RequestBody UpdateReservationStatus updateReservationStatus,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		Integer result=testRequestRepository.updateVerificationStatus(updateReservationStatus.getEntityIdTobeReserved(),
				updateReservationStatus.getOldStatus(), updateReservationStatus.getNewStatus(), 
				updateReservationStatus.getEntityIdTobeReservedFor(),new Date(),
				updateReservationStatus.getUpdatedBy(),updateReservationStatus.getUpdatedById());
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
 	
	@RequestMapping(value="/getMatchingVendorReqFromCustomerReq",method=RequestMethod.POST)
	public List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(@RequestBody FreightRequestByCustomer freightRequestByCustomer)
	{
		List<FreightRequestByVendor> requestList=testRequestRepository.getMatchingVendorRequest(freightRequestByCustomer);
		
		//List<FreightRequestByVendorDTO> returnList=new ArrayList<FreightRequestByVendorDTO>();
		
		//for(int i=0;i<requestList.size();i++)
		//	returnList.add(freightRequestByVendorService.toFreightRequestByVendorDTO(requestList.get(i)));
		
		return requestList;
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

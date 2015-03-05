package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

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
import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.repository.completeregister.VendorDetailsCustomRepository;
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	Constants constants;
	
	@Autowired
	VendorDetailsCustomRepository vendorDetailsRepository;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> save(@Valid @RequestBody VendorDetails vendorDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<VendorDetails> result=null;
		
		try{
			VendorDetails savedEntity=vendorDetailsRepository.save(vendorDetails);
			result=new ResponseEntity<VendorDetails>(savedEntity, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> update(@Valid @RequestBody VendorDetails vendorDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<VendorDetails> result=null;
		
		try{
			VendorDetails updatedEntity=vendorDetailsRepository.update(vendorDetails);
			result=new ResponseEntity<VendorDetails>(updatedEntity, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getById/{vendorId}")
	public ResponseEntity<VendorDetails> findOne(@PathVariable("vendorId") Long vendorId)
	{
		ResponseEntity<VendorDetails> result=null;
		
		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		if(fetchedEntity==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<VendorDetails>(fetchedEntity, HttpStatus.FOUND);
		
		return result;
	}
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateMobileVerificationStatus(@Valid @RequestBody UpdateMobileVerificationStatusUpdatedByDTO updateVerificationStatusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		try{
		
			Integer updateStatus=vendorDetailsRepository.updateMobileAndVerificationStatus(updateVerificationStatusDTO.getCustomerId(),
					updateVerificationStatusDTO.getMobile(),updateVerificationStatusDTO.getStatus(),updateVerificationStatusDTO.getUpdatedBy());
			
			result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<Integer> updateEmailVerificationStatus(@Valid @RequestBody UpdateEmailVerificationStatusUpdatedByDTO updateVerificationStatusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Integer> result=null;
		
		Integer updateStatus=vendorDetailsRepository.updateEmailAndVerificationStatus(updateVerificationStatusDTO.getCustomerId(), 
				updateVerificationStatusDTO.getEmail(),updateVerificationStatusDTO.getStatus(),updateVerificationStatusDTO.getUpdatedBy());
		
		result=new ResponseEntity<Integer>(updateStatus, HttpStatus.OK);
		
		return result;
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer fetchedResult=vendorDetailsRepository.count();
		
		return fetchedResult;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			vendorDetailsRepository.deleteAll();
		
		return true;
	}
}

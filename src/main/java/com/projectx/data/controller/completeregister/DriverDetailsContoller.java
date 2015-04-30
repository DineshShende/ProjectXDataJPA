package com.projectx.data.controller.completeregister;

import static com.projectx.data.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.projectx.data.domain.commdto.ResponseDTO;
import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.repository.completeregister.DriverDetailsCustomRepository;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;

@RestController
@RequestMapping(value="/driver")
public class DriverDetailsContoller {

	@Autowired
	Constants constants;
	
	@Autowired
	DriverDetailsCustomRepository driverDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Value("${MOBILE_ALREADY_REPORTED}")
	private String MOBILE_ALREADY_REPORTED;
	
	@Value("${MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED}")
	private String MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED;
	
	@Value("${DRIVING_LICENCE_ALREADY_REPORTED}")
	private String DRIVING_LICENCE_ALREADY_REPORTED;
	
	@Value("${ALREADY_REPORTED}")
	private String ALREADY_REPORTED;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<DriverDetails>> save(@Valid @RequestBody DriverDetails driverDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<DriverDetails>> savedEntityResponse=null;
		
		DriverDetails savedEntity=null;
		
		try
		{
			savedEntity=driverDetailsRepository.save(driverDetails);
			
			savedEntityResponse=new ResponseEntity<ResponseDTO<DriverDetails>>(new ResponseDTO<DriverDetails>("",savedEntity), HttpStatus.CREATED);
		}
		catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			if(mobileVerificationDetailsRepository.findByMobile(driverDetails.getMobile())!=null)
					errorMessage.append(MOBILE_ALREADY_REPORTED);
			
			if(driverDetailsRepository.findByLicenceNumber(driverDetails.getLicenceNumber())!=null)
				errorMessage.append(DRIVING_LICENCE_ALREADY_REPORTED);
			
			errorMessage.append(ALREADY_REPORTED);
			
			savedEntityResponse=new ResponseEntity<ResponseDTO<DriverDetails>>(new ResponseDTO<DriverDetails>(errorMessage.toString(),null),
					HttpStatus.ALREADY_REPORTED);
		}
		
		return savedEntityResponse;
				
	}
	
	
	@RequestMapping(value="/updateMobileAndMobileVerificationStatus",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Integer>> updateMobileAndMobileVerificationStatus(@Valid @RequestBody UpdateMobileVerificationStatusUpdatedByDTO mobileVerificationStatusDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<Integer>> result=null;
		
		Integer status=null;
		
		try{
		
			status=driverDetailsRepository
				.updateMobileAndMobileVerificationStatus(mobileVerificationStatusDTO.getCustomerId(), mobileVerificationStatusDTO.getMobile(),
						mobileVerificationStatusDTO.getStatus(),mobileVerificationStatusDTO.getUpdatedBy(),mobileVerificationStatusDTO.getUpdatedById());
			
			result=new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>("",status), HttpStatus.OK);
			
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			errorMessage.append(MOBILE_DUE_TO_UPDATE_INCONSISTENCY_ALREADY_REPORTED);
			errorMessage.append(ALREADY_REPORTED);
			
			return new ResponseEntity<ResponseDTO<Integer>>(new ResponseDTO<Integer>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED);
		}
		
		
		
		return result;
	}
	
	@RequestMapping(value="/getDriversByVendorId/{vendorId}")
	public ResponseEntity<List<DriverDetails>> getDriversByVendorId(@PathVariable Long vendorId)
	{
		List<DriverDetails> driverList=driverDetailsRepository.getDriverListByVendorId(vendorId);
		
		return new ResponseEntity<List<DriverDetails>>(driverList, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getById/{driverId}")
	public ResponseEntity<DriverDetails> findOne(@PathVariable Long driverId)
	{
		ResponseEntity<DriverDetails> result=null;
		
		DriverDetails driverDetails=driverDetailsRepository.findOne(driverId);
		
		if(driverDetails==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<DriverDetails>(driverDetails, HttpStatus.FOUND);
			
		return result;
	}
	
	@RequestMapping(value="/getByLicenceNumber/{licenceNumber}")
	public ResponseEntity<DriverDetails> findByLicenceNumber(@PathVariable String licenceNumber)
	{
		ResponseEntity<DriverDetails> result=null;
		
		DriverDetails driverDetails=driverDetailsRepository.findByLicenceNumber(licenceNumber);
		
		if(driverDetails==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<DriverDetails>(driverDetails, HttpStatus.FOUND);
			
		return result;
	}
	
	@RequestMapping(value="/deleteById/{driverId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long driverId)
	{
		try
		{
			driverDetailsRepository.deleteByKey(driverId);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
			
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer driverDetails=driverDetailsRepository.count();
		
		return driverDetails;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			driverDetailsRepository.deleteAll();
		
		return true;
	}
}

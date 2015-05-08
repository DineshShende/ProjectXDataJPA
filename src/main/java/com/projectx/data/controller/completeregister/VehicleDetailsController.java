package com.projectx.data.controller.completeregister;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.projectx.data.domain.completeregister.VehicleDetails;
import com.projectx.data.repository.completeregister.VehicleDetailsRepository;

import static com.projectx.data.config.Constants.*;

@RestController
@RequestMapping(value="/vehicle")
@PropertySource(value="classpath:/application.properties")
public class VehicleDetailsController {

	@Autowired
	Constants constants;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Value("${ALREADY_REPORTED}")
	private String ALREADY_REPORTED;

	@Value("${REGISTRATION_NUMBER_ALREADY_REPORTED}")
	private String 	REGISTRATION_NUMBER_ALREADY_REPORTED;
	
	@Value("${CHASIS_NUMBER_ALREADY_REPORTED}")
	private String CHASIS_NUMBER_ALREADY_REPORTED;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleDetails>> save(@Valid @RequestBody VehicleDetails vehicleDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<VehicleDetails>> result=null;
		
		try{
			VehicleDetails savedEntity=vehicleDetailsRepository.save(vehicleDetails);
			result=new ResponseEntity<ResponseDTO<VehicleDetails>>(new ResponseDTO<VehicleDetails>("",savedEntity), HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			StringBuilder errorMessage=new StringBuilder();
			
			VehicleDetails detailsRegistartionNumber=vehicleDetailsRepository
					.findByRegistrationNumber(vehicleDetails.getRegistrationNumber());
			
			if(detailsRegistartionNumber!=null)
				errorMessage.append(REGISTRATION_NUMBER_ALREADY_REPORTED);
					
			
			VehicleDetails detailsChasisNumber=vehicleDetailsRepository.findByChassisNumber(vehicleDetails.getChassisNumber());
			
			if(detailsChasisNumber!=null)
				errorMessage.append(CHASIS_NUMBER_ALREADY_REPORTED);
			
			errorMessage.append(ALREADY_REPORTED);
			
			result=new ResponseEntity<ResponseDTO<VehicleDetails>>(new ResponseDTO<VehicleDetails>(errorMessage.toString(),null), HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getVehiclesByVendorId/{vendorId}")
	public ResponseEntity<List<VehicleDetails>> getVehiclesByVendorId(@PathVariable Long vendorId)
	{
		List<VehicleDetails> vehicleDetailsList=vehicleDetailsRepository.getVehiclesByVendorId(vendorId);
		
		return new ResponseEntity<List<VehicleDetails>>(vehicleDetailsList, HttpStatus.OK);
	}



	@RequestMapping(value="/getByRegistrationNumber/{registrationNumber}")
	public ResponseEntity<VehicleDetails> findRegistrationNumber(@PathVariable String registrationNumber)
	{
		ResponseEntity<VehicleDetails> result=null;
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.findByRegistrationNumber(registrationNumber);
		
		if(vehicleDetails ==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<VehicleDetails>(vehicleDetails, HttpStatus.FOUND);
				
		return result;
	}

	@RequestMapping(value="/getByChassisNumber/{chassisNumber}")
	public ResponseEntity<VehicleDetails> findByChassisNumber(@PathVariable String chassisNumber)
	{
		ResponseEntity<VehicleDetails> result=null;
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.findByChassisNumber(chassisNumber);
		
		if(vehicleDetails ==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<VehicleDetails>(vehicleDetails, HttpStatus.FOUND);
				
		return result;
	}
	
	@RequestMapping(value="/getById/{vehicleId}")
	public ResponseEntity<VehicleDetails> findOne(@PathVariable Long vehicleId)
	{
		ResponseEntity<VehicleDetails> result=null;
		
		VehicleDetails vehicleDetails=vehicleDetailsRepository.findOne(vehicleId);
	
		if(vehicleDetails ==null)
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			result=new ResponseEntity<VehicleDetails>(vehicleDetails, HttpStatus.FOUND);
		
		return result;
	}
	
	@RequestMapping(value="/deleteById/{vehicleId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long vehicleId)
	{
		ResponseEntity<Boolean> result=null;
		
		try{
		vehicleDetailsRepository.delete(vehicleId);
		result=new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(EmptyResultDataAccessException e)
		{
			result=new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return result;
	}

	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer vehicleDetails=(int) vehicleDetailsRepository.count();
		
		return vehicleDetails;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			vehicleDetailsRepository.deleteAll();
		
		return true;
	}
}

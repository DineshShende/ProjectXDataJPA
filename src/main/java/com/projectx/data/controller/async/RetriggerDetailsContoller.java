package com.projectx.data.controller.async;

import java.util.ArrayList;
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

import com.projectx.data.config.Constants;
import com.projectx.data.domain.async.RetriggerDetails;
import com.projectx.data.repository.async.RetriggerDetailsRepository;

import static com.projectx.data.config.Constants.*;

@RestController
@RequestMapping(value="/retriggerDetails")
public class RetriggerDetailsContoller {

	@Autowired
	Constants constants;
	
	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RetriggerDetails> save(@Valid @RequestBody RetriggerDetails retriggerDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		
		return new ResponseEntity<RetriggerDetails>(savedEntity, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public ResponseEntity<List<RetriggerDetails>> findAll()
	{
		List<RetriggerDetails> list=new ArrayList<RetriggerDetails>();
		
		Iterable<RetriggerDetails> iterator=retriggerDetailsRepository.findAll();
		
		iterator.forEach(e->list.add(e));
		
		return new ResponseEntity<List<RetriggerDetails>>(list, HttpStatus.OK);
	}

	@RequestMapping(value="/deleteById/{retriggerId}",method=RequestMethod.GET)
	public ResponseEntity<Boolean> deleteById(@PathVariable Long retriggerId)
	{
		try{
		retriggerDetailsRepository.delete(retriggerId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			retriggerDetailsRepository.deleteAll();
		
		return true;
	}
	
}

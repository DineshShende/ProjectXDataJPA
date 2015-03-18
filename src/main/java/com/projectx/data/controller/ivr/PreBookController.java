package com.projectx.data.controller.ivr;

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
import com.projectx.data.domain.ivr.PreBookEntity;
import com.projectx.data.repository.ivr.PreBookRepository;


@RestController
@RequestMapping(value="/preBook")
public class PreBookController {
	
	@Autowired
	PreBookRepository preBookRepository;
	
	@Autowired
	Constants constants;

	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<PreBookEntity> save(@Valid @RequestBody PreBookEntity preBookEntity,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
		PreBookEntity entity=preBookRepository.save(preBookEntity);
		return new ResponseEntity<>(entity,HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		
	}
	
	@RequestMapping(value="/getByFreightRequestByCustomerId/{freightRequestByCustomerId}")
	public ResponseEntity<PreBookEntity> getByFreightRequestByCustomerId(@PathVariable Long freightRequestByCustomerId)
	{
		PreBookEntity preBookEntity=preBookRepository.findByFreightRequestByCustomerId(freightRequestByCustomerId);
		
		if(preBookEntity==null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<PreBookEntity>(preBookEntity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteByFreightRequestByCustomerId/{freightRequestByCustomerId}")
	public ResponseEntity<Integer> deleteByFreightRequestByCustomerId(@PathVariable Long freightRequestByCustomerId)
	{
		try{
			Integer result=preBookRepository.deleteByfreightRequestByCustomerId(freightRequestByCustomerId);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		return (int)preBookRepository.count();
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		if(!constants.SPRING_PROFILE_ACTIVE.equals(SPRING_PROFILE_PRODUCTION))
			preBookRepository.deleteAll();
		
		return true;
	}

}

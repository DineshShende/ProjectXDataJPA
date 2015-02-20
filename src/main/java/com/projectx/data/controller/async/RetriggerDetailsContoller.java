package com.projectx.data.controller.async;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.async.RetriggerDetails;
import com.projectx.data.repository.async.RetriggerDetailsRepository;

@RestController
@RequestMapping(value="/retriggerDetails")
public class RetriggerDetailsContoller {
	
	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public RetriggerDetails save(@RequestBody RetriggerDetails retriggerDetails )
	{
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public List<RetriggerDetails> findAll()
	{
		List<RetriggerDetails> list=new ArrayList<RetriggerDetails>();
		
		Iterable<RetriggerDetails> iterator=retriggerDetailsRepository.findAll();
		
		iterator.forEach(e->list.add(e));
		
		return list;
	}

	@RequestMapping(value="/deleteById/{retriggerId}",method=RequestMethod.GET)
	public Boolean deleteById(@PathVariable Long retriggerId)
	{
		retriggerDetailsRepository.delete(retriggerId);
		
		return true;
	}
}

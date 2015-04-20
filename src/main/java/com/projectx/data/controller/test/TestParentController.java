package com.projectx.data.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.test.TestParent;
import com.projectx.data.repository.test.TestParentRepository;

@RestController
@RequestMapping(value="/testParent")
public class TestParentController {

	@Autowired
	TestParentRepository testParentRepository; 
	
	@RequestMapping(method=RequestMethod.POST)
	public TestParent save(@RequestBody TestParent testParent)
	{
		TestParent parent=testParentRepository.save(testParent);
		
		return parent;
	}
	
}

package com.projectx.data.controller.completeregister;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.domain.completeregister.DocumentKey;
import com.projectx.data.repository.completeregister.DocumetDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;


@RestController
@RequestMapping(value="/document")
public class DocumentController {

	@Autowired
	DocumetDetailsRepository customerDocumentRepository;
	
	
	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> saveCustomerDocument(@Valid @RequestBody DocumentDetails customerDocumet,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<DocumentDetails> result=null;
		
		
		DocumentDetails savedEntity= customerDocumentRepository.save(customerDocumet);
		
		if(savedEntity!=null)
			result=new ResponseEntity<DocumentDetails>(savedEntity, HttpStatus.CREATED);
		else
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		
		return result;
	}
	
	@RequestMapping(value="/getCustomerDocumentByKey",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> getCustomerDocumentById(@RequestBody DocumentKey documentKey)
	{
		ResponseEntity<DocumentDetails> result=null;
		
		DocumentDetails fetchedEntity= customerDocumentRepository.findOne(documentKey);

		if(fetchedEntity!=null)
			result=new ResponseEntity<DocumentDetails>(fetchedEntity, HttpStatus.FOUND);
		else
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return result;
	}
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Integer count()
	{
		return (int)customerDocumentRepository.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		customerDocumentRepository.deleteAll();
		return true;
	}
}

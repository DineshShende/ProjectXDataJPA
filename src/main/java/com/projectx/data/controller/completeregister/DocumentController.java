package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
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
	public DocumentDetails saveCustomerDocument(@RequestBody DocumentDetails customerDocumet)
	{
		DocumentDetails savedEntity= customerDocumentRepository.save(customerDocumet);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getCustomerDocumentByKey",method=RequestMethod.POST)
	public DocumentDetails getCustomerDocumentById(@RequestBody DocumentKey documentKey)
	{
		DocumentDetails fetchedEntity= customerDocumentRepository.findOne(documentKey);

		if(fetchedEntity==null)
			return new DocumentDetails();
		
		return fetchedEntity;
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

package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.repository.completeregister.DocumetDetailsRepository;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;


@RestController
@RequestMapping(value="/customer/quickregister")
public class DocumentController {

	@Autowired
	DocumetDetailsRepository customerDocumentRepository;
	
	
	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public DocumentDetails saveCustomerDocument(@RequestBody DocumentDetails customerDocumet)
	{
		return customerDocumentRepository.save(customerDocumet);
	}
	
	@RequestMapping(value="/getCustomerDocumentById",method=RequestMethod.POST)
	public DocumentDetails getCustomerDocumentById(@RequestBody CustomerIdDTO customerIdDTO)
	{
		//return customerDocumentRepository.findOne(customerIdDTO.getCustomerId());
		return null;
	}
	
	@RequestMapping(value="/getAllCustomerDocument")
	public DocumentDetails getAllCustomerDocument()
	{
		return customerDocumentRepository.findAll().iterator().next();
	}
	
}

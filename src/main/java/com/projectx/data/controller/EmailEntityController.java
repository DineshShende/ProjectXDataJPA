package com.projectx.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.projectx.data.domain.Email;
import com.projectx.data.repository.EmailRepository;


@RestController

@RequestMapping("/email")
public class EmailEntityController {
	
	@Autowired
	EmailRepository emailRepository;
	
	@RequestMapping(value="/addemail",method = RequestMethod.POST)
    public ResponseEntity<Email> addemail(@RequestBody Email email, UriComponentsBuilder builder) {

       
		Email savedEmail=emailRepository.save(email);
		
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/email/getemail/{email}")
                        .buildAndExpand(savedEmail.getEmail()).toUri());

        return new ResponseEntity<Email>(savedEmail, headers, HttpStatus.CREATED);
    }


	@RequestMapping(value="/checkemail",method=RequestMethod.POST)
	public ResponseEntity<Boolean> checkifExistEmail(@RequestBody String emailId)
	{
		Boolean result=emailRepository.exists(emailId);
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	
	
    @RequestMapping(method = RequestMethod.GET, value = "/getemail/{email}")
    public ResponseEntity<Email> viewEmail(@PathVariable String email) {
    	
       	
        Email emailRetrived = emailRepository.findOne(email);
  
         
        return new ResponseEntity<Email>(emailRetrived, HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/getbyemail",method=RequestMethod.POST)
	public ResponseEntity<Email> findByEmail(@RequestBody String emailId)
	{
		Email resultEmail=emailRepository.findOne(emailId);
		
		if(resultEmail==null)
			return new ResponseEntity<Email>(HttpStatus.NOT_FOUND);
		
		System.out.println("In data:"+resultEmail);
		return new ResponseEntity<Email>(resultEmail, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getallemail",method=RequestMethod.GET)
	public ResponseEntity<List<Email>> fetchAllEmails()
	{
		List<Email> emailList=new ArrayList<Email>();
		
		emailList=emailRepository.findAll();
		
		return new ResponseEntity<List<Email>>(emailList, HttpStatus.OK); 
	}
	
	
}

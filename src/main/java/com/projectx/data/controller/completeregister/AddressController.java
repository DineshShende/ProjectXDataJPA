package com.projectx.data.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.repository.completeregister.AddressRepository;

@RestController
@RequestMapping(value="/address")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@RequestMapping(value="/{addressId}",method=RequestMethod.DELETE)
	public void deleteById(@PathVariable("addressId") Long addressId)
	{
		addressRepository.delete(addressId);
		
	}
	
	@RequestMapping(value="/{addressId}",method=RequestMethod.GET)
	public Address getById(@PathVariable("addressId")Long addressId)
	{
		Address fetchedEntity=addressRepository.findOne(addressId);
		
		return fetchedEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Address save(@RequestBody Address address)
	{
		Address savedEntity=addressRepository.save(address);
		
		return savedEntity;
	}
	
}

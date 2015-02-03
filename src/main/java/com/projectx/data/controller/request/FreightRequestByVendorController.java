package com.projectx.data.controller.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.repository.request.FreightRequestByVendorRepository;

@RestController
@RequestMapping("/request/freightByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public FreightRequestByVendor save(@RequestBody FreightRequestByVendor freightRequestByVendor)
	{
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(freightRequestByVendor);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getById/{requestId}",method=RequestMethod.GET)
	public FreightRequestByVendor getById(@PathVariable Long requestId)
	{
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.findOne(requestId);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public Boolean deleteById(@PathVariable Long requestId)
	{
		freightRequestByVendorRepository.delete(requestId);
		
		return true;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		freightRequestByVendorRepository.deleteAll();
		
		return true;
	}
	
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=(int)freightRequestByVendorRepository.count();
		
		return count;
	}
	
	@RequestMapping(value="/findByVendorId/{vendorId}")
	public List<FreightRequestByVendor> findByVendorId(@PathVariable Long vendorId)
	{
		List<FreightRequestByVendor> requestList=freightRequestByVendorRepository.findByVendorId(vendorId);
		
		return requestList;
	}
}

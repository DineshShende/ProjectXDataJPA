package com.projectx.data.repository.async;

import static org.junit.Assert.*;
import static com.projectx.data.fixtures.request.FreightRequestByVendorDataFixtures.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.projectx.data.config.Application;
import com.projectx.data.domain.async.RetriggerDetails;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
public class RetriggerDetailsRepositoryTest {

	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;

	Gson gson=new Gson();
	
	@Before
	public void setUp()
	{
		retriggerDetailsRepository.deleteAll();
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	public void saveAndGet()
	{
		String jsonData=gson.toJson(standardFreightRequestByVendorDTO());
		
		RetriggerDetails retriggerDetails=new RetriggerDetails("abc", jsonData);
		
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		
		assertEquals(savedEntity, retriggerDetailsRepository.findOne(savedEntity.getRetriggerId()));
		
	}
	
	@Test
	public void findAll()
	{
		String jsonData=gson.toJson(standardFreightRequestByVendorDTO());
		
		RetriggerDetails retriggerDetails=new RetriggerDetails("abc", jsonData);
		
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		
		List<RetriggerDetails> list=(List<RetriggerDetails>) retriggerDetailsRepository.findAll();
	
		assertEquals(savedEntity, list.get(0));
	}
	
	@Test
	public void deleteById()
	{
		String jsonData=gson.toJson(standardFreightRequestByVendorDTO());
		
		RetriggerDetails retriggerDetails=new RetriggerDetails("abc", jsonData);
		
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		
		assertEquals(savedEntity, retriggerDetailsRepository.findOne(savedEntity.getRetriggerId()));
		
		retriggerDetailsRepository.delete(savedEntity.getRetriggerId());
		
		assertNull(retriggerDetailsRepository.findOne(savedEntity.getRetriggerId()));
	}

}

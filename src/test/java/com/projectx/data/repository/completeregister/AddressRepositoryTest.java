package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.Address;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles={"Prod"})
@Transactional
public class AddressRepositoryTest {

	@Autowired
	AddressRepository addressRepository; 
	
	@Before
	public void clearTestData()
	{
		//addressRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
	}

	
	@Test
	public void saveAndGetById()
	{
		Address address=addressRepository.save(standardAddress());
		
		assertEquals(standardAddress(), address);
		
		assertEquals(standardAddress(), addressRepository.findOne(address.getAddressId()));
	}
	@Test
	public void deleteById()
	{
	//	assertEquals(0,addressRepository.count());
		
		Address address=new Address(1, "ADDRESS", "ADDRESS", "ADDRESS", "ADDRESS", 111111, new Date(), new Date(), "CUST_ONLINE");
		
		assertEquals(address, addressRepository.save(address));
		
		addressRepository.delete(address.getAddressId());
		
		assertNull(addressRepository.findOne(address.getAddressId()));
		
	//	assertEquals(1,addressRepository.count());
		
	}
	
	
}

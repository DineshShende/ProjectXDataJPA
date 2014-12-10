package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.completeregister.Address;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(value={"Prod"})
@Transactional
public class OneToManyRepositoryTest {

	//@Autowired
	//OneToManyRepository oneToManyRepository; 
	
	
	@Before
	public void setUp()
	{
		//oneToManyRepository.deleteAll();
		
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	/*
	@Test
	@Rollback(value=false)
	public void add()
	{
		List<Address> addressList=new ArrayList<Address>();
		
		OneToManyClass oneToManyClass=new OneToManyClass(1, "sfsff", addressList);
		
		
		oneToManyClass.getAddress().add(new Address( "asdd", "sffs", "dfdf", 1244,oneToManyClass, new Date(), new Date(), "dggg"));
		oneToManyClass.getAddress().add(new Address( "adffsdd", "sfgfgfs", "dfrrtdf", 781244,oneToManyClass, new Date(), new Date(), "dgujjukgg"));
		
		
		
		oneToManyRepository.save(oneToManyClass);
		
	}
	*/
}

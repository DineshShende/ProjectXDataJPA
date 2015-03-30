package com.projectx.data.repository.ivr;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.data.fixtures.ivr.PreBookDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.ivr.PreBookEntity;
import com.projectx.data.repository.ivr.PreBookRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class PreBookRepositoryTest {

	@Autowired
	PreBookRepository preBookRepository;
	
	@Before
	public void setUp()
	{
		preBookRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndGetByCustomerRequestId()
	{
		assertEquals(0, preBookRepository.count());
		
		PreBookEntity preBookEntity=preBookRepository.save(standardPreBookEntity());
		
		assertEquals(1, preBookRepository.count());
		
		assertEquals(preBookEntity, preBookRepository.findByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()));
		
	}
	
	@Test
	public void saveAndDeleteByCustomerRequestId()
	{
		assertEquals(0, preBookRepository.count());
		
		PreBookEntity preBookEntity=preBookRepository.save(standardPreBookEntity());
		
		assertEquals(1, preBookRepository.count());
		
		assertEquals(preBookEntity, preBookRepository.findByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()));
		
		assertEquals(1, preBookRepository.deleteByfreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()).intValue());
		
		assertNull(preBookRepository.findByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()));
		
	}

}

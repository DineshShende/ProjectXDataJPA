package com.projectx.data.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static com.projectx.data.fixtures.DataFixture.*;


import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.Application;
import com.projectx.data.domain.Email;
import com.projectx.data.repository.EmailRepository;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Test")
@Transactional
public class EmailRespositoryIntegrationTest {

	@Autowired
	EmailRepository  emailRepository;
	
	
	
	@Test
	public void addEmailTest() {
		assertEquals(0, emailRepository.findAll().size());
		
		emailRepository.save(standardEmail());
		
		assertEquals(1, emailRepository.findAll().size());
		
		
	}
	
	@Test
	public void checkEmailTest()
	{
		assertEquals(0, emailRepository.findAll().size());
		
		assertEquals(false, emailRepository.exists(EMAIL_EMAIL));
		
		emailRepository.save(standardEmail());
		
		assertEquals(true, emailRepository.exists(EMAIL_EMAIL));
		
		assertThat(emailRepository.exists(EMAIL_EMAIL), is(true));
		
		assertEquals(1, emailRepository.findAll().size());
		
	}
	
	
	
	@Test
	public void findAll()
	{
		emailRepository.save(standardEmail());
		
		List <Email> allEmails=emailRepository.findAll();
		
		assertThat(allEmails.size(), is(1));
        assertThat(allEmails, contains(
                allOf(
                        hasProperty("name", is(EMAIL_NAME)),
                        hasProperty("email", is(EMAIL_EMAIL))
                )
        ));
		
	}
	
	

}

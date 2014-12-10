package com.projectx.data.controller.quickregister;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.data.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.data.repository.quickregister.EmailVerificationDetailsRepository;

public class EmailVerificationControllerStandAloneTest {

	@InjectMocks
	EmailVerificationController emailVerificationController;
	
	@Mock
	EmailVerificationDetailsRepository customerEmailVerificationDetailsRepository;
	
	
	private static final Integer ZERO_COUNT=0;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(emailVerificationController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}
	
	
}

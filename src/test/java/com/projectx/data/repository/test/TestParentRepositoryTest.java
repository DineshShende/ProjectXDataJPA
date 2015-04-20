package com.projectx.data.repository.test;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.test.TestChild;
import com.projectx.data.domain.test.TestParent;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class TestParentRepositoryTest {

	@Autowired
	TestParentRepository testParentRepository;
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		TestChild testChild=new TestChild("childName");
		
		TestParent testParent=new TestParent(1L, "parentName", testChild);
		
		TestParent parent=testParentRepository.save(testParent);
		
		
		parent.getTestChild().setChildName("childName1");
		//testChild.setChildName("childName1");
		
		///testParent.setTestChild(testChild);
		
		testParentRepository.save(testParent);
		
	}

}

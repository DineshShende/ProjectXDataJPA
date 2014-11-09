package com.projectx.data.repository;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.CustomerDocumet;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class CustomerDocumentRepositoryTest {

	@Autowired
	CustomerDocumetRepository customerDocumentRepository;
	
	@Test
	public void test() {
		CustomerDocumet doc=new CustomerDocumet();
		
		doc.setCustomerId(2L);
	
		
		
		File file = new File("/home/dinesh/10582917_673092789452549_6548939224392088956_o.jpg");

	    byte[] bFile = new byte[(int) file.length()];

		try {

		        FileInputStream fileInputStream = new FileInputStream(file);

		        fileInputStream.read(bFile);

		        fileInputStream.close();
	        } catch (Exception e) {

		         e.printStackTrace();

	        }

		doc.setImage(bFile);
		
		assertEquals(0, customerDocumentRepository.count());
		
		customerDocumentRepository.save(doc);
		
		assertEquals(1, customerDocumentRepository.count());
		
		CustomerDocumet fetched=customerDocumentRepository.findOne(2L);
		
		
		
	}
	
}

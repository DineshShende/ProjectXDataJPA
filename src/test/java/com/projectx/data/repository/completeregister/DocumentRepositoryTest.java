package com.projectx.data.repository.completeregister;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

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
import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.repository.completeregister.DocumetDetailsRepository;

import static com.projectx.data.fixtures.completeregister.DocumentDetailsDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")
@Transactional
public class DocumentRepositoryTest {

	@Autowired
	DocumetDetailsRepository customerDocumentRepository;
	
	
	@Before
	public void clearTestData()
	{
		customerDocumentRepository.deleteAll();
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	public void saveDocumentDetails()
	{
		assertEquals(0, customerDocumentRepository.count());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.save(standardDocumentDetails()));
		
		assertEquals(1, customerDocumentRepository.count());
	}
	
	

	@Test
	public void saveAndGetByKeyDocumentDetails()
	{
		assertEquals(0, customerDocumentRepository.count());
		
		assertNull(customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.save(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count());
	}
	
	@Test
	public void updateDocumentAndContentType()
	{
		assertEquals(0, customerDocumentRepository.count());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.save(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count());
		
		DocumentDetails updatedDocument=customerDocumentRepository.save(standardDocumentDetailsWithNewDocumentContentType());
		
		assertEquals(standardDocumentDetailsWithNewDocumentContentType(),
				customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count());
	}
	
	@Test
	public void updateVerificationStatusAndRemark()
	{
		assertEquals(0, customerDocumentRepository.count());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.save(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count());
		
		DocumentDetails updatedDocument=customerDocumentRepository.save(standardDocumentDetailsWithNewVerificationStatusAndRemark());
		
		assertEquals(standardDocumentDetailsWithNewVerificationStatusAndRemark(),
				customerDocumentRepository.findOne(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, customerDocumentRepository.count());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.save(standardDocumentDetails()));
		
		assertEquals(1, customerDocumentRepository.count());
		
		customerDocumentRepository.deleteAll();
		
		assertEquals(0, customerDocumentRepository.count());
	}
	
	/*
	
	@Test
	public void test() {
		DocumentDetails doc=new DocumentDetails();
		
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
		
		DocumentDetails fetched=customerDocumentRepository.findOne(2L);
		
		
		
	}
	*/
}

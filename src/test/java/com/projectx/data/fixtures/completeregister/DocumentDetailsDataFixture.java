package com.projectx.data.fixtures.completeregister;

import java.io.File;
import java.io.FileInputStream;


import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.domain.completeregister.DocumentKey;

import static com.projectx.data.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

public class DocumentDetailsDataFixture {

	
	public static String DOCUMENT_NAME="LightBill";
	public static String DOCUMENT_CONTENT_TYPE="image/jpeg";
	public static Integer DOCUMENT_VERIFICATION_STATUS=1;
	public static String DOCUMENT_VERIFICATION_REMARKS="NOT VERIFIED";
	
	
	public static DocumentKey standardDocumentKey()
	{
		return new DocumentKey(CUST_ID, CUST_TYPE_CUSTOMER, DOCUMENT_NAME);
	}
	
	public static byte[] document()
	{
		File file = new File("/home/dinesh/10582917_673092789452549_6548939224392088956_o.jpg");

		
		
	    byte[] bFile = new byte[(int) file.length()];

		try {

		        FileInputStream fileInputStream = new FileInputStream(file);

		        fileInputStream.read(bFile);

		        
		        fileInputStream.close();
	        } catch (Exception e) {

		         e.printStackTrace();

	        }
		
		return bFile;
	}
	
	public static DocumentDetails standardDocumentDetails()
	{
		return new DocumentDetails(standardDocumentKey(), document(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	
	
}

package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;


public class EmailVerificationDetailsDataFixtures {
	
	public static String CUST_EMAIL_TYPE_PRIMARY="PRIMARY";
	public static String CUST_EMAIL_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	
	public static Date CUST_DATE=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";

	public static EmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new EmailVerificationDetails(standardEmailVerificationKey(), CUST_EMAIL_TYPE_PRIMARY, CUST_EMAIL, CUST_DATE, CUST_COUNT_ZERO, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID, CUST_TYPE_CUSTOMER,CUST_EMAIL, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT);
	}
	
	public static CustomerIdTypeEmailDTO standardCustomerIdTypeEmailDTO()
	{
		return new CustomerIdTypeEmailDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL);
	}
	
	public static EmailVerificationKey standardEmailVerificationKey()
	{
		return new EmailVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_EMAIL);
	}

	
	public static String standardJsonCustomerEmailVerificationDetails()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		//{"key":{"customerId":212,"customerType":1,"email":"dineshshe@gmail.com"},
		
		jsonBuilder.append("{\"key\":{\"customerId\":"+standardEmailVerificationKey().getCustomerId()+",\"customerType\":"+standardEmailVerificationKey().getCustomerType()+",\"email\":\""+standardEmailVerificationKey().getEmail()+"\"},");
		//jsonBuilder.append("\"email\":\""+standardCustomerEmailVerificationDetails().getEmail()+"\",");
		jsonBuilder.append("\"emailType\":\""+standardCustomerEmailVerificationDetails().getEmailType()+"\",");
		jsonBuilder.append("\"emailHash\":\""+standardCustomerEmailVerificationDetails().getEmailHash()+"\",");
		jsonBuilder.append("\"emailHashSentTime\":"+standardCustomerEmailVerificationDetails().getEmailHashSentTime().getTime()+",");
		jsonBuilder.append("\"resendCount\":"+standardCustomerEmailVerificationDetails().getResendCount()+"}");
		
		
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonCustomerIdTypeEmail()
	{
		System.out.println("{\"customerId\":"+standardCustomerIdTypeEmailDTO().getCustomerId()+","
				+ "\"customerType\":"+standardCustomerIdTypeEmailDTO().getCustomerType()+","
				
				+ "\"email\":\""+standardCustomerIdTypeEmailDTO().getEmail()+"\"}");
		
		return "{\"customerId\":"+standardCustomerIdTypeEmailDTO().getCustomerId()+","
				+ "\"customerType\":"+standardCustomerIdTypeEmailDTO().getCustomerType()+","
				
				+ "\"email\":\""+standardCustomerIdTypeEmailDTO().getEmail()+"\"}";
	}
	
	public static String standardJsonUpdateEmailHashAndEmailHashSentTimeAndResendCountDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId()+",");
		jsonBuilder.append("\"customerType\":"+standardCustomerIdTypeEmailDTO().getCustomerType()+",");
		jsonBuilder.append("\"email\":\""+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail()+"\",");
		jsonBuilder.append("\"emailHash\":\""+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash()+"\",");
		jsonBuilder.append("\"emailHashSentTime\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime().getTime()+",");
		jsonBuilder.append("\"resendCount\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	

}

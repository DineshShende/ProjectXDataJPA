package com.projectx.data.fixtures;

import static com.projectx.data.fixtures.CustomerQuickRegisterDataFixture.*;

import com.projectx.data.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;


public class CustomerEmailVerificationDetailsDataFixtures {
	
	public static String CUST_EMAIL_TYPE_PRIMARY="PRIMARY";
	public static String CUST_EMAIL_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	

	public static CustomerEmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new CustomerEmailVerificationDetails(CUST_ID, CUST_EMAIL, CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT);
	}
	
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID, CUST_EMAIL, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT);
	}
	
	public static CustomerIdEmailDTO standardCustomerIdEmailDTO()
	{
		return new CustomerIdEmailDTO(CUST_ID, CUST_EMAIL);
	}
	

	public static String standardJsonCustomerEmailVerificationDetails()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardCustomerEmailVerificationDetails().getCustomerId()+",");
		jsonBuilder.append("\"email\":\""+standardCustomerEmailVerificationDetails().getEmail()+"\",");
		jsonBuilder.append("\"emailType\":\""+standardCustomerEmailVerificationDetails().getEmailType()+"\",");
		jsonBuilder.append("\"emailHash\":\""+standardCustomerEmailVerificationDetails().getEmailHash()+"\",");
		jsonBuilder.append("\"emailHashSentTime\":"+standardCustomerEmailVerificationDetails().getEmailHashSentTime().getTime()+",");
		jsonBuilder.append("\"resendCount\":"+standardCustomerEmailVerificationDetails().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	public static String standardJsonCustomerIdEmail()
	{
		return "{\"customerId\":"+standardCustomerIdEmailDTO().getCustomerId()+","
				+ "\"email\":\""+standardCustomerIdEmailDTO().getEmail()+"\"}";
	}
	
	public static String standardJsonUpdateEmailHashAndEmailHashSentTimeAndResendCountDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId()+",");
		jsonBuilder.append("\"email\":\""+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail()+"\",");
		jsonBuilder.append("\"emailHash\":\""+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash()+"\",");
		jsonBuilder.append("\"emailHashSentTime\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime().getTime()+",");
		jsonBuilder.append("\"resendCount\":"+standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()+"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	

}

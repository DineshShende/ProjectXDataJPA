package com.projectx.data.fixtures.quickregister;

import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;


public class EmailVerificationDetailsDataFixtures {
	
	public static Integer CUST_EMAIL_TYPE_PRIMARY=1;
	public static Integer CUST_EMAIL_TYPE_SECONDARY=2;
	public static Integer CUST_RESEND_COUNT=0;
	
	public static Date CUST_DATE=new Date();
	

	public static Gson gson=new Gson();
	
	public static EmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new EmailVerificationDetails(standardEmailVerificationKey(), CUST_EMAIL, CUST_EMAIL, CUST_DATE, CUST_COUNT_ZERO, CUST_DATE, CUST_DATE,  ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	
	public static EmailVerificationDetails standardCustomerEmailVerificationDetailsWithError()
	{
		return new EmailVerificationDetails(standardEmailVerificationKey(), null, CUST_EMAIL, CUST_DATE, CUST_COUNT_ZERO, CUST_DATE, CUST_DATE,  ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID, CUST_TYPE_CUSTOMER,CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT,CUST_UPDATED_BY,CUST_ID);
	}
	
	public static CustomerIdTypeEmailTypeDTO standardCustomerIdTypeEmailDTO()
	{
		return new CustomerIdTypeEmailTypeDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY);
	}
	
	
	
	public static CustomerIdTypeEmailTypeUpdatedByDTO standardCustomerIdTypeEmailTypeUpdatedByDTO()
	{
		return new CustomerIdTypeEmailTypeUpdatedByDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY,CUST_UPDATED_BY,CUST_ID);
	}
	
	public static EmailVerificationKey standardEmailVerificationKey()
	{
		return new EmailVerificationKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY);
	}

	
	public static String standardJsonCustomerEmailVerificationDetails(EmailVerificationDetails emailVerificationDetails)
	{
			
		
		
		return gson.toJson(emailVerificationDetails);
	}
	
	public static String standardJsonCustomerIdTypeEmail()
	{
		return gson.toJson(standardCustomerIdTypeEmailDTO());
	}
	
	public static String standardJsonCustomerIdTypeEmailUpdatedBy()
	{
		return gson.toJson(standardCustomerIdTypeEmailTypeUpdatedByDTO());
	}
	
	public static String standardJsonEmail()
	{
		return "{\"email\":\""+standardCustomerEmailVerificationDetails().getEmail()+"\"}";
	}
	
	public static String standardJsonEmailKey()
	{
		
		
		return gson.toJson(standardCustomerEmailVerificationDetails().getKey());
	}
	
	public static String standardJsonUpdateEmailHashAndEmailHashSentTimeAndResendCountDTO()
	{
				
		
		
		return gson.toJson(standardUpdateEmailHashAndEmailHashSentTimeDTO());
		
		
	}
	
	

}

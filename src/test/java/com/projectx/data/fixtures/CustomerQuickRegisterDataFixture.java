package com.projectx.data.fixtures;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.EmailDTO;
import com.projectx.rest.domain.MobileDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdateStatusWithCustomerIdDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static String CUST_EMAIL_OTHER="shendedinesh@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";
	public static Integer CUST_MOBILEPIN_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	
	public static Date CUST_LAST_STATUS_CHANGED_TIME=new Date();
	
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,CUST_MOBILE_PIN_SENT_TIME,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,CUST_MOBILE_PIN_SENT_TIME,null,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGED_TIME);
		
	}
	

	public static String standardJsonEmailMobileCustomer()
	{
		return "{\"customerId\":212,\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":9960821869,\"emailHash\":\"02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a\",\"mobilePin\":101010,\"status\":\"EmailMobileVerificationPending\",\"pin\":413133}";
		//return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":\"9960821869\",\"pin\":\"413133\",\"status\":\"\"}";
		        	
	}
	
	public static String standardJsonEmailCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"pin\":413133}";
	}
	

	public static String standardJsonEmailCustomerOther()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"shendedinesh@gmail.com\",\"pin\":413133}";
	}
	
	public static String standardJsonMobileCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"mobile\":9960821869,\"pin\":413133}";
	}
	
	public static CustomerIdDTO standardCustomerId()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
	public static EmailDTO standardGetByEmailDTO()
	{
		return new EmailDTO(CUST_EMAIL);
	}
	
	public static MobileDTO standardGetByMobile()
	{
		return new MobileDTO(CUST_MOBILE);
	}
	
	public static VerifyMobileDTO standardVerifyMobilePinDTO()
	{
		return new VerifyMobileDTO(CUST_ID, CUST_MOBILEPIN);
	}
	
	public static VerifyEmailDTO standardVerifyEmailHashDTO()
	{
		return new VerifyEmailDTO(CUST_ID,CUST_EMAILHASH);
	}
	
	public static UpdateStatusWithCustomerIdDTO standardUpdateStatusWithCustomerId()
	{
		return new UpdateStatusWithCustomerIdDTO(CUST_ID,STATUS_EMAIL_MOBILE_VERIFIED,CUST_LAST_STATUS_CHANGED_TIME);
	}
	
	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID,CUST_EMAILHASH_UPDATED,new Date());
	}
	
	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID,CUST_MOBILEPIN_UPDATED,new Date());
	}


	public static String standardJsonCustomerId()
	{
		return "{\"customerId\":212}";
	}
	
	public static String standardJsonEmailDTO()
	{
		return "{\"email\":\"dineshshe@gmail.com\"}";
	}
	
	public static String standardJsonMobileDTO()
	{
		return "{\"mobile\":9960821869}";
	}
	
	public static String standardJsonVerifyMobileDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":101010}";
		       
	}
	
	public static String standardJsonVerifyEmailDTO()
	{
		return "{\"customerId\":212,\"emailHash\":\"02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a\"}";
		       
	}

	public static String standardJsonUpdateStatusByCustomerIdDTO()
	{
		String formattedDate = dateFormat.format(standardUpdateStatusWithCustomerId().getStatusChangeTime());
		
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":212,\"status\":\"EmailMobileVerified\",\"statusChangeTime\":\"");
		jsonBuilder.append(standardUpdateStatusWithCustomerId().getStatusChangeTime().getTime());
		jsonBuilder.append("\"}");
		
		return jsonBuilder.toString();
	}
	
	
	public static String standardJsonUpdateMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":102010,\"mobilePinSentTime\":\"10-11-2014:16:39:27\"}";
	}
	
	public static String standardJsonUpdateEmailHashDTO()
	{
		return "{\"customerId\":212,\"emailHash\":1020102010,\"emailHashSentTime\":\"10-11-2014:16:39:27\"}";
	}
}

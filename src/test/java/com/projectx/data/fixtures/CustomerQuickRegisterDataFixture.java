package com.projectx.data.fixtures;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.EmailDTO;
import com.projectx.rest.domain.MobileDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.UpdateStatusByMobileDTO;
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
	public static Long CUST_EMAILHASH=1010101010L;
	public static Long CUST_EMAILHASH_UPDATED=1020102010L;
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
		
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}
	

	public static String standardJsonEmailMobileCustomer()
	{
		return "{\"customerId\":212,\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":9960821869,\"emailHash\":10101010,\"mobilePin\":101010,\"status\":\"EmailMobileVerificationPending\",\"pin\":413133}";
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
		return new UpdateStatusWithCustomerIdDTO(CUST_ID,STATUS_MOBILE_VERFIED);
	}
	
	/*
	public static UpdateStatusByMobileDTO standardUpdateStatusByMobileDTO()
	{
		return new UpdateStatusByMobileDTO(CUST_MOBILE,STATUS_MOBILE_VERFIED_EMAIL_PENDING);
	}
	
	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID,CUST_MOBILEPIN_UPDATED);
	}

	*/
	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID,CUST_EMAILHASH_UPDATED);
	}
	
	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID,CUST_MOBILEPIN_UPDATED);
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
		return "{\"customerId\":212,\"emailHash\":1010101010}";
		       
	}

	public static String standardJsonUpdateStatusByCustomerIdDTO()
	{
		return "{\"customerId\":212,\"status\":\"EmailMobileVerified\"}";
	}
	
	/*
	public static String standardJsonUpdateStatusByMobileDTO()
	{
		return "{\"mobile\":9960821869,\"status\":\"UpdatedStatus\"}";
	}
	
	public static String standardJsonUpdateStatusByEmailDTO()
	{
		return "{\"email\":\"dineshshe@gmail.com\",\"status\":\"UpdatedStatus\"}";
	}
	*/
	public static String standardJsonUpdateMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":102010}";
	}
	
	public static String standardJsonUpdateEmailHashDTO()
	{
		return "{\"customerId\":212,\"emailHash\":1020102010}";
	}
}

package com.projectx.data.fixtures.quickregister;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerIdDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.EmailDTO;
import com.projectx.rest.domain.quickregister.MobileDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.rest.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;



public class QuickRegisterDataFixture {

	

	public static Integer ACTOR_ENTITY_SELF_WEB=1;
	
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
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	public static Integer CUST_TYPE_CUSTOMER=1;
	public static Integer CUST_TYPE_VENDER=2;
	
	public static Integer CUST_COUNT_ZERO=0;
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";

	
	public static Boolean CUST_ISMOBILE_VERIFIED=false;
	public static Boolean CUST_ISEMAIL_VERIFIED=false;
	public static Date CUST_INSERT_TIME=new Date();
	public static Date CUST_UPDATE_TIME=new Date();
	public static Integer CUST_UPDATED_BY=1;
	

	static Gson gson=new Gson();
	
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static QuickRegisterEntity standardEmailMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER ,CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileVendor()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_VENDER ,CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerWithErrors()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileVendorWithErrorNullFirstName()
	{
		return new QuickRegisterEntity(CUST_ID, null, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_VENDER ,CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
	}
	
	public static QuickRegisterEntity standardMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
	}
	
	public static QuickRegisterEntity standardEmailCustomer()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
				CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
		
	}
	
	
	
	public static QuickRegisterEntity standardEmailCustomerOther()
	{
		
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL_OTHER, null, CUST_PIN, CUST_ISEMAIL_VERIFIED, CUST_ISMOBILE_VERIFIED,CUST_TYPE_CUSTOMER, CUST_INSERT_TIME, 
					CUST_UPDATE_TIME, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,CUST_ID,CUST_ID);
			
	}
	

	public static String standardJsonQuickRegisterCustomer(QuickRegisterEntity customer)
	{
		System.out.println(gson.toJson(customer));
		
		return gson.toJson(customer);
	}

	
	public static CustomerIdDTO standardCustomerId()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
	
	public static CustomerIdTypeDTO standardCustomerIdTypeDTO()
	{
		return new CustomerIdTypeDTO(CUST_ID, CUST_TYPE_CUSTOMER);
	}
	
	public static EmailDTO standardGetByEmailDTO()
	{
		return new EmailDTO(CUST_EMAIL);
	}
	
	public static MobileDTO standardGetByMobile()
	{
		return new MobileDTO(CUST_MOBILE);
	}
	
	
	public static UpdateEmailMobileVerificationStatus standardUpdateEmailMobileVerificationStatus()
	{
		return new UpdateEmailMobileVerificationStatus(CUST_ID, true, new Date(), CUST_UPDATED_BY,CUST_ID);
	}
	
	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID,CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME);
	}
	
	
	public static String standardJsonUpdateEmailMobileVerificationStatus()
	{
		System.out.println(gson.toJson(standardUpdateEmailMobileVerificationStatus()));
		
		return gson.toJson(standardUpdateEmailMobileVerificationStatus());
	}
	
	
	public static String standardJsonCustomerId(Long customerId)
	{
		return "{\"customerId\":"+customerId+"}";
	}
	
	public static String standardJsonEmailDTO(String email)
	{
		return "{\"email\":\""+email+"\"}";
	}
	
	public static String standardJsonMobileDTO(Long mobile)
	{
		return "{\"mobile\":"+mobile+"}";
	}
	
	
	public static String standardJsonUpdateEmailHashDTO(UpdateEmailHashDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
		
	}
	
	public static String standardJsonUpdatePasswordDTO(UpdatePasswordEmailPasswordAndPasswordTypeDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
		
	}
	
}

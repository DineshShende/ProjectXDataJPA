package com.projectx.data.fixtures;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.domain.CustomerQuickRegisterKey;

public class CustomerQuickRegisterDataFixture {

	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	public static Integer CUST_MOBILEPIN=4125;
	public static Long CUST_EMAILHASH=4141414141L;

	public static CustomerQuickRegisterKey standardEmailMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, CUST_MOBILE);
	}
	
	public static CustomerQuickRegisterKey standardEmailCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, 0L);
	}
	
	
	public static CustomerQuickRegisterKey standardMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey("", CUST_MOBILE);
	}
	
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME, CUST_LASTNAME, standardEmailMobileCustomerKey(), CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME, CUST_LASTNAME, standardMobileCustomerKey(), CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_FIRSTNAME, CUST_LASTNAME, standardEmailCustomerKey(), CUST_PIN,CUST_STATUS_EMAIL, CUST_MOBILEPIN, CUST_EMAILHASH);
		
	}
	
}
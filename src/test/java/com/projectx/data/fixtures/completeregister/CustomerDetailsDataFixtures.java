package com.projectx.data.fixtures.completeregister;


import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.data.fixtures.completeregister.AddressDataFixture.*;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.UpdateAddressDTO;
import com.projectx.rest.domain.completeregister.UpdateVerificationStatusDTO;



public class CustomerDetailsDataFixtures {
	
	
	public static Date CUST_DOB =new Date();
	
	public static String CUST_LANG="Marathi";
	public static String CUST_BUSINESS_DOMAIN="TRANSPORT";
	
	public static String CUST_NAME_OF_FIRM="ABC TRANSPORT";
	public static Long CUST_SEC_MOBILE=8598058044L;
	public static String CUST_SEC_EMAIL="dineshshende@hotmail.com";
	
	public static Date CUST_DATE=new Date();
	
	static Gson gson=new Gson();
	
	
	/*
	public static CustomerDetails standardCustomerDetails()
	{
		return new CustomerDetails(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_DOB, standardAddress(), CUST_MOBILE, CUST_ISMOBILE_VERIFIED,
				CUST_EMAIL, CUST_ISEMAIL_VERIFIED, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),CUST_SEC_MOBILE , 
				CUST_ISMOBILE_VERIFIED, 
				CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
*/
	
	public static CustomerDetails standardCustomerDetailsCopiedFromQuickRegisterEntity()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsFirstPart()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetails()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static UpdateAddressDTO standardUpdateAddressDTO()
	{
		return new UpdateAddressDTO(CUST_ID, new Address(CUST_TYPE_CUSTOMER, "Updted", "Updted", "Updted", "Updted", 234567,
				new Date(), new Date(), "CUST_ONLINE"));
	}
	
	public static UpdateVerificationStatusDTO standardMobileVerificationStatusDTO()
	{
		return new UpdateVerificationStatusDTO(CUST_ID, true);
	}
	
	
	public static String standardJsonCustomerDetails(CustomerDetails customerDetails)
	{
		
		/*
		JsonObject jsonArray=Json.createObjectBuilder().add("firstName", customerDetails.getFirstName())
							.add("lastName", customerDetails.getLastName())
							.add("dateOfBirth", new Date().getTime())
							.add("homeAddress",Json.createObjectBuilder().add("customerType", standardAddress().getCustomerType())
									.add("addressLine",customerDetails.getHomeAddressId().getAddressLine())
									.add("city",customerDetails.getHomeAddressId().getCity())
									.add("district",customerDetails.getHomeAddressId().getDistrict())
									.add("state",customerDetails.getHomeAddressId().getState())
									.add("pincode",customerDetails.getHomeAddressId().getPincode())
									.add("insertTime",customerDetails.getHomeAddressId().getInsertTime().getTime())
									.add("updateTime",customerDetails.getHomeAddressId().getUpdateTime().getTime())
									.add("updatedBy",customerDetails.getHomeAddressId().getUpdatedBy())
									.build().toString())
							.add("mobile", customerDetails.getMobile())
							.add("isMobileVerified", customerDetails.getIsMobileVerified())
							.add("email", customerDetails.getEmail())
							.add("isEmailVerified", customerDetails.getIsEmailVerified())
							.add("language", customerDetails.getLanguage())
							.add("businessDomain",customerDetails.getBusinessDomain())
							.add("nameOfFirm", customerDetails.getNameOfFirm())
							.add("homeAddress",null)
							.add()
							
				.build();
		
		JsonObject jsonObject=new JsonObject();
		
		System.out.println(jsonArray.toString());
		*/
		
		
		System.out.println(gson.toJson(customerDetails));
		
		return gson.toJson(customerDetails);
	}
	
	public static String standardJsonUpdateAddress()
	{
		System.out.println(gson.toJson(standardUpdateAddressDTO()));
		
		return gson.toJson(standardUpdateAddressDTO());
	}
	
	public static String standardJsonUpdateVerificationStatus()
	{
		System.out.println(gson.toJson(standardMobileVerificationStatusDTO()));
		
		return gson.toJson(standardMobileVerificationStatusDTO());
	}
	
}
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
import com.projectx.rest.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileType;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;



public class CustomerDetailsDataFixtures {
	
	
	public  static final Long CUST_MOBILE_NEW = 9766460156L;
	public  static final Long CUST_MOBILE_SEC_NEW = 9766444444L;
	
	
	public static String CUST_MIDDLENAME="A.";

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
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),null,
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(),null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsCopiedFromQuickRegisterEntityNew()
	{
		return new CustomerDetails(333L, standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(),null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsCopiedFromQuickRegisterEntityError()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), null, null, null,null, 
				standardEmailMobileCustomer().getIsEmailVerified(),null,
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsCopiedFromQuickRegisterEntityFirstNull()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), null,CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(),null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsFirstPart()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(),null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetails()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(),null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsError()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), null, null,
				standardEmailMobileCustomer().getIsEmailVerified(),null,
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsWithNewMobile()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),CUST_MIDDLENAME,
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), CUST_MOBILE_NEW,null, 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	
	
	public static UpdateAddressDTO standardUpdateAddressDTO()
	{
		return new UpdateAddressDTO(CUST_ID, new Address(CUST_TYPE_CUSTOMER, "Updted", "Updted", "Updted", "Updted", 234567,
				new Date(), new Date(), "CUST_ONLINE"));
	}
	
	public static UpdateMobileVerificationStatusUpdatedByDTO standardMobileVerificationStatusDTO()
	{
		return new UpdateMobileVerificationStatusUpdatedByDTO(CUST_ID,CUST_MOBILE, true,CUST_UPDATED_BY);
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
	
	
	public static CustomerIdTypeEmailTypeDTO standardCustomerIdTypeEmailTypeDTO()
	{
		return new CustomerIdTypeEmailTypeDTO(CUST_ID, CUST_TYPE_CUSTOMER, 1);
	}
	
	public static CustomerIdTypeEmailTypeUpdatedByDTO standardCustomerIdTypeEmailTypeUpdatedByDTO()
	{
		return new CustomerIdTypeEmailTypeUpdatedByDTO(CUST_ID, CUST_TYPE_CUSTOMER, 1,CUST_UPDATED_BY);
	}

	public static String standardJsonCustomerIdTypeEmailTypeDTO()
	{
		return gson.toJson(standardCustomerIdTypeEmailTypeDTO());
	}
	
	public static String standardJsonCustomerIdTypeEmailTypeUpdatedByDTO()
	{
		return gson.toJson(standardCustomerIdTypeEmailTypeUpdatedByDTO());
	}
	
	public static CustomerIdTypeMobileType standardCustomerIdTypeMobileTypeDTO()
	{
		return new CustomerIdTypeMobileType(CUST_ID, CUST_TYPE_CUSTOMER, 1);
	}
	
	public static CustomerIdTypeMobileTypeUpdatedByDTO standardCustomerIdTypeMobileTypeUpdatedDTO()
	{
		return new CustomerIdTypeMobileTypeUpdatedByDTO(CUST_ID, CUST_TYPE_CUSTOMER, 1,CUST_UPDATED_BY);
	}

	
	
	public static String standardJsonCustomerIdTypeMobileTypeDTO()
	{
		return gson.toJson(standardCustomerIdTypeMobileTypeDTO());
	}
	
	public static String standardJsonCustomerIdTypeMobileUpdatedByTypeDTO()
	{
		return gson.toJson(standardCustomerIdTypeMobileTypeUpdatedDTO());
	}
	
	public static String standardJsonUpdateVerificationStatus()
	{
		
		
		return gson.toJson(standardMobileVerificationStatusDTO());
	}
	
	public static UpdateEmailVerificationStatusUpdatedByDTO standardUpdateEmailVerificationStatusDTO()
	{
		return new UpdateEmailVerificationStatusUpdatedByDTO(CUST_ID, CUST_EMAIL, true,CUST_UPDATED_BY);
	}
	
	public static String standardJsonEmailUpdateVerificationStatus()
	{
		System.out.println(gson.toJson(standardUpdateEmailVerificationStatusDTO()));
		
		return gson.toJson(standardUpdateEmailVerificationStatusDTO());
	}
	
}

package com.projectx.data.fixtures.completeregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.Address;
import static com.projectx.data.fixtures.quickregister.QuickRegisterDataFixture.*;


public class AddressDataFixture {

	public static Long ADDRESS_ID=42L;

	public static Integer ADDRESS_CUST_TYPE=1;
	
	public static String ADDRESS_LINE="AT-GHADGE WASTI PO-SAWAL";
	
	public static String ADDRESS_CITY="Baramati";
	
	public static String ADDRESS_DIST="Pune";
	
	public static String ADDRESS_STATE="Maharashtra";
	
	public static Integer ADDRESS_PINCODE=413133;
	
	public static Integer ADDRESS_PINCODE_INVALID=4131337;
	
	public static Date ADDRESS_DATE=new Date();
	
	
	
	private static Gson gson=new Gson();
	
	public static Address standardAddress()
	{
		return new Address(ADDRESS_CUST_TYPE, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, ADDRESS_STATE, ADDRESS_PINCODE, ADDRESS_DATE, ADDRESS_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressInvalid()
	{
		return new Address(ADDRESS_CUST_TYPE, ADDRESS_LINE, ADDRESS_CITY, ADDRESS_DIST, null, 1111111, ADDRESS_DATE, ADDRESS_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static Address standardAddressUpdated()
	{
		return new Address(ADDRESS_CUST_TYPE, "Updated", "Updated", "Updated", "Updated", 111111, ADDRESS_DATE, ADDRESS_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB,ADDRESS_ID,ADDRESS_ID);
	}
	
	public static String standardJsonAddressId()
	{
		
		return "{\"addressId\":212}";
	}
	
	public static String standardJsonAddress()
	{
		System.out.println(gson.toJson(standardAddress()));
		
		return gson.toJson(standardAddress());
	}
}

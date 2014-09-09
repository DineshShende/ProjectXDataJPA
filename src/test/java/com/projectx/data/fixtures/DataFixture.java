package com.projectx.data.fixtures;

import com.projectx.data.domain.Email;

public class DataFixture {
	
	public static String EMAIL_NAME="dinesh";
	public static String EMAIL_EMAIL="dineshshe@gmail.com";
			
	
	public static Email standardEmail()
	{
		return new Email(EMAIL_NAME,EMAIL_EMAIL);
		
	}

}

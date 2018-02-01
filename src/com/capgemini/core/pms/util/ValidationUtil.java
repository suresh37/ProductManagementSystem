package com.capgemini.core.pms.util;

import java.util.regex.Pattern;

public class ValidationUtil {

	public static boolean isProductNameInvalid(String productName)
	{
		String mypattern = "[a-zA-Z0-9]+";
		
		Boolean isNameValid =  Pattern.matches(mypattern, productName);
		
		return !isNameValid;
		
		
	}
	
	public static boolean isEmailInValid(String productName)
	{
		String mypattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 
		
		//^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
		
		Boolean isEmailValid =  Pattern.matches(mypattern, productName);
		
		return !isEmailValid;
		
		
	}
	public static boolean isDateInValid(String date)
	{
		String mypattern  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$"; 
				// ^[789]\d{9}$
		Boolean isDateValid =  Pattern.matches(mypattern, date);
		
		
		return !isDateValid;
	}
	public static boolean isMobileInvalid(long mobile)
	{
		String mypattern  = "^[789][0-9]{9}"; 
				// ^[789]\d{9}$
		Boolean isMobileValid =  Pattern.matches(mypattern, String.valueOf(mobile));
		
		
		return !isMobileValid;
	}
	public static void main(String[] args)
	{
		String email = "telltosureysh@gmail.com";
		
		System.out.println(isEmailInValid(email));
		
       long mobile= 7845382720L;
		
		System.out.println(isMobileInvalid(mobile));
		
     String date = "20-10-2017";
		
		System.out.println(isDateInValid(date));
	}

}

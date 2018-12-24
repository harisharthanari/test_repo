package com.auto.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which has the methods related to the String related operations
 * @author 
 */
public class StringUtil {
	
	 static final Random random = new Random();
 	 static char[] buf = null; 	 
     private static char[] symbols;
     private static long start;
	 private static long execTime;
	
	/**
	 * Method is to check the given string value is empty or null.
	 * @param string - Data to be validate whether it has null or empty value
	 * @return boolean value
	 */
	public static boolean isEmpty(String string) {
		//Check the string has null value
		if (string == null) return true;
		//Check the string has empty value
		if (string.trim().isEmpty()) return true;
		return false;		
	}
	
	/**
	 * Method is to check the given string value is NA.
	 * @param string - Data to be validate whether it has NA value
	 * @return boolean value
	 */
	public static boolean isNotApplicable(String string) {
		//Check the string has NA value
		if (string.toUpperCase().equalsIgnoreCase("NA")) return true;			
		else return false;
	}
	

	 //public class RandomData
	 //{	    
	     /**
	      * @param length
	      *            the string length
	      * @return an alpha random string
	      */
	     static public String getRandomAlphaString(int length)
	     {
	          RandomAlphaString(length);	          
	          return nextString();
	     }

	    /* static public int randInt(int min, int max)
	     {
	         // Usually this should be a field rather than a method variable so
	         // that it is not re-seeded every call.
	         Random rand = new Random();

	         // nextInt is normally exclusive of the top value,
	         // so add 1 to make it inclusive
	         int randomNum = rand.nextInt((max - min) + 1) + min;

	         return randomNum;
	     }*/
	 	
	 	
	 	

	     static {
	       StringBuilder tmp = new StringBuilder();
	       for (char ch = 'a'; ch <= 'z'; ++ch)
	         tmp.append(ch);
	       symbols = tmp.toString().toCharArray();
	     }   

	 	
	 	  public static void RandomAlphaString(int length) {
	       if (length < 1)
	         throw new IllegalArgumentException("length < 1: " + length);
	        buf = new char[length];
	     }

	     public static String nextString() {
	       for (int idx = 0; idx < buf.length; ++idx) 
	         buf[idx] = symbols[random.nextInt(symbols.length)];
	       return new String(buf);
	     }
	 //}
	     
	     /**
	 	 * Method is to compare two string values
	 	 * @param strValue1 - contains the first value
	 	 * @param strValue2 - contains the second value
	 	 * @return boolean value
	 	 */	
	 	public static boolean compareStrings(String strValue1, String strValue2) {
	 		
	 		// initialize the variable
	 		boolean compareRes;
	 		
	 		if (isEmpty(strValue1) && isEmpty(strValue2)) {
	 			compareRes = true;
	 		} else if (!isEmpty(strValue1) && !isEmpty(strValue2)) {
	 			strValue1 = strValue1.trim();
	 			strValue2 = strValue2.trim();
	 			compareRes = (strValue1.equalsIgnoreCase(strValue2)) ? true : false;
	 		} else {
	 			compareRes = false;
	 		}
	 		
	 		return compareRes;
	 	}
	
	 	public static boolean checkStringContains(String stringValue,String extension) {
	 		boolean isContain = false ;
	 		if (stringValue.contains(extension)) {
	 			isContain = true;
	 		}
	 		return isContain;
	 	}
	 	
	 	
	 	public static void executionTimerStart () {
	 		start = System.currentTimeMillis();
	 	}
	 	
	 	public static long executionTimerStop () {
	 		execTime = System.currentTimeMillis() - start;
	 		return execTime;
	 	}
	 	
	 	
	 	/*public static String getRandomAlphaNumericString(int length) {
	 	    String generatedString = RandomStringUtils.randomAlphanumeric(length);
			return generatedString;
	 	}*/
	 	
	 	public static String getRandomAlphaNumericString(int length) {
			// Get a n-digit multiplier of 10
			int maxDigit = (int) Math.pow(10, length - 2);
			Random random = new Random();
			/*
			 * Get a random character by getting a number from 0 t0 26 and then adding an
			 * 'A' to make it a character
			 * 
			 */
			char randomCharacter = (char) (random.nextInt(26) + 'A');
			/*
			 * Add 1*maxDigit to ensure that the number is equals to or greater than minimum
			 * value nextInt() method will return the number between 0 and 9*maxDigit
			 */
			int randomNumber = 1 * maxDigit + random.nextInt(9 * maxDigit);
			return String.valueOf(randomCharacter) + randomNumber;
		}
	 	
	 	
	 	public static String getRandomNumericValue(int length) {
			// Get a n-digit multiplier of 5
			int maxDigit = (int) Math.pow(10, length);
			Random random = new Random();
			/*
			 * Add 1*maxDigit to ensure that the number is equals to or greater than minimum
			 * value nextInt() method will return the number between 0 and 9*maxDigit
			 */
			
			return String.valueOf((long)1 * maxDigit + random.nextInt(9 * maxDigit));
			/*int randomNumber = 1 * maxDigit + random.nextInt(9 * maxDigit);
			return randomNumber;*/
			
		}
	 	
	 	public static boolean isMatchStringPattern(String totPageValue,String pattern) {
			
			Pattern usrNamePtrn = Pattern.compile(pattern);
			Matcher mtch = usrNamePtrn.matcher(totPageValue);
	        if(mtch.matches()){
	            return true;
	        }
	        return false;
		}
}

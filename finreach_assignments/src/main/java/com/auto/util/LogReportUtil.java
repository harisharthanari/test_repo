package com.auto.util;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

	/**
	 * Class which has the test error handling methods related to Assertion
	 * @author 
	 */
	public class LogReportUtil {
		
		private SoftAssert softAssert;
		
		/**
		 * Initializing the softAssert as an constructor to be used for re-initialization every time before a @test is being executed
		 */
		public LogReportUtil() {
			softAssert = new SoftAssert();
		}
		
		/**
		 * Method is to reporting the test results
		 * @param resStatus - contains the result status
		 * @param resMessage - contains the result message
		 * @param onFailure - contains the result status  
		 */	
		public void reportTestResults(boolean resStatus, String resMessage, String onFailure) {
		
			// Check the resMessage has NA, null or empty values
			if (StringUtil.isEmpty(resMessage) || StringUtil.isNotApplicable(resMessage)) {			
				resMessage = "Result message should not be null or NA.";				
			}	
			// Check the onFailure value is NA, null or empty values, if yes then assign the default value as 'PROCEED' or 'STOP'
			if (StringUtil.isEmpty(onFailure) || StringUtil.isNotApplicable(onFailure)) {			
				onFailure = "PROCEED";				
			}	
			//checking the assertion
			softAssert.assertTrue(resStatus, resMessage);
			//Check the OnFailure value and print the assert messages
			if ((onFailure.equalsIgnoreCase("STOP")) && (resStatus == false)) {
				reportMessage(resMessage);
				reportErrorResults();
			} else if ((onFailure.equalsIgnoreCase("STOP")) && (resStatus == true)) {
				reportMessage(resMessage);
				reportErrorResults(); 
			} else if  ((onFailure.equalsIgnoreCase("PROCEED")) && (resStatus == true)) {
				reportMessage(resMessage);
			} else if ((onFailure.equalsIgnoreCase("PROCEED")) && (resStatus == false)){
				reportMessage(resMessage);
			}
		}
		
		/**
		 * Method is to reporting the error test results
		 */	
		public void reportErrorResults() {		
			//print all the failure asserts
			softAssert.assertAll();		
		}
		
		/**
		 * Method is to reporting the message in the Reporter.Log
		 */	
		public void reportMessage(String message) {		
			//print the message in the Reporter.Log asserts
			Reporter.log(message);	
		}
	
}

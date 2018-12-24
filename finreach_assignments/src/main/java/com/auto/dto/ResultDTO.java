package com.auto.dto;

import java.io.Serializable;
import com.auto.util.StringUtil;

/**
 * Class which has the getter and setter methods for reporting the result message.
 */
public class ResultDTO implements Serializable {
	
		// Declaring the Constant serialized variable and class variables
		private static final long serialVersionUID = -8443101044205605604L;
		private boolean resultStatus;
		private String resultMessage = "No message to show the results.";
		
		/**
		 * Method is to get the Result status
		 * @return resultStatus - boolean value
		 */
		public boolean getResultStatus() {
			return resultStatus;
		}
		
		/**
		 * Method is to set the Result status to the class variable
		 * @param resultStatus - boolean value (true/false)
		 */	
		public void setResultStatus(boolean resultStatus) {		
			this.resultStatus = resultStatus;
		}
		
		/**
		 * Method is to get the Result message
		 * @return resultMessage - String value
		 */
		public String getResultMessage() {
			return resultMessage;
		}
		
		/**
		 * Method is to set the Result message
		 * @param resultMessage - Data used to report
		 */
		public void setResultMessage(String resultMessage) {
			// Check the resultMessage has null or empty values
			if (!StringUtil.isEmpty(resultMessage)) {
				this.resultMessage = resultMessage;				 
			}
		}
}

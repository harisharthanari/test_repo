package com.auto.util;

public class FrameworkException extends Exception {
	
	// Declaring the Constant serialized variable	
	private static final long serialVersionUID = -1790825494148548094L;

		
    /**
     * Method is to set the custom message to the Exception class variable
     * @param message - Customized reporting message
     */
    public FrameworkException(String message) {		
		super(message);				
	}
	
	/**
	 * Method is to set the custom message and the cause of the exception to the Exception class variable
	 * @param message - Customized reporting message
	 * @param cause
	 */
	public FrameworkException(String message, Throwable cause) {		
		super(message, cause);				
	}
}

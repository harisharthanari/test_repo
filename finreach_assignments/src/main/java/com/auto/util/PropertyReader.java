package com.auto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.auto.generic.FrameworkConstants;

/*
 * PropertyReader Class
 */
public class PropertyReader {
	
	//private static Logger logger = Logger.getLogger(PropertyReader.class);
	public static Properties envproperties;
	public static Properties msgproperties;
	public static String environment = null;
	public static String user = null;
	public static String browserName = null;
	public static String testName = null;
	private static LogReportUtil logReport; 
	
	/*
	 * initialize and load env and message property files under src/test/resources directory
	 */
	static {
		try {
			logReport = new LogReportUtil();
			if(envproperties == null) {	
			envproperties = new Properties();
			InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream("envConfig.properties");
			envproperties.load(in);
			}
		} catch (IOException e) {
			logReport.reportTestResults(false, "Error while reading env property file "+e.getMessage(), FrameworkConstants.ONFAIL_STOP);
			System.out.println("Error while reading env property file "+e.getMessage());
		}
	}
	
	/*
	 *  return different Environment based on user input like test, st, prod, dev and etc. 
	 *  First Priority is, external system property like through jenkins or some other way,
	 *  if no external system property, then it will consider the env.properties file
	 */
	public static String getEnvironment() throws FrameworkException {
		if(environment == null) {
			if(System.getProperty("environment") == null)
				environment = envproperties.getProperty("env");
			else {
				envproperties.setProperty("env", System.getProperty("environment"));
				environment = envproperties.getProperty("env");
			}
		}
		return environment;	
	}
	
	
	/**
	 * @return
	 */
	public static String getBrowser() throws FrameworkException {
		if(browserName == null) {
			if(System.getProperty("browserName") == null)
				browserName = envproperties.getProperty("browserName");
			else {
				envproperties.setProperty("browserName", System.getProperty("browserName"));
				browserName = envproperties.getProperty("browserName");
			}
		}
		return browserName;	
	}
	
	/**
	 * @return
	 */
	public static String getTestName() throws FrameworkException {
		if(testName == null) {
			if(System.getProperty("browserName") == null)
				testName = envproperties.getProperty("testName");
			else {
				envproperties.setProperty("testName", System.getProperty("testName"));
				testName = envproperties.getProperty("testName");
			}
		}
		return testName;	
	}
	
	
	/**
	 * @return
	 */
	public static String getUser() throws FrameworkException {
		user = System.getProperty("user");
		if(user == null)
			return envproperties.getProperty("User");
		else
			return user;
	}
	
	
	
	/**
	 * @return
	 */
	public static String getPassowrd() throws FrameworkException {
		
			return envproperties.getProperty(environment+".password");
		
	}
	
	
	
	/**
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return envproperties.getProperty(key);
	}
	
}

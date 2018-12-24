package com.auto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/*
 * PropertyReader Class
 */
public class PropertyReaderUtil {
	
	private static Logger logger = Logger.getLogger(PropertyReaderUtil.class);
	public static Properties envproperties;
	public static Properties msgproperties;
	
	/*
	 * initialize and load env and message property files under src/test/resources directory
	 */
	static {
		if(envproperties == null) {
			envproperties = new Properties();
			InputStream in = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("env.properties");
			try {
				envproperties.load(in);
			} catch (IOException e) {
				logger.error("Error while reading env property file "+e.getMessage());
				e.printStackTrace();
			}
		}
		if(msgproperties == null) {
			msgproperties = new Properties();
			InputStream in = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("error_messages.properties");
			try {
				msgproperties.load(in);
			} catch (IOException e) {
				logger.error("Error while reading error_messages property file "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/*
	 *  return different Environment based on user input like test, st, prod, dev and etc. 
	 *  First Priority is, external system property like through jenkins or some other way,
	 *  if no external system property, then it will consider the env.properties file
	 */
	public static String getEnvironment() {
		String env = null;
		if(env == null) {
			if(System.getProperty("env") == null)
				env = envproperties.getProperty("env");
			else {
				envproperties.setProperty("env", System.getProperty("env"));
				env = envproperties.getProperty("env");
			}
		}
		return env;	
	}
	
	/*
	 * return message properties values based on key
	 */
	public static String get(String key) {
		return msgproperties.getProperty(key);
	}
	
	
}

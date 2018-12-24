package com.auto.util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/*
 * DriverUtil Class cotains utility method related to selenium web driver
 */
public class DriverUtil {
	
	public WebDriver webDriver;	
	public webDriverCapsulation wc;
	public String downloadFilepath;
	
	public DriverUtil() throws FrameworkException {
		wc = new  webDriverCapsulation();
		loadDriver(PropertyReader.getBrowser());
	}
	
	/*
	 * launch browser based on user input
	 */
	public void loadDriver(String browser) throws FrameworkException {
		switch (browser) {
		
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			break;
		case "IE" :
			break;
		case "chrome" :
			 System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
			 webDriver = new ChromeDriver();
			break;
		default :
			webDriver = null;		
		}
		wc.setWebDriver(webDriver);
		deleteAllCookies();
		implicitlyWait();
	}
	
	private void deleteAllCookies() {
		
		wc.getWebDriver().manage().deleteAllCookies();
		
	}
	
	private void implicitlyWait() {
		
		wc.getWebDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	public void refreshWebPage() {
		wc.getWebDriver().navigate().refresh();
	}
	
	
	/**
	 * Method is to get the WebDriver object
	 * @return Webdriver object
	 */
	public WebDriver getWebDriver() {
		return wc.getWebDriver();		
	}
	
	
	/**
	 * @author Inner class to set and get the variables - DTO 
	 *
	 */
	public static class webDriverCapsulation {
		public WebDriver innerWebDriver;
		
		public void setWebDriver(WebDriver driver) {
			this.innerWebDriver = driver;
		}
		
		public WebDriver getWebDriver() {			
			return this.innerWebDriver;
		}
		
	}
}

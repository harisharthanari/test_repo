package com.auto.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * DriverUtil Class cotains utility method related to selenium web driver
 */
public class DriverUtil {
	
	public static WebDriver webDriver;
	
	public DriverUtil(String browser){
		loadDriver(browser);
	}
	/*
	 * launch browser based on user input
	 */
	public void loadDriver(String browser) {
		switch (browser) {
		
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
		case "IE" :
			
		case "chrome" :
			System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
		default :
					
		}
		
		/*if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			webDriver = new FirefoxDriver(capabilities);
		} else if("chrome".equals(browser)) {
			System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
		}*/
		
		deleteAllCookies();
		implicitlyWait();
		
		//webDriver.manage().deleteAllCookies();
		
		
		//webDriver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
		
		
		//return webDriver;
	}
	
	private static void deleteAllCookies(){
		
		webDriver.manage().deleteAllCookies();
		
	}
	
	private static void setScriptTimeout(){
		
		webDriver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
		
	}
	
	private static void pageLoadTimeout(){
		
		webDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
	}
	
	private static void implicitlyWait(){
		
		webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	/**
	 * Method is to get the WebDriver object
	 * @return Webdriver object
	 */
	public WebDriver getWebDriver() {		
		return webDriver;		
	}
	
	
}

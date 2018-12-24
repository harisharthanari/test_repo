package com.auto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.auto.dto.ResultDTO;
import com.auto.generic.FrameworkConstants;
import com.auto.generic.FrameworkLocators;

/*
 * CommonAction Util class contain all selenium related methods 
 */

public abstract class CommonActionUtil {

	private final static Logger logger = Logger.getLogger(CommonActionUtil.class);
	private WebDriver webDriver;
	public WebDriverWait wait, buttonWait;
	private LogReportUtil logReport = new LogReportUtil();
	private ResultDTO resultDTO = new ResultDTO();
	private static final String JQUERY_ACTIVE_CONNECTIONS_QUERY = "return $.active == 0;";
	private static final int DEFAULT_SLEEP_TIME_IN_SECONDS = 2;
	private static final int DEFAULT_TIMEOUT_IN_SECONDS = 10;

	/*
	 * initialize the webdriver and webdriverwait
	 */

	/**
	 * Empty constructor
	 * @author Harish Arthanari
	 */
	public CommonActionUtil() {}

	/**
	 * Constructor to initialize and assign webdriver
	 * @param webDriver
	 * @author Harish Arthanari
	 */
	public CommonActionUtil(WebDriver webDriver) {
		this.webDriver = webDriver;
		wait = new WebDriverWait(webDriver, 2000);
		buttonWait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT_IN_SECONDS);
	}

	
	public void pageLoadTime() {
		webDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	}

	/**
	 * This method is to initialize the browser
	 * @return - initialize the browser
	 * @author Harish Arthanari
	 */
	public ResultDTO initBrowser() throws FrameworkException {
		resultDTO = openHomePage();
		return resultDTO;

	}

	/**
	 * This method is to launch the browser based on the webdriver initialized
	 * @return
	 * @throws FrameworkException
	 * @author Harish Arthanari
	 */
	private ResultDTO openHomePage() throws FrameworkException {
		String env = PropertyReader.getEnvironment();
		if (env == null ) {
			env = "test";
		}
			
		logReport.reportMessage("!!!!! Automation Test is about to start on " + env + " Environment !!!!!");
		System.out.println("!!!!! Automation Test is about to start on " + env + " Environment !!!!!");
		String url = null;

		switch (env) {
		case "prod":
			url = "";
			break;
		case "test":
			url = "https://cafetownsend-angular-rails.herokuapp.com/login";
			break;
		case "stage":
			url = "";
			break;
		default:
			throw new FrameworkException("unable to find the matching URL from the property file for the env -" + env);
		}

		System.out.println("The URL -" + url);
		webDriver.manage().window().maximize();
		webDriver.get(url);

		resultDTO.setResultStatus(true);
		resultDTO.setResultMessage("The URL -" + url + "- has been launched successfully ");
		return resultDTO;

	}

	/**
	 * This method is to close the webdriver instance
	 * @return - close the current instance of the browser
	 * @author Harish Arthanari
	 */
	public ResultDTO closeWebDriver() {
		webDriver.close();
		resultDTO.setResultStatus(true);
		resultDTO.setResultMessage("Closed the current browser instance successfully");
		return resultDTO;
	}

	/**
	 * This method is to quit the current instance of the webdriver
	 * @return - close and quit the browser
	 * @author Harish Arthanari
	 */
	public ResultDTO quitWebDriver() {

		webDriver.quit();
		logger.info("Webdriver is quitted successfully");
		resultDTO.setResultStatus(true);
		resultDTO.setResultMessage("Webdriver is quitted successfully.");
		return resultDTO;
	}

	/**
	 * Method is to either accept / reject the windows alert displayed
	 * @param alertAction  Object name used for reporting
	 * @return resultDTO - Locator Type method
	 * @throws FrameworkException - Stores the exception messages
	 * @author Harish Arthanari
	 */
	protected ResultDTO switchToAlert(String alertAction, String existence) {
		boolean status = true;
		String message = "";
		// ResultDTO resultDTO = new ResultDTO();
		Alert alert = null;
		boolean alertDisplayed = isAlertDisplayed();
		// wait.until(ExpectedConditions.alertIsPresent());
		if ((alertDisplayed == true && existence.equalsIgnoreCase("exists"))) {
			alertAction(alertAction, alert);
			message = "Alert is needed and it is displayed";
		} else if ((alertDisplayed == false && existence.equalsIgnoreCase("notexists"))) {
			message = "Alert is not needed and it is not displayed";
		} else if ((alertDisplayed == true && existence.equalsIgnoreCase("notexists"))) {
			status = false;
			message = "Alert is not needed and it is displayed";
		}
		resultDTO.setResultStatus(status);
		resultDTO.setResultMessage(message);
		return resultDTO;
	}

	/**
	 * Method is to click the button based on the actions(ACCEPT-OK,YES/REJECT-NO,CANCEL)
	 * @param alertAction - action to be perform on the Window dialog
	 * @param alert - Alert object
	 * @throws FrameworkException - Stores the exception messages
	 * @author Harish Arthanari
	 */
	private void alertAction(String alertAction, Alert alert) {
		try {
			alert = webDriver.switchTo().alert();
			switch (alertAction.toUpperCase()) {
			case "ACCEPT":
				alert.accept();
				break;
			case "REJECT":
				alert.dismiss();
				break;
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Method is to either find the existence of the windows alert displayed
	 * @return foundAlert - boolean value
	 * @author Harish Arthanari
	 */
	protected boolean isAlertDisplayed() {
		boolean isDisplayed = false;
		try {
			// If Alert found return true otherwise false
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				isDisplayed = false;
			} else {
				isDisplayed = true;
			}
		} catch (TimeoutException toe) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	
	/**
	 * This method is to initialize the find method in webdriver API find the webelement by different locators like by 
	 * id, name, xpath,classname, link text and etc, if not found, it will return error message
	 * with description input
	 * @param by
	 * @param desc
	 * @return
	 * @author Harish Arthanari
	 */
	public WebElement find(By by, String desc) {
		WebElement webElement = null;
		try {
			waitTillPresent(by);
			webElement = webDriver.findElement(by);
		} catch (Exception e) {
			throw new RuntimeException("Unable to locate element " + desc);
		}
		return webElement;

	}

	/*
	 * find the webelement by different locators like by id, name, xpath,
	 * classname, link text and etc
	 */
	public WebElement find(By by) {
		return webDriver.findElement(by);
	}

	/*
	 * find all the webelements by different locators like by id, name, xpath,
	 * classname, link text and etc, if not found, it will return error message
	 * with description input
	 */
	public List<WebElement> findAll(By by, String desc) {
		List<WebElement> webElements = null;
		try {
			waitTillPresent(by);
			webElements = webDriver.findElements(by);
		} catch (Exception e) {
			throw new RuntimeException("Unable to locate element -> " + desc);
		}
		return webElements;
	}

	/*
	 * find all the webelements by different locators like by id, name, xpath,
	 * classname, link text and etc, if not found
	 */
	public List<WebElement> findAll(By by) {
		return webDriver.findElements(by);
	}

	/*
	 * click on the webelement
	 */
	public void click(WebElement element) {
		if (isClickable(element)) {
			element.click();
		} else {
			logger.error("The given Element is not clickable " + element.getTagName());
		}
	}

	/*
	 * click on the webelement
	 */
	/**
	 * This method is to click the webelement
	 * @param argObjName
	 * @param webElement
	 * @return
	 * 
	 */
	public ResultDTO clickElement(String argObjName, WebElement webElement) {
		resultDTO = new ResultDTO();
		try {
			// wait.until(ExpectedConditions.elementToBeClickable(webElement));
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// Click the object
				webElement.click();
				// Set the status and message in the resultDTO object
				resultDTO.setResultStatus(true);
				resultDTO.setResultMessage("Object " + argObjName + " is clicked.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage("Object " + argObjName + " is not clicked. Reason:" + e.getMessage());
		}
		return resultDTO;
	}

	/**
	 * This method is to type input text on the webelement
	 * @param element
	 * @param text
	 * @author Pardip Nayyar
	 */
	private void configureValue(WebElement element, String text) {
		if (element.isDisplayed() || element.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		} else
			logger.error("The given Element is not Displayed or Enabled " + element.getTagName());
	}

	/*
	 * select the input value in the drop down webelement
	 */
	public void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	/**
	 * Method is to select the given option value from the WebElement(dropdown/combobox) object
	 * @param argObjName - Object name used for reporting
	 * @param argSelectVal - Data used to perform the desired action
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO selectElementDropdownOption(String argObjName, String argSelectVal, WebElement webElement) {
		boolean valFound = false;
		String tempVal;
		try {
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				List<WebElement> optionList = select.getOptions();
				// loop to find the given value and select it
				for (WebElement webElmtOption : optionList) {
					// verify the expected and actual option value
					if (webElementGetText(webElmtOption,argObjName).equals(argSelectVal)) {
					//if (webElmtOption.getText().equals(argSelectVal)) {
						// set the boolean as true once the value found
						valFound = true; 
						// select the value by VisibleText property
						select.selectByVisibleText(argSelectVal);
						// For reporting purpose
						if (StringUtil.isEmpty(argSelectVal)) {
							tempVal = "empty";
						} else {
							tempVal = argSelectVal;
						}
						waitForAjax();
						// verify the selected value is displayed in the dropdown and report
						if (webElementGetText(select.getFirstSelectedOption(),argObjName).equals(argSelectVal)) {
						//if (select.getFirstSelectedOption().getText().equals(argSelectVal)) {
							resultDTO.setResultStatus(true);
							resultDTO.setResultMessage(
									"Object " + argObjName + " is selected with " + tempVal + " value.");
						} else {
							resultDTO.setResultStatus(false);
							resultDTO.setResultMessage(
									"Object " + argObjName + " is not selected with " + tempVal + " value.");
						}
					}
				}
				// report the value is not found
				if (!valFound) {
					resultDTO.setResultStatus(false);
					resultDTO.setResultMessage(
							"Object " + argObjName + " doesn't have the " + argSelectVal + " value for selection.");
				}
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	
	
	
	
	
	/**
	 * Method is to validate the given option value from the WebElement(dropdown/combobox) object
	 * @param argObjName - Object name used for reporting
	 * @param argSelectVal - Data used to perform the desired action
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO validateElementDropdownOption(String argObjName, List<String> argSelectVal,
			WebElement webElement) {
		StringBuffer cumulativeResultMessage = new StringBuffer();
		String totalTimeTaken;
		boolean isElementFound = false;
		boolean isFail = false;
		try {
			StringUtil.executionTimerStart();
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				List<WebElement> optionList = select.getOptions();
				// loop to find the given expected value and select it
				for (String expValue : argSelectVal) {
					// loop to find the given actual value and select it
					for (WebElement webElmtOption : optionList) {
						if (StringUtil.compareStrings(webElementGetText(webElmtOption,argObjName),expValue)) {
							isElementFound = true;
							break;
						}
					}
					// verify the selected value is displayed in the dropdown and report
					if (isElementFound) {
						cumulativeResultMessage.append("The expected value - " + expValue + " is found inside the - "
								+ argObjName + " list box.");
						cumulativeResultMessage.append("\n");
					} else {
						isFail = true;
						cumulativeResultMessage.append("The expected value - " + expValue
								+ " is not found inside the - " + argObjName + " list box.");
						cumulativeResultMessage.append("\n");
					}
				}
				totalTimeTaken = StringUtil.executionTimerStop() + " - milliseconds";
				// report the value is not found
				if (isFail) {
					resultDTO.setResultStatus(!isFail);
					resultDTO.setResultMessage("List box values are not matching/mismatching - "
							+ cumulativeResultMessage.toString() + "Total time taken -" + totalTimeTaken);
				} else {
					resultDTO.setResultStatus(!isFail);
					resultDTO.setResultMessage("List box values are matching - " + cumulativeResultMessage.toString()
							+ "Total time taken -" + totalTimeTaken);
				}
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	
	/*-------------------------------------------------------------*/
	public ResultDTO selectEmployeeList(String argObjName, String argSelectVal,
			WebElement webElement) {
		boolean valFound = false;
		resultDTO = verifyElementEnable(argObjName, webElement);
		if (resultDTO.getResultStatus()) {
			List<WebElement> listValues = findAll(FrameworkLocators.CREATE_EMPLOYEE_LISTVALUE, "Created employee List");
			// loop to find the given value and select it
			for (WebElement webElmtOption : listValues) {
				// verify the expected and actual option value
				if (webElementGetText(webElmtOption,argObjName).contains(argSelectVal)) {
					// set the boolean as true once the value found
					valFound = true; 
					// select the value by VisibleText property
					click(webElmtOption);
					resultDTO.setResultStatus(valFound);
					resultDTO.setResultMessage("Object '" + argObjName + "' is selected with value "+webElmtOption.getText());
					break;
				}
			}
			// report the value is not found
			if (!valFound) {
				resultDTO.setResultStatus(false);
				resultDTO.setResultMessage(
						"Object " + argObjName + " doesn't have the " + argSelectVal + " value for selection.");
			}		
		}
		return resultDTO;
	}
	
	/*-------------------------------------------------------------*/
	
	/**
	 * Method is to select the given option value from the WebElement(dropdown/combobox) object 
	 * @param argObjName - Object name used for reporting
	 * @param argSelectVal - Data used to perform the desired action
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO selectElementDropdownOptionOnly(String argObjName, String argSelectVal, WebElement webElement) {
		boolean valFound = false;
		try {
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				List<WebElement> optionList = select.getOptions();
				// loop to find the given value and select it
				for (WebElement webElmtOption : optionList) {
					// verify the expected and actual option value
					if (webElementGetText(webElmtOption,argObjName).equals(argSelectVal)) {
						// set the boolean as true once the value found
						valFound = true; 
						// select the value by VisibleText property
						select.selectByVisibleText(argSelectVal);
						break;
					}
				}
				// report the value is not found
				if (!valFound) {
					resultDTO.setResultStatus(false);
					resultDTO.setResultMessage(
							"Object " + argObjName + " doesn't have the " + argSelectVal + " value for selection.");
				}
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	/**
	 * Method is to select the given Index value from the WebElement(dropdown/combobox) object  
	 * @param argObjName - Object name used for reporting
	 * @param argSelectIndex - Data used to perform the desired action(Index must start
	 *            either with 0 or 1 based on application)
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO selectElementDropdownByIndexOption(String argObjName, int argSelectIndex,
			WebElement webElement) {
		boolean valFound = false;
		String tempVal;
		try {
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				List<WebElement> optionList = select.getOptions();
				// checking index value is less than dropdown list index value
				if (argSelectIndex <= optionList.size()) {
					// select the value by ByIndex property
					select.selectByIndex(argSelectIndex);
					// Getting text for the First Selected Option by using index
					String selectedText = webElementGetText(select.getFirstSelectedOption(),argObjName);
					// verify the selected Index value is Empty or displayed in
					// the drop down and report
					if (StringUtil.isEmpty(selectedText)) {
						tempVal = "empty";
						resultDTO.setResultStatus(false);
						resultDTO.setResultMessage("Object '" + argObjName + "' is not selected with index '"
								+ argSelectIndex + "' as '" + tempVal + "' value.");
					} else {
						tempVal = selectedText;
						valFound = true; // set the boolean as true once the
											// value found
						resultDTO.setResultStatus(true);
						resultDTO.setResultMessage("Object '" + argObjName + "' is selected with index '"
								+ argSelectIndex + "' as '" + tempVal + "' value.");
					}

					// report the value is not found
					if (!valFound) {
						resultDTO.setResultStatus(false);
						resultDTO.setResultMessage("Object '" + argObjName + "' doesn't have the '" + argSelectIndex
								+ "' value for selection.");
					}
				}
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());

		}
		return resultDTO;
	}

	/**
	 * Method is to get values by Index value from the WebElement(dropdown/combobox) object
	 * @param argObjName
	 * @param argSelectIndex
	 * @param webElement
	 * @return
	 * @author Harish Arthanari
	 */
	protected String getElementDropdownByIndexOption(String argObjName, int argSelectIndex, WebElement webElement) {
		String selectedText = null;
		try {
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				List<WebElement> optionList = select.getOptions();
				// checking index value is less than dropdown list index value
				if (argSelectIndex <= optionList.size()) {
					// select the value by ByIndex property
					select.selectByIndex(argSelectIndex);
					// Getting text for the First Selected Option by using index
					selectedText = webElementGetText(select.getFirstSelectedOption(),argObjName);
				}
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());

		}
		return selectedText;
	}

	/**
	 * Method is to verify the enable state of the given WebElement object
	 * @param argObjName - Object name used for reporting
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO verifyElementEnable(String argObjName, WebElement webElement) {
		resultDTO = new ResultDTO();
		try {
			// Check the enable state of the WebElement object
			if (webElement != null) {
				if (webElement.isEnabled()) {
					resultDTO.setResultStatus(true);
					resultDTO.setResultMessage("Object '" + argObjName + "' is in enabled state.");
				} else {
					resultDTO.setResultStatus(false);
					resultDTO.setResultMessage("Object '" + argObjName + "' is in disabled state.");
				}
			} else {
				resultDTO.setResultStatus(false);
				resultDTO.setResultMessage("Object '" + argObjName + "' is not initialized or not exists.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' enabled state is not verified. Reason: " + e.getMessage());
		}
		return resultDTO;
	}
	
	/**
	 * Method is to verify the disable state of the given WebElement object
	 * @param argObjName - Object name used for reporting
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	protected ResultDTO verifyElementDisabled(String argObjName, WebElement webElement) {
		resultDTO = new ResultDTO();
		try {
			// Check the enable state of the WebElement object
			if (webElement != null) {
				String attributeValue = webElement.getAttribute("data-enabled");
				if (StringUtil.isEmpty(attributeValue)) {
					resultDTO.setResultStatus(false);
					resultDTO.setResultMessage("Object '" + argObjName + "' is in enabled state.");
				} else {
					resultDTO.setResultStatus(true);
					resultDTO.setResultMessage("Object '" + argObjName + "' is in disabled state.");
				} 
			} else {
				resultDTO.setResultStatus(false);
				resultDTO.setResultMessage("Object '" + argObjName + "' is not initialized or not exists.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' enabled state is not verified. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	/**
	 * Method is to verify the existence of the given WebElement object	 * 
	 * @param argObjName - Object name used for reporting
	 * @param webElement - Object used to perform the desired action
	 * @return resultDTO - ResultDTO object used for reporting
	 * @throws FrameworkException - Stores the exception messages
	 * @author Harish Arthanari
	 */
	protected ResultDTO verifyElementExist(String argObjName, WebElement webElement) throws FrameworkException {
		//resultDTO = new ResultDTO();
		try {
			// Check the existence of the WebElement object
			if ((webElement != null)) {
				if (webElement.isDisplayed()) {
					resultDTO.setResultStatus(true);
					resultDTO.setResultMessage("Object " + argObjName + " is exists.");
				} else {
					resultDTO.setResultStatus(false);
					resultDTO.setResultMessage("Object " + argObjName + " is initialized, but not displayed.");
				}
			} else {
				resultDTO.setResultStatus(false);
				resultDTO.setResultMessage("Object " + argObjName + " is not initialized or not exists.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO
					.setResultMessage("Object " + argObjName + " existence is not verified. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	/*
	 * return the current selected value from the drop down webelement
	 */
	public String getSelectedValue(String argObjName,WebElement element) {
		String selectedText = null;
		Select select = new Select(element);
		WebElement webElement = select.getFirstSelectedOption();
		if ((webElement != null)) {
			if (webElement.isDisplayed()) {
				selectedText = webElementGetText(webElement,argObjName);
			} else {
				resultDTO.setResultStatus(false);
				resultDTO.setResultMessage("Object " + argObjName + " is initialized, but not displayed.");
			}
		} else {
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage("Object " + argObjName + " is not initialized or not exists.");
		}
		return selectedText;
	}

	/*
	 * return the text associated with the webelement
	 */
	public String getText(WebElement webElement) {
		if (webElement != null)
			return webElement.getText();
		return null;
	}

	/*
	 * click on the radio webelement by index
	 */
	public void clickRadioByIndex(List<WebElement> webElements, int index) {
		click(webElements.get(index));
	}

	
	/**
	 * This method is to click the radio button 
	 * @param webElements
	 * @param value
	 * @author Harish Arthanari
	 */
	public void clickRadioByValue(List<WebElement> webElements, String value) {
		for (WebElement webElement : webElements) {
			String radioValue = webElement.getAttribute("value");
			System.out.println(radioValue);
			if (value.equalsIgnoreCase(radioValue))
				click(webElement);
			else
				throw new RuntimeException("Unable to find radio box with given value " + value);

		}
	}

	/**
	 * This method is to check whether radio button is selected
	 * @param webElements
	 * @param value
	 * @return
	 * @author Harish Arthanari
	 */
	public boolean isRadioChecked(List<WebElement> webElements, String value) {
		boolean isChecked = false;
		for (WebElement webElement : webElements) {
			String radioValue = webElement.getAttribute("value");
			if (value.equalsIgnoreCase(radioValue))
				isChecked = true;
			else
				isChecked = false;
		}
		return isChecked;
	}

	/**
	 * This method is to implement the therad.sleep functionality of seleniums
	 * @param millis
	 * @author Harish Arthanari
	 */
	public void sleepFor(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is to wait for a certain amount of time explicitly before throwing
	 * an exception for not finding the element by Webdriver
	 * @param time - contains data for setting the time
	 * @author Harish Arthanari
	 */
	protected WebDriverWait explicitWait(long time) {
		return new WebDriverWait(webDriver, time);
	}

	/*
	 * retrun if the webelement is clickable or not
	 */
	public boolean isClickable(WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
			else {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * retrun if the webelement is displayed or not
	 */
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/*
	 * retrun page source in html format
	 */
	public String getSource() {
		return webDriver.getPageSource();
	}

	/*
	 * file upload using autoit
	 */
	public void fileUpload(String path) {
		try {
			Runtime.getRuntime().exec(path);
		} catch (Exception e) {
			logger.error("Error while upload " + e.getMessage());
		}
	}

	/*
	 * wait till the element is present
	 */
	public void waitTillPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/*
	 * wait till the element is clickable
	 */
	public void waitTillClickable(By locator) {

		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method is to refresh the webpage
	 * @author Harish Arthanari
	 */
	protected void refreshWebPage() {
		webDriver.navigate().refresh();
	}

	/**
	 * This method is to fetch the URL of the current browser instance
	 * @return - return the current URL
	 * @author Harish Arthanari
	 */
	protected String getbrowserURL() {
		return webDriver.getCurrentUrl();
	}

	
	/**
	 * @param URL
	 * @author Harish Arthanari
	 */
	public void loadBrowserURL(String URL) {
		webDriver.get(URL);
	}

	/*
	 * return all windows for the current page
	 */
	public List<String> windows() {
		return new ArrayList<String>(webDriver.getWindowHandles());
	}

	/*
	 * switch to given frame
	 */
	public void switchTo(String frame) {
		webDriver.switchTo().window(frame);
	}


	/**
	 * @author Harish Arthanari
	 */
	public void alertAccept() {
		wait.until(ExpectedConditions.alertIsPresent());
		webDriver.switchTo().alert().accept();
	}

	/**
	 * @author Harish Arthanari
	 */
	public void alertDismiss() {
		wait.until(ExpectedConditions.alertIsPresent());
		webDriver.switchTo().alert().dismiss();
	}

	/**
	 * @return
	 */
	public String alertText() {
		wait.until(ExpectedConditions.alertIsPresent());
		return webDriver.switchTo().alert().getText();
	}


	/**
	 * @return - returns the string value of current new window
	 * @author Harish Arthanari
	 */
	public String getWindowsHandle() {
		String newWindowHandle = null;
		String currWindow = getCurrentWindowName();

		Set<String> allWindowHandles = webDriver.getWindowHandles();
		if (allWindowHandles.size() > 1) {
			for (String windows : allWindowHandles) {
				if (!windows.equals(currWindow)) {
					newWindowHandle = windows;
					break;
				}
			}
		}
		return newWindowHandle;
	}

	/**
	 * @return - return the window name
	 * @author Harish Arthanari
	 */
	public String getCurrentWindowName() {
		return webDriver.getWindowHandle();
	}

	/**
	 * @param Window - Object used for switching to the specified window
	 * @author Harish Arthanari
	 */
	public void switchToWindow(String Window) {
		webDriver.switchTo().window(Window);
	}

	/**
	 * This method is to return the text associated with the webelement
	 * @param webElement
	 * @param argObjName
	 * @return - returns the actual text value from UI
	 * @author Harish Arthanari
	 */
	protected String webElementGetText(WebElement webElement, String argObjName) {
		String actualTextValue = null;
		resultDTO = verifyElementEnable(argObjName, webElement);
		if (resultDTO.getResultStatus()) {
			// retrieve the value from the object
			actualTextValue = webElement.getText();
		} 
		return actualTextValue;
	}

	/**
	 * This method is to check the current value using the attribute named value
	 * @param webElement
	 * @param argObjName
	 * @return - return the text value from the UI
	 * @author Harish Arthanari
	 */
	protected String webElementGetTextAttribute(WebElement webElement, String argObjName) {
		String actualTextValue = null;
		try {
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// retrieve the value from the object
				actualTextValue = webElement.getAttribute("value");
				logReport.reportTestResults(true, "Object " + argObjName + " value is retrieved successfully.", "");// resultDTO.setResultStatus(true);
			} else {
				logReport.reportTestResults(false, "Object " + argObjName + " value is not retrieved successfully.",
						"");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			logReport.reportTestResults(false,
					"Object " + argObjName + " is not retrieved successfully. Reason: " + e.getMessage(),
					FrameworkConstants.ONFAIL_STOP);
		}
		return actualTextValue;
	}

	/**
	 * This method is to get the recently selected value from the list box
	 * @param webElement
	 * @param argObjName
	 * @return - return the text associated with the webelement
	 * @author Harish Arthanari
	 */
	protected String webElementGetTextListbox(WebElement webElement, String argObjName) {
		String selectedText = null;
		try {
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// set the web element object as Select and retrieve the option values
				Select select = new Select(webElement);
				selectedText = webElementGetText(select.getFirstSelectedOption(),argObjName);
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object '" + argObjName + "' is not selected with the given input. Reason: " + e.getMessage());
		}
		return selectedText;
	}

	/**
	 * This method is to configure the values into the text box in UI
	 * @param argObjName - Object name used for reporting
	 * @param webElement - Object used to perform the desired action
	 * @param argConfigVal - String value used to configure text field
	 * @return resultDTO - ResultDTO object used for reporting
	 * @author Harish Arthanari
	 */
	public ResultDTO configElementText(String argObjName, WebElement webElement, String argConfigVal) {
		try {
			if (StringUtil.isEmpty(argConfigVal)) {
				throw new FrameworkException("Configuration value should not be null or empty.");
			}
			// Check the existence & enable state of the object
			resultDTO = verifyElementEnable(argObjName, webElement);
			// Configure the value in the object
			if (resultDTO.getResultStatus()) {
				configureValue(webElement, argConfigVal);
				// webElement.sendKeys(argConfigVal);
				// Hide the characters if the object name contains 'Password'
				if (argObjName.toUpperCase().contains("PASSWORD")) {
					argConfigVal = "*******";
				}
				// Set the status and message in the resultDTO object
				resultDTO.setResultStatus(true);
				resultDTO.setResultMessage("Object " + argObjName + " is configured with " + argConfigVal + " value.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object " + argObjName + " is not configured with the given input. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	/**
	 * This method is to select the checkbox webelement
	 * @param argObjName
	 * @param webElement
	 * @return
	 * @author Harish Arthanari
	 */
	protected ResultDTO selectCheckBox(String argObjName, WebElement webElement) {
		try {
			resultDTO = verifyElementEnable(argObjName, webElement);
			if (resultDTO.getResultStatus()) {
				// Configure the value in the object
				click(webElement);
				// Set the status and message in the resultDTO object
				resultDTO.setResultStatus(true);
				resultDTO.setResultMessage("Object " + argObjName + " is configured.");
			}
		} catch (Exception e) {
			// Set the status and message in the resultDTO object
			resultDTO.setResultStatus(false);
			resultDTO.setResultMessage(
					"Object " + argObjName + " is not configured with the given input. Reason: " + e.getMessage());
		}
		return resultDTO;
	}

	/**
	 * This method is to validate whether check box is checked
	 * @param webElement
	 * @param value
	 * @return
	 * @author Harish Arthanari
	 */
	public boolean isCheckBoxChecked(WebElement webElement, boolean value) {
		boolean isChecked = false;
		boolean checkValue = webElement.isSelected();
		if (value == checkValue)
			isChecked = true;
		else
			isChecked = false;
		return isChecked;
	}

	/**
	 * wait for ajax call
	 * @author Harish Arthanari
	 */
	public void waitUntilAjaxRequestCompletes() {
		new FluentWait<WebDriver>(webDriver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						JavascriptExecutor jsExec = (JavascriptExecutor) d;
						return (Boolean) jsExec.executeScript(JQUERY_ACTIVE_CONNECTIONS_QUERY);
					}
				});
	}

	/**
	 * This method is to wait for the AJAX call arised in the webpage wait for ajax call
	 * @author Harish Arthanari
	 */
	protected void waitForAjax() {
		wait.until(waitJQueryResponse(webDriver));
	}

	private static ExpectedCondition<Boolean> waitJQueryResponse(final WebDriver webDriver) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js;
				if (driver instanceof JavascriptExecutor) {
					try {
						driver.switchTo().alert().accept();
					} catch (NoAlertPresentException e) {
					}
					js = (JavascriptExecutor) driver;
					if (((Long) (js.executeScript("return $.active;"))).intValue() > 0) {
						return false;
					} else {
						return true;
					}
				} else {
					throw new UnsupportedOperationException("This driver does not support javascript execution");
				}
			}

		};
	}

	/**
	 * This method is to execute the Auto IT functionality
	 * @param executionPath
	 * @return
	 * @throws FrameworkException
	 * @author Harish Arthanari
	 */
	protected ResultDTO executeAutoIT(String executionPath) throws FrameworkException {
		Boolean isExecuted = false;
		int retCode = 0;
		resultDTO.setResultMessage("Auto IT scripts are not executed successfully");

		try {
			Process proc = Runtime.getRuntime().exec(executionPath);
			// making the script to wait until autoit scripts are executed
			InputStream is = proc.getInputStream();
			while (retCode != -1) {
				retCode = is.read();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new FrameworkException(e.getMessage());
		}

		if (retCode == -1) {
			isExecuted = true;
			resultDTO.setResultMessage("Auto IT scripts are executed successfully");
		}
		resultDTO.setResultStatus(isExecuted);
		return resultDTO;
	}



	/**
	 * This Method is to switch inside a frame displayed using webelement
	 * @param webElement - webelement of the frame to be switched
	 * @throws FrameworkException - Stores the exception messages
	 * @author Harish Arthanari
	 */
	public ResultDTO switchToFrame(WebElement webElement) throws FrameworkException {
		resultDTO.setResultStatus(false);
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(webElement);
			resultDTO.setResultStatus(true);
			resultDTO.setResultMessage("Switched to a new frame -" + webElement);
		} catch (NoSuchFrameException nfe) {
			resultDTO.setResultMessage(
					"Unable to locate frame with ID " + webElement + " and reason is  " + nfe.getCause());
		} 
		return resultDTO;
	}

	/**
	 * This Method is to switch inside a frame displayed using the frame ID
	 * @param frameId - ID of the of the frame to be switched
	 * @throws FrameworkException - Stores the exception messages
	 *s @author Harish Arthanari
	 */
	public ResultDTO switchToFrame(int frameId) throws FrameworkException {
		resultDTO.setResultStatus(false);
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(frameId);
			resultDTO.setResultStatus(true);
			resultDTO.setResultMessage("Switched to a new frame -" + frameId);
		} catch (NoSuchFrameException nfe) {
			resultDTO.setResultMessage(
					"Unable to locate frame with ID " + frameId + " and reason is  " + nfe.getCause());
		}
		return resultDTO;
	}

	/**
	 * This Method is to switch inside a frame displayed using frame name
	 * @param frameName - Name of the of the frame to be switched
	 * @author Harish Arthanari
	 */
	public void switchToFrame(String frameName) throws FrameworkException {
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(frameName);
		} catch (NoSuchFrameException nfe) {
			throw new FrameworkException("Unable to locate frame with name " + frameName, nfe.getCause());
		} catch (Exception e) {
			throw new FrameworkException("Unable to navigate to frame with name " + frameName, e.getCause());
		}
	}

	/**
	 * This Method is to switch back to the default frame in UI 
	 * @throws FrameworkException - Stores the exception messages
	 * @author Harish Arthanari
	 */
	public ResultDTO switchToDefaultContent() throws FrameworkException {
		resultDTO.setResultStatus(false);
		try {

			webDriver.switchTo().defaultContent();

			resultDTO.setResultStatus(true);
			resultDTO.setResultMessage(" Switched to a default frame ");

		} catch (NoSuchFrameException nfe) {
			resultDTO.setResultMessage("Unable to locate default frame " + nfe.getCause());
		} 
		return resultDTO;
	}


	/**
	 * This method is to check for the weblement existence in the the UI until
	   the element appears on the UI
	 * @param frameworkLocators
	 * @throws FrameworkException
	 * @author Harish Arthanari
	 */
	public void waitForElementExistence(By frameworkLocators) throws FrameworkException {
		int Count = 0;
		while (Count < 11) {
			if (wait.until(ExpectedConditions.presenceOfElementLocated(frameworkLocators)) != null) {
				sleepFor(2000);
				break;
			} else {
				sleepFor(5000);
				refreshWebPage();
			}
			Count++;
		}
	}
	
	
	/**
	 * This method is the get the page URL of the current browser instance
	 * @param URL
	 * @return
	 * @author Harish Arthanari
	 */
	public boolean getPageURL(String URL) {
		boolean isValue = false;

		String pageURL = webDriver.getCurrentUrl();
		System.out.println(pageURL);
		if (StringUtil.compareStrings(pageURL, URL)) {
			isValue = true;
		}
		return isValue;
	}

	
	
	
	
}

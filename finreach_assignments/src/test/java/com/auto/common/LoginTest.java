package com.auto.common;

import com.auto.dto.ResultDTO;
import com.auto.generic.FrameworkConstants;
import com.auto.generic.FrameworkLocators;
import com.auto.page.ApplicationActions;
import com.auto.page.WebDriverInit;
import com.auto.util.DriverUtil;
import com.auto.util.FrameworkException;
import com.auto.util.LogReportUtil;
import com.auto.util.PropertyReader;

import org.testng.annotations.Test;


public class LoginTest extends WebDriverInit {
	
	//Declare the Constants
	private final String USERNAME_TEXT = "UserName text field";
	private final String PASSWORD_TEXT = "Password text field";
	private final String LOGIN_BUTTON = "Log In Button";
	private ApplicationActions actionPage;
	private LogReportUtil logReport;
	private ResultDTO resultDTO;
	
	@Test(description="Login to the CafeTownSend application",priority=0,groups={"loginApplication"})	
	public void loginApplication()  throws FrameworkException {	
	
		try {
			logReport = new LogReportUtil();
			WebDriverInit.webDriverMgr = new DriverUtil();
			//Initialize objects
			actionPage = new ApplicationActions(WebDriverInit.webDriverMgr);
			resultDTO = actionPage.initBrowser();
			logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(),"");
			//User Name
			resultDTO = actionPage.configElementText(USERNAME_TEXT, actionPage.find(FrameworkLocators.USERNAME, USERNAME_TEXT), PropertyReader.getUser());
			logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(),"");
			//Password
			resultDTO = actionPage.configElementText(PASSWORD_TEXT, actionPage.find(FrameworkLocators.PASSWORD, PASSWORD_TEXT), PropertyReader.getPassowrd());
			logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(),"");
			//Click login button
			resultDTO = actionPage.clickElement(LOGIN_BUTTON,actionPage.find(FrameworkLocators.LOGIN, LOGIN_BUTTON));
			logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(),FrameworkConstants.ONFAIL_STOP);
			
		} catch (FrameworkException Fe) {
			logReport.reportTestResults(false, "Error while reading env property file "+Fe.getMessage(), FrameworkConstants.ONFAIL_STOP);
			System.out.println("Error while reading env property file "+Fe.getMessage());
		}
	}

}

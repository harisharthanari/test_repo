package com.auto.common;

import com.auto.dto.ResultDTO;
import com.auto.generic.FrameworkLocators;
import com.auto.page.ApplicationActions;
import com.auto.page.WebDriverInit;
import com.auto.util.FrameworkException;
import com.auto.util.LogReportUtil;
import com.auto.util.PropertyReader;

import org.testng.annotations.Test;

public class LogoutTest extends WebDriverInit {
	
	private ApplicationActions actionPage;
    private ResultDTO resultDTO;// = new ResultDTO();
    LogReportUtil logReport = new LogReportUtil();
    
	@Test(description="Logout  to the Alacra application",priority=1)	
	public void logout() throws FrameworkException {
		
		//Initialize objects
		actionPage = new ApplicationActions(WebDriverInit.webDriverMgr); 
		
		//Log out 
		resultDTO = actionPage.clickElement("Logout",actionPage.find(FrameworkLocators.LOGOUT, "Logout"));
		logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(),"");
		
		resultDTO = actionPage.closeWebDriver();
		logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(), "");
		 
		resultDTO = actionPage.quitWebDriver();
		logReport.reportTestResults(resultDTO.getResultStatus(),resultDTO.getResultMessage(), "");
	}
	
	
}

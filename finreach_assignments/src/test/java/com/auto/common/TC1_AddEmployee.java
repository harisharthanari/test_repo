package com.auto.common;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.auto.generic.FrameworkConstants;
import com.auto.page.ApplicationActions;
import com.auto.util.FrameworkException;
import com.auto.util.LogReportUtil;
import com.auto.page.WebDriverInit;

public class TC1_AddEmployee extends WebDriverInit {
	
	private ApplicationActions empObj;
	private LogReportUtil logReport;
	 
	@BeforeClass(description="initDrivers")
	public void initDrivers() {
			this.empObj = new ApplicationActions(WebDriverInit.webDriverMgr);
			WebDriverInit.caseTest= this.empObj;
			logReport = new LogReportUtil();
			
	}
	
	@Test(priority = 0,description="test1CreateEmployee",dependsOnGroups={"loginApplication"})
	public void testCreateEmployee() {
		try {
			
			empObj.createAndValidateEmployee();
			
			
		} catch (FrameworkException tfe) {
			logReport.reportTestResults(false, "ERROR: " + tfe.getMessage() + tfe.getCause(), FrameworkConstants.ONFAIL_STOP);
		}
		
		
	}
}

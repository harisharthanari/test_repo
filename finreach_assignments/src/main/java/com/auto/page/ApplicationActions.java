package com.auto.page;

import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.auto.util.CommonActionUtil;
import com.auto.util.DriverUtil;
import com.auto.util.FrameworkException;
import com.auto.util.LogReportUtil;
import com.auto.dto.ResultDTO;
import com.auto.generic.FrameworkConstants;
import com.auto.generic.FrameworkLocators;

public class ApplicationActions extends CommonActionUtil { 

	private WebDriver webDriver;
	private LogReportUtil logReport;
	private ResultDTO resultDTO = new ResultDTO();
	/*Empty constructor*/
	public ApplicationActions() {}

	/**
	 * @param webDriverInit
	 */
	public ApplicationActions(DriverUtil webDriverInit) {

		super(webDriverInit.getWebDriver());
		this.webDriver = webDriverInit.getWebDriver();
		logReport = new LogReportUtil();

	}


	/**
	 * @param Action
	 * @throws FrameworkException
	 * @author Harish Arthanari
	 */
	
	public void createAndValidateEmployee() throws FrameworkException {
		
		resultDTO = createEmployee();
		logReport.reportTestResults(resultDTO.getResultStatus(), resultDTO.getResultMessage(), FrameworkConstants.ONFAIL_STOP);
		if(resultDTO.getResultStatus()) {
			wait.until(ExpectedConditions.visibilityOf(find(FrameworkLocators.CREATE_EMPLOYEE_LIST, "Created employee List")));
			logReport.reportTestResults(true, "Employee List is visible to the user", FrameworkConstants.ONFAIL_PROCEED);
		}
		
	}

	public ResultDTO createEmployee() throws FrameworkException {
		boolean isPass = false;
		String firstName = "cafetest1", lastName = "cafetest1", startDate = "2018-12-24" , empEmail = "configure@configure.com";
		//ResultDTO resultDTOCreateEmployee = new ResultDTO();
		ResultDTO resultDTOFirstName = new ResultDTO();
		ResultDTO resultDTOLastName = new ResultDTO();
		ResultDTO resultDTOStartDate = new ResultDTO();
		ResultDTO resultDTOEmpEmail = new ResultDTO();
		ResultDTO resultDTOAddEmployee = new ResultDTO();
		
		sleepFor(3000);
		waitForElementExistence(FrameworkLocators.CREATE_EMPLOYEE);
		System.out.println("Started Add employee execution");
		resultDTO = clickElement("Create Employee Button",
				find(FrameworkLocators.CREATE_EMPLOYEE, "Create Employee Button"));
		if (resultDTO.getResultStatus()) {
			wait.until(ExpectedConditions.visibilityOf(find(FrameworkLocators.CREATE_FORM, "Create Case Form")));
			resultDTOFirstName = configElementText("Configure Employee First Name",
					find(FrameworkLocators.CREATE_FIRST_NAME, "Employee First name text field"),
					firstName);
			resultDTOLastName = configElementText("Configure Employee Last Name",
					find(FrameworkLocators.CREATE_LAST_NAME, "Employee Last name text field"),
					lastName);
			resultDTOStartDate = configElementText("Configure Employee Start Date",
					find(FrameworkLocators.CREATE_START_DATE, "Employee Start Date"),
					startDate);
			resultDTOEmpEmail = configElementText("Configure Employee Email",
					find(FrameworkLocators.CREATE_EMAIL, "Employee Email"),
					empEmail);
			resultDTOAddEmployee = clickElement("Add Employee Button",
					find(FrameworkLocators.CREATE_ADD, "Add Employee Button"));
			if (resultDTOFirstName.getResultStatus() && resultDTOLastName.getResultStatus()
					&& resultDTOStartDate.getResultStatus() && resultDTOEmpEmail.getResultStatus() && resultDTOAddEmployee.getResultStatus()) {
				isPass = true;
			}
			resultDTO.setResultStatus(isPass);
			StringBuffer cumulativeResultMessage = new StringBuffer();
			cumulativeResultMessage.append(resultDTOFirstName.getResultMessage());
			cumulativeResultMessage.append("\n");
			cumulativeResultMessage.append(resultDTOLastName.getResultMessage());
			cumulativeResultMessage.append("\n");
			cumulativeResultMessage.append(resultDTOStartDate.getResultMessage());
			cumulativeResultMessage.append("\n");
			cumulativeResultMessage.append(resultDTOEmpEmail.getResultMessage());
			cumulativeResultMessage.append("\n");
			cumulativeResultMessage.append(resultDTOAddEmployee.getResultMessage());
			cumulativeResultMessage.append("\n"); 
			resultDTO.setResultMessage(cumulativeResultMessage.toString());
		}
		return resultDTO;
		
	}
	
	
	
	
	public void editAndValidateEmployee() throws FrameworkException {
		
		resultDTO = editEmployee();
		logReport.reportTestResults(resultDTO.getResultStatus(), resultDTO.getResultMessage(), FrameworkConstants.ONFAIL_STOP);
		if(resultDTO.getResultStatus()) {
			wait.until(ExpectedConditions.visibilityOf(find(FrameworkLocators.CREATE_EMPLOYEE_LIST, "Created employee List")));
			logReport.reportTestResults(true, "Employee List is visible to the user", FrameworkConstants.ONFAIL_PROCEED);
		}
		
	}
	
	
	public ResultDTO editEmployee() throws FrameworkException {
		boolean isPass = false;
		String firstName = "cafetest2", lastName = "cafetest2", startDate = "2018-12-25" , empEmail = "configure2@configure.com";
		ResultDTO resultDTOFirstName = new ResultDTO();
		ResultDTO resultDTOLastName = new ResultDTO();
		ResultDTO resultDTOStartDate = new ResultDTO();
		ResultDTO resultDTOEmpEmail = new ResultDTO();
		ResultDTO resultDTOAddEmployee = new ResultDTO();
		
		waitForElementExistence(FrameworkLocators.CREATE_EMPLOYEE_LIST);
		System.out.println("Started Edit Employee execution");
		//select the recently employee name from the list
		resultDTO = selectEmployeeList("Employee list","cafetest",find(FrameworkLocators.CREATE_EMPLOYEE_LIST, "Create Case Form"));
		if (resultDTO.getResultStatus()) {
			logReport.reportTestResults(resultDTO.getResultStatus(), resultDTO.getResultMessage(), FrameworkConstants.ONFAIL_PROCEED);
			waitForElementExistence(FrameworkLocators.EDIT_EMPLOYEE);
			//Click edit button
			resultDTO = clickElement("Edit Employee Button",
					find(FrameworkLocators.EDIT_EMPLOYEE, "Edit Employee Button"));
			if (resultDTO.getResultStatus()) {
				wait.until(ExpectedConditions.visibilityOf(find(FrameworkLocators.CREATE_FORM, "Create Case Form")));
				resultDTOFirstName = configElementText("Configure Employee First Name",
						find(FrameworkLocators.CREATE_FIRST_NAME, "Employee First name text field"),
						firstName);
				resultDTOLastName = configElementText("Configure Employee Last Name",
						find(FrameworkLocators.CREATE_LAST_NAME, "Employee Last name text field"),
						lastName);
				resultDTOStartDate = configElementText("Configure Employee Start Date",
						find(FrameworkLocators.CREATE_START_DATE, "Employee Start Date"),
						startDate);
				resultDTOEmpEmail = configElementText("Configure Employee Email",
						find(FrameworkLocators.CREATE_EMAIL, "Employee Email"),
						empEmail);
				resultDTOAddEmployee = clickElement("Update Employee Button",
						find(FrameworkLocators.CREATE_UPDATE, "Update Employee Button"));
				
				if (resultDTOFirstName.getResultStatus() && resultDTOLastName.getResultStatus()
						&& resultDTOStartDate.getResultStatus() && resultDTOEmpEmail.getResultStatus() && resultDTOAddEmployee.getResultStatus()) {
					isPass = true;
				}
				resultDTO.setResultStatus(isPass);
				StringBuffer cumulativeResultMessage = new StringBuffer();
				cumulativeResultMessage.append(resultDTOFirstName.getResultMessage());
				cumulativeResultMessage.append("\n");
				cumulativeResultMessage.append(resultDTOLastName.getResultMessage());
				cumulativeResultMessage.append("\n");
				cumulativeResultMessage.append(resultDTOStartDate.getResultMessage());
				cumulativeResultMessage.append("\n");
				cumulativeResultMessage.append(resultDTOEmpEmail.getResultMessage());
				cumulativeResultMessage.append("\n");
				cumulativeResultMessage.append(resultDTOAddEmployee.getResultMessage());
				cumulativeResultMessage.append("\n"); 
				resultDTO.setResultMessage(cumulativeResultMessage.toString());
			}
		}
		return resultDTO;
	}
	
	
	public void DeleteEmployee() throws FrameworkException {
		
		resultDTO = deleteEmployee();
		logReport.reportTestResults(resultDTO.getResultStatus(), resultDTO.getResultMessage(), FrameworkConstants.ONFAIL_STOP);
		if(resultDTO.getResultStatus()) {
			wait.until(ExpectedConditions.visibilityOf(find(FrameworkLocators.CREATE_EMPLOYEE_LIST, "Created employee List")));
			logReport.reportTestResults(true, "Employee List is visible to the user", FrameworkConstants.ONFAIL_PROCEED);
		}
		
	}
	
	public ResultDTO deleteEmployee() throws FrameworkException {
		
		waitForElementExistence(FrameworkLocators.CREATE_EMPLOYEE_LIST);
		System.out.println("Started Delete Employee execution");
		//select the recently employee name from the list
		resultDTO = selectEmployeeList("Employee list","cafetest",find(FrameworkLocators.CREATE_EMPLOYEE_LIST, "Create Case Form"));
		if (resultDTO.getResultStatus()) {
			logReport.reportTestResults(resultDTO.getResultStatus(), resultDTO.getResultMessage(), FrameworkConstants.ONFAIL_PROCEED);
			waitForElementExistence(FrameworkLocators.DELETE_EMPLOYEE);
			resultDTO = clickElement("Delete Employee Button",
					find(FrameworkLocators.DELETE_EMPLOYEE, "Delete Employee Button"));
		}
		return resultDTO;
	}

}

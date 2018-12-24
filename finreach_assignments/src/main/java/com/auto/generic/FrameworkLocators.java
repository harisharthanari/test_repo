package com.auto.generic;

import org.openqa.selenium.By;

/*
 * Locators contains all the possible html elements identifiers like by name, id, xpath, classname, cssname, link text and etc
 */
public class FrameworkLocators {
	
	//#Login
	public static final By USERNAME = By.xpath("//input[@ng-model='user.name']");
	public static final By PASSWORD = By.xpath("//input[@ng-model='user.password']");
	public static final By LOGIN = By.xpath("//button[@class='main-button']"); 
	
	//#Logout
	public static final By LOGOUT = By.xpath("//p[@class='main-button']");
	
	//#Create Button
	public static final By CREATE_EMPLOYEE = By.id("bAdd");
	public static final By CREATE_FORM = By.name("employeeForm");
		
	//# Create employee
	public static final By CREATE_FIRST_NAME = By.xpath("//input[@ng-model='selectedEmployee.firstName']");
	public static final By CREATE_LAST_NAME = By.xpath("//input[@ng-model='selectedEmployee.lastName']");
	public static final By CREATE_START_DATE = By.xpath("//input[@ng-model='selectedEmployee.startDate']");
	public static final By CREATE_EMAIL = By.xpath("//input[@ng-model='selectedEmployee.email']");
	public static final By CREATE_ADD = By.xpath("//button[@class='main-button'][contains(text(),'Add')]");
	public static final By CREATE_UPDATE = By.xpath("//button[@class='main-button'][contains(text(),'Update')]");
	public static final By CREATE_DELETE = By.xpath("//p[@class='main-button'][contains(text(),'Delete')]");
	
	//#Update Button
	public static final By EDIT_EMPLOYEE = By.id("bEdit");
	//#Delete Button
	public static final By DELETE_EMPLOYEE = By.id("bDelete");
	
	//#EmployeeList
	public static final By CREATE_EMPLOYEE_LIST = By.xpath("//div[@id='employee-list-container']/ul[@id='employee-list']");
	public static final By CREATE_EMPLOYEE_LISTVALUE = By.xpath("//div[@id='employee-list-container']/ul[@id='employee-list']/li");
	
	//# Create case
	//public static final By CREATE_CASE = By.linkText("Create Cases");
	public static final By DJLOADINGICON = By.xpath("//*[@id='form1']/div[3]/img");
	
	public static final By FORMSEARCHCRITERIA = By.xpath("//form[@name='frmSearchCriteria']");
	public static final By CREATE_CASE = By.xpath("//a[contains(text(),'Create Cases')]");
	public static final By CREATE_RELATIONSHIP_TYPE = By.name("ent_ent_type");
	public static final By CREATE_RELATIONSHIP_NAME = By.name("ent_name");
	public static final By CREATE_FIRSTNAME = By.name("ent_first_name");
	public static final By CREATE_MIDDLENAME = By.name("ent_middle_name");
	public static final By CREATE_SURNAME = By.name("ent_last_name");
	public static final By CREATE_CASEPHONE = By.name("inv_requestor_phone");
	public static final By CREATE_SEGMENT = By.name("inv_queue");
	
	public static final By CREATE_CASE_NAME = By.name("inv_inv_name");
	public static final By CREATE_CASE_OWNER = By.name("inv_investigator");
	public static final By CREATE_BUTTON_EXPLORE = By.xpath("//input[@class='submit-btn']");
	public static final By CREATE_COUNTRY = By.name("ent_address_country");
	public static final By CREATE_CASE_DETAILS = By.xpath("//div[@class='form investigation']");//className("form investigation"); //;form entity
	
	public static final By CREATE_CASE_STATUS = By.xpath("//select[@name='inv_status']");
	public static final By CREATE_CASESTATUS_TEMPMSG =	By.xpath("//div[@id='prepbooksection']/descendant::span[contains(text(),'Your report is being')]");
	public static final By CASE_BUTTON_DOWNLOAD = By.xpath("//button[@name='downloadreport']");
	
	//Related Entities
	public static final By CREATE_CASE_ENTITYLIST = By.xpath("//div[@class='entitylist list']");//className("entitylist list");
	public static final By CREATE_CASE_ADDRELATEDENTITY = By.xpath("//span[@title='Add related entity']");
	public static final By CREATE_CASE_CONNECTION = By.xpath("//select[@name='entity_relation_type_id']");
	
	//Relationship information
	public static final By CREATE_CASE_ENTITYLISTTABLE = By.xpath("//table[@name='entitylist']");
	
	public static final By CREATE_CASE_ENTITYLISTROWS = By.xpath(".//tbody/tr[contains(@class,'entitylist_item')]");
	public static final By CREATE_CASE_ENTITYLISTCOLUMNS = By.xpath(".//span[@name='ent_name']");
	
			
	//# Cases dashboard 
	public static final By Modify_CASE = By.linkText("Cases");
	public static final By Modify_TABLE_QUEUES = By.id("queue");
	public static final By Modify_TABLE_ROWS = By.xpath("//table[@id='queue']/tbody/tr");
	public static final By Modify_TABLE_COLUMNS = By.tagName("td");
	public static final By DASHBOARD = By.linkText("Dashboard");
	
	//# case dashboard
	public static final By DASHBOARD_RISKEXPAND = By.xpath("//div[@class='section external_services']/div[1]/descendant::span[@class='collapsed3']");
	public static final By DASHBOARD_NEWSEXPAND = By.xpath("//div[@class='section external_services']/div[2]/descendant::span[@class='collapsed3']");
	public static final By DASHBOARD_TABLE_RISKALERT = By.xpath("//div[@class='section external_services']/div[1]/descendant::table[@class='external_service_result sortable']");
	public static final By DASHBOARD_TABLE_NEWS =  By.xpath("//div[@class='section external_services']/div[2]/descendant::table[@class='external_service_result sortable']");   //external_service_result sortable
	//public static final By DASHBOARD_TABLE_RISKALERT = By.xpath("//table[@class='external_service_result sortable']");
	public static final By DASHBOARD_RISKTABLE_ROWS = By.xpath("//table[@class='external_service_result sortable']/tbody/tr");
	public static final By DASHBOARD_RISKTABLE_COLUMNS = By.tagName("td");
	public static final By DASHBOARD_RISKTABLE_COMMENTSCOUMN = By.xpath("//td[@class='comment']/descendant::span[@class='comment']");
	//public static final By DASHBOARD_RISKTABLE_BELLICON = By.xpath("//span[@name='ext_service_bell_status']");
	public static final By DASHBOARD_RISKTABLE_COMMENTS = By.xpath("//span[@class='commentplaceholder']/descendant::div[@class='comment']");
	public static final By DASHBOARD_RISKTABLE_ERROR = By.xpath("//div[@class='result_error']/div");
	public static final By DASHBOARD_CASENAME =  By.tagName("a"); //name("//a[@name='inv_inv_name']");
	
	public static final By RISKTABLE_SECONDCOMMENT_HEADING = By.xpath("//div[@class='comment']/descendant::span[@class='heading']");
	public static final By RISKTABLE_SECONDCOMMENT_STATUS = By.xpath("//select[@name='search_res_comm_id_hor']");
	public static final By DASHBOARD_RISKALERT_STATUS = By.xpath("//select[@name='search_res_comm_id_hor']");
	public static final By DASHBOARD_RISKALERT_SAVE = By.xpath("//input[@value='Save']");	//name("search_res_comm_id_ho");
	public static final By DASHBOARD_RISKALERT_BELLICON = By.className("bell");
	public static final By DASHBOARD_RISKALERT_CHECKBOX = By.xpath("//input[@name='external_result_id']") ; //name("external_result_id");
	public static final By DASHBOARD_RISKALERT_STATUSCOL = By.xpath("//span[@name='search_res_comm_id_hor']/descendant::span");
	public static final By DASHBOARD_TABLE_FOOTER =  By.xpath("//tfoot");
	public static final By DASHBOARD_TABLE_FOOTER_PERPAGE = By.xpath("//tfoot/descendant::select[@name='batch_size']");	
	public static final By DASHBOARD_TABLE_FOOTER_PREV = By.xpath("//tfoot/descendant::span[@class='navigation prev']");
	public static final By DASHBOARD_TABLE_FOOTER_NEXT = By.xpath("//tfoot/descendant::span[@class='navigation next']");
	public static final By DASHBOARD_TABLE_FOOTER_NAVIGATION = By.xpath("//tfoot/descendant::span[@class='navigation-container']");
	public static final By DASHBOARD_TABLE_FOOTER_PAGEVALUE = By.xpath("//tfoot/descendant::span[@class='navigation-container']/span");
	public static final By DASHBOARD_TABLE_FOOTER_NAVINFOTEXT = By.xpath("//span[@class='nav-info-text']");
	
	
	public static final By DASHBOARD_TABLE_lOADICON = By.xpath("//div[@class='blockUI']");
	
	public static final By CREATE_CASE_REGION = By.name("inv_sales_location");
	public static final By CREATE_CASE_ENTITY_NAME = By.name("ent_name");
	public static final By SEARCH = By.xpath("//input[@data-action='search']");
	public static final By SAVE = By.xpath("//input[@value='Save']");
	public static final By SUBMIT = By.xpath("//input[@value='Submit']");
	public static final By ERROR = By.className("error1");
	public static final By CREATE_ENTITY = By.name("btnUseEntity");
	public static final By SOURCES = By.name("sid");
	public static final By ENTITY_RISK_LEVEL = By.name("ent_risk_level");
	public static final By ENTITY_LIST = By.xpath("//div[@data-source='entitylist']");
	public static final By ENTITY_USE_EXISTS = By.xpath("//div[@class='result']");
	public static final By ENTITY_CREATE_EXISTS = By.xpath("//div[@class='resultmatch']");
	public static final By FIND_ENTITY = By.linkText("Find Entity");
	public static final By FIND_ENTITY_FILTER = By.name("ent_filter_field");
	public static final By FIND_ENTITY_NAME = By.name("ent_name");
	public static final By FIND_ENTITY_ADD = By.id("add");
	public static final By FIND_ENTITY_FORM_SEARCH = By.xpath("//div[@class='form search']");
	public static final By CONTROL = By.className("control");
	public static final By EDIT_ENTITY = By.xpath("//span[@data-value='6']");
	public static final By CLIENT_ID = By.name("client_ent_id");
	public static final By UPDATE_FORMENTITY = By.className("form entity"); //;form entity
	public static final By IMG_ICON = By.xpath("//img[@src='/ace/img/check.png']");
	
	//# Bulk upload
	public static final By BULKIMPORT_DOWNLOADTEMPLATE = By.linkText("Download Template");
	public static final By BULKIMPORT_UPLOAD = By.linkText("Upload");
	
	public static final By BULKIMPORT_BUTTON_BROWSE = By.xpath("//input[@name='file']");
	//public static final By BULKIMPORT_BUTTON_BROWSE = By.name("file");
	public static final By BULKIMPORT_BUTTON_UPLOAD =  By.xpath("//button[@name='upload']");	
	
	public static final By BULKIMPORT_UPLOADTABLE = By.xpath("//div[@class=' bulkimport']/descendant::table/descendant::tbody/tr");
	public static final By BULKIMPORT_UPLOADTABLE_COLUMNS = By.tagName("td");
	public static final By BULKIMPORT_UPLOADTABLE_STATUSVALUE = By.tagName("a"); 
	
	public static final By BULKIMPORT_FORM_CASEID = By.xpath("//div[@class='form investigation']");
	public static final By BULKIMPORT_FORM_CASENAME = By.xpath("//span[@name='inv_inv_name']");
	
	//## Case Details 
		public static final By CASE_CASENAME = By.xpath("//span[@name='inv_inv_name']");
		public static final By CASE_SEGMENT = By.xpath("//span[@name='inv_queue']");
		public static final By CASE_PRIORITY = By.xpath("//span[@name='inv_priority']");
		public static final By CASE_REQUESTOR = By.xpath("//span[@name='inv_requestor_name']");
		public static final By CASE_CASEOWNER = By.xpath("//span[@name='inv_investigator']");
		public static final By CASE_CASEOWNERPHONE = By.xpath("//span[@name='inv_requestor_phone']"); 
		public static final By CASE_CASEOWNEREMAIL = By.xpath("//span[@name='inv_requestor_email']");
		public static final By CASE_COMMENT = By.xpath("//span[@class='text comment']");
		
	//## Relationship Details 
		
		//## General Information
		public static final By RELATIONSHIP_RELATIONHIPTYPE = By.xpath("//span[@name='ent_ent_type']");
		public static final By RELATIONSHIP_NAME =  By.xpath("//div[@class='form entity']/descendant::span[@name='ent_name']");
		public static final By RELATIONSHIP_FIRSTNAME = By.xpath("//div[@class='entity_item  alt ']/descendant::span[@name='ent_first_name']");
		public static final By RELATIONSHIP_MIDDLENAME = By.xpath("//div[@class='entity_item  alt ']/descendant::span[@name='ent_middle_name']");
		public static final By RELATIONSHIP_SURNAME = By.xpath("//div[@class='entity_item  alt ']/descendant::span[@name='ent_last_name']");
		public static final By RELATIONSHIP_ID = By.xpath("//span[@name='client_ent_id']");
		public static final By RELATIONSHIP_ALTERNATIVENAME = By.xpath("//span[@name='ent_original_script_name']");
		public static final By RELATIONSHIP_INDUSTRYSECTOR = By.xpath("//span[@name='inv_industry_v']");
		public static final By RELATIONSHIP_ASSOCIATTIONTYPE =  By.xpath("//span[@name='ent_association_type']");
		public static final By RELATIONSHIP_IDENTIFICATIONTYPE = By.xpath("//span[@name='ent_identification_type']");
		public static final By RELATIONSHIP_IDENTIFICATIONVALUE = By.xpath("//span[@name='ent_identification_value']");
		public static final By RELATIONSHIP_RELATIONSHIPID = By.xpath("//span[@name='client_ent_id']");
		public static final By RELATIONSHIP_RELATIONSHIP_PRIORITY = By.xpath("//span[@name='ent_risk_level']");
		public static final By RELATIONSHIP_GENDER = By.xpath("//span[@name='ent_gender']");
		public static final By RELATIONSHIP_DOB = By.xpath("//span[@name='ent_date_of_birth']");
				
		//## Address information
		public static final By RELATIONSHIP_ADDRESSLINE = By.xpath("//span[@name='ent_address_address']");
		public static final By RELATIONSHIP_CITY = By.xpath("//span[@name='ent_address_city']");
		public static final By RELATIONSHIP_STATE = By.xpath("//span[@name='ent_address_state_name']"); 
		public static final By RELATIONSHIP_POSTALCODE = By.xpath("//span[@name='ent_address_zip']");
		public static final By RELATIONSHIP_COUNTRY = By.xpath("//div[@class='form entity']//descendant::span[@name='ent_address_country']");
		public static final By RELATIONSHIP_ADDRESSURL = By.xpath("//span[@name='ent_address_address_5']");
		public static final By RELATIONSHIP_PHONE = By.xpath("//span[@name='ent_address_address_6']");
		
		//## Other Information
		public static final By RELATIONSHIP_DOCUMENTLINKS = By.xpath("//span[@name='ent_doc_links']");
		public static final By RELATIONSHIP_NOTE1 = By.xpath("//span[@name='ent_notes_1']");
		public static final By RELATIONSHIP_NOTE2 = By.xpath("//span[@name='ent_notes_2']");
		
		// ## Filters
		public static final By FILTER_RELATIONSHIPID = By.name("client_ent_id");
		
		public static final By FILTER_CRITERIA = By.name("filter_field");
		public static final By FILTER_VALUE_BELLSTATUS = By.name("inv_bell_status");
		public static final By FILTER_VALUE_CASEID = By.name("inv_request_id");
		public static final By FILTER_VALUE_CASENAME = By.name("inv_inv_name");
		public static final By FILTER_VALUE_CASEOWNER = By.name("inv_investigator");
		public static final By FILTER_VALUE_CASEEMAIL = By.name("inv_requestor_email");
		public static final By FILTER_VALUE_CASEPRIORITY = By.name("inv_priority");
		public static final By FILTER_VALUE_COUNTRY = By.name("ent_address_country");
		public static final By FILTER_VALUE_DATEADDED = By.name("inv_created");
		public static final By FILTER_VALUE_DATELASTUPDATED = By.name("ext_service_completed");
		public static final By FILTER_VALUE_FIRSTNAME = By.name("ent_first_name");
		public static final By FILTER_VALUE_RISKFLAG = By.name("inv_has_given_risk");
		public static final By FILTER_VALUE_MIDDLENAME = By.name("ent_middle_name");
		public static final By FILTER_VALUE_RELATIONSHIPID = By.name("client_ent_id");
		public static final By FILTER_VALUE_RELATIONSHIPNAME = By.name("ent_name");
		public static final By FILTER_VALUE_RELATIONSHIPTYPE = By.name("ent_ent_type");
		public static final By FILTER_VALUE_SCREENING = By.name("ie_has_monitoring");
		public static final By FILTER_VALUE_SEGMENT = By.name("inv_queue");
		public static final By FILTER_VALUE_STATUS = By.name("inv_status");
		public static final By FILTER_VALUE_SURNAME = By.name("ent_last_name");
		
		
		public static final By FILTER_ADD = By.xpath("//span[@id='add']");
		public static final By FILTER_ITEM = By.xpath("//span[@class='fitem']");
		public static final By FILTER_DELETE = By.xpath("//span[@class='delete']");
		public static final By FILTER_EMAIL = By.name("inv_requestor_email");
		
}

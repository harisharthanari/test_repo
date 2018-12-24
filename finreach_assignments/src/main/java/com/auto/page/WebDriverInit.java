package com.auto.page;

import com.auto.page.ApplicationActions;
import com.auto.util.DriverUtil;

public class WebDriverInit {
	
	/**
	 * Class is to declare the WebDriverMgr class variable as static for using it across the test cases.
	 * @author Harish Arthanari
	 *
	 */
		//declare the static variable
		public static DriverUtil webDriverMgr = null;
		public static ApplicationActions caseTest;
        public static String testName = null;
}
